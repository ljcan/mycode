package cn.shinelon.Controller;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class OrderController {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderController.class, args);
	}
	
	@RequestMapping("/OrderUser")
	public String getOrderUserId(Integer id) throws IOException {
		String result=get("http://localhost:8080/memeber/getUser?id="+id);
		return result;
	}
	public String get(String Url) throws IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//����httpGet
		HttpGet httpGet=new HttpGet(Url);
		System.out.println("URL is "+httpGet.getURI());
		CloseableHttpResponse response = null;
		try {
			//ִ��http����
			response=httpClient.execute(httpGet);
			//��ȡhttp��Ӧ��
			HttpEntity entity=response.getEntity();
			System.out.println("-----------------");
			//��ӡ��Ӧ״̬
			System.out.println(response.getStatusLine());
			if(entity!=null) {
				System.out.println("Response Content Length:"+entity.getContentLength());
				String content=EntityUtils.toString(entity);
				System.out.println("Response Content:"+content);
				return content;
			}
			System.out.println("------------------");
		
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			response.close();
			httpClient.close();
		}
		return null;
	}
}
