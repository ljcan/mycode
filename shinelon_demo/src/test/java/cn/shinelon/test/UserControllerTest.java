/**
 * 
 */
package cn.shinelon.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author Shinelon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user")
//		.contentType(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
		
		//在STS里的偏好设置中设置了这几个类，所以可以自动引入其静态方法
		String result=mockMvc.perform(get("/user")
//				.param("username", "shinelon")
				.param("username", "shinelon")
				.param("sex", "male")
				.param("age", "18")
				.param("address", "北京市")
				.param("size", "15")	//分页的信息
				.param("page", "3")
				.param("sort", "age,desc")		//按照年龄升序排列
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();			//打印后台返回的json格式字符串
		System.out.println(result);
	}
	@Test
	public void whenQuery() throws Exception {
		String result=mockMvc.perform(get("/user")
		.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(3))
		.andReturn().getResponse().getContentAsString();	
		System.out.println(result);
		
	}
	
	@Test
	public void whenGetSuccess() throws Exception {
		String result=mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk())
		        .andExpect((ResultMatcher) jsonPath("$.username").value("shinelon"))
		        .andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	@Test
	//测试url中插入正则表达式来控制传入参数的类型
	public void whenGetInfoSuccess() throws Exception {
		mockMvc.perform(get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());  		//前台用正则表达式来控制传入的参数是整数，
		//而这里传入了一个字符，所以我们期望它失败时返回以4开头的，当测试成功是不会报错。
	}
	
	@Test
	public void whenPostSuccess() throws Exception {
		//一年以后的时间
		Date date=(Date) new java.util.Date(LocalDateTime.now().plusYears(1).
				atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content="{\"username\":\"shinelon\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result=mockMvc.perform(post("/user")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	//修改用户信息
	@Test
	public void whenPutSuccess() throws Exception {
		//过去的一年时间
		Date date=(Date) new java.util.Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		
		String content="{\"id\":1,\"username\":\"shinelon\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result=mockMvc.perform(put("/user/1")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	//删除用户信息
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	
	//测试文件上传
	@Test
	public void fileUpload() throws UnsupportedEncodingException, Exception {
		String result=mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file_upload")
				.file(new MockMultipartFile("file","test.txt" ,"multipart/form-data", "hello world".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	
}
