//package cn.shinelon.wiremock;
//
//
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
//import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
//
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.core.io.ClassPathResource;
//
//
//public class TestWrieMock {
//	public static void main(String[] args) throws IOException {
//		configureFor(8081);
//		removeAllMappings(); 	//清除以前的映射
//		mock("/order/1","1");
//		
//		
//	}
//	private static void mock(String url, String filename) throws IOException{
//		ClassPathResource classPathResource=new ClassPathResource("/mock/response/"+filename+".txt");
//		//FileUtils.readLines该方法返回的是一个list，所以需要使用StringUtils.join将其连接在一起，最后需要处理回车
//		String content=StringUtils.join(FileUtils.readLines(classPathResource.getFile(),"UTF-8").toArray(),"\n");
////		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).
////				willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
//		//设置了偏好设置
//		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
//	}
//	}
//
