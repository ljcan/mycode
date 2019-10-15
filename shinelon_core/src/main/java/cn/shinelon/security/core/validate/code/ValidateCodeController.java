package cn.shinelon.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.alibaba.fastjson.JSONObject;

import cn.shinelon.security.core.properties.SecurityProperties;

@RestController
public class ValidateCodeController {
	//Spring的工具类
	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
    static final String SESSION_KEY="SESSION_KEY_CODE";
    static final String MOBILE_SESSION_KEY="MOBILE_SESSION_KEY";
    
    private SimpleSmsAPI api=new SimpleSmsAPI();
    
    @Autowired
    private SecurityProperties securityProperties;
	
	@GetMapping("/code/image")
	public void createCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
		ImageCode imageCode=createImageCode(request);
		//第一个参数是请求，第二个是session key，第三个是imagecode
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
	}
	
	@GetMapping("/code/sms")
	public void createMobileCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
		String mobile=request.getParameter("mobile");
		//随机生成六位随机短信码
		String code = RandomStringUtils.randomNumeric(6);
		ValidateCode validateCode=new ValidateCode(code, 60);
		sessionStrategy.setAttribute(new ServletWebRequest(request), MOBILE_SESSION_KEY, validateCode);
		System.out.println("向手机"+mobile+"发送验证码"+code);
		post("https://open.ucpaas.com/ol/sms/sendsms",mobile,code);
	}
	//调用短信接口
	 public void post(String Url,String mobile,String code) throws IOException {
	        CloseableHttpClient httpClient=HttpClients.createDefault();
	        HttpPost httpPost=new HttpPost(Url);
	        api.setMobile(mobile);
	        api.setParam(code);
	        StringEntity stringEntity=new StringEntity(api.toString(),Charset.forName("UTF-8"));
	        stringEntity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
	        stringEntity.setContentType("application/json");
	        httpPost.setEntity(stringEntity);
	        System.out.println("请求路径是：");
	        System.out.println(httpPost.getURI().getPath());
	        String mm=EntityUtils.toString(httpPost.getEntity());
	        System.out.println("请求体是：");
	        System.out.println(mm);
	        System.out.println("==================");
	        HttpResponse httpResponse=httpClient.execute(httpPost);
	    }
	 
	 //回调函数
	 @RequestMapping("/return")
	 public void get(String returnmsg) {
		 System.out.println("===================函数回调===================");
		JSONObject jsonObject=JSONObject.parseObject(returnmsg);
		String code=jsonObject.getString("code");
		String msg=jsonObject.getString("msg");
		String mobile=jsonObject.getString("mobile");
		System.out.println("code="+code+"  msg="+msg+"  mobile="+mobile);
	 }
	
	

	private ImageCode createImageCode(HttpServletRequest request) {
		int width=67;
		int height=23;
		BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics gs=bufferedImage.getGraphics();
		Random random=new Random();
		gs.setColor(getRandColor(200,250));
		gs.fillRect(0, 0, width, height);
		gs.setFont(new Font("The New Roman", Font.ITALIC, 20));
		gs.setColor(getRandColor(160,200));
		for(int i=0;i<155;i++) {
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int x1=random.nextInt(12);
			int y1=random.nextInt(12);
			gs.drawLine(x, y, x+x1, y+y1);
		}
		String sRand="";
		for(int i=0;i<4;i++) {
			String rand=String.valueOf(random.nextInt(10));
			sRand+=rand;
			gs.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110)));
			gs.drawString(rand, 13*i+6, 16);
		}
		gs.dispose();
		
		return new ImageCode(bufferedImage, sRand, 60);
	}
	/**
	 * 生成随机背景条纹
	 * @param i
	 * @param j
	 * @return
	 */
	private Color getRandColor(int i, int j) {
		Random random=new Random();
		if(i>255) {
			i=255;
		}
		if(j>255) {
			j=255;
		}
		int r=i+random.nextInt(j-i);
		int g=i+random.nextInt(j-i);
		int b=i+random.nextInt(j-i);
		return new Color(r, g, b);
	}
}
