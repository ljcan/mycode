package cn.microboot.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
//1.
//@Controller
////开启自动配置处理，必须加这个注解，springboot的固定配置，是springboot启动的配置，必须与主类在一起
////如果有多个controller时，可以建立一个controller包，但是这个包必须是这个主类所在包的子类中，官方的强烈建议
//@EnableAutoConfiguration
//public class SampleController {
//
//    @RequestMapping("/")
//    @ResponseBody		//在restful的架构中，该注解是将返回值作为字符串或者json的格式直接返回
//    public String home() {
//        return "Hello World!";
//    }
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
//    }
//}
import org.springframework.scheduling.annotation.EnableScheduling;


//2.拆分controller
//@Controller
//开启自动配置处理，必须加这个注解，springboot的固定配置，是springboot启动的配置，必须与主类在一起
//如果有多个controller时，可以建立一个controller包，但是这个包必须是这个主类所在包的子类中，官方的强烈建议
//@EnableAutoConfiguration
//当分离controller后，要启动程序，必须自动扫描注解
//@ComponentScan("cn.microboot.base")
//除了上面的@EnableAutoConfiguration和@ComponentScan("cn.microboot.base")这两个注释结合使用外，还可以
//使用springboot提供的注解代替这两个注解
@SpringBootApplication
@EnableScheduling		//支持计时器
public class StartSpringBootMain {
  public static void main(String[] args) throws Exception {
      SpringApplication.run(StartSpringBootMain.class, args);
  }
}