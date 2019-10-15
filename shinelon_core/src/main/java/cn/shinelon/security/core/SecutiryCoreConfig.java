package cn.shinelon.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.shinelon.security.core.properties.SecurityProperties;
//为了让自定义跳转的配置生效
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecutiryCoreConfig {

}
