package com.stem.springwebapp.demo.security;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
 
 
@Configuration
@ImportResource({ "classpath*:security-config.xml" })
public class Security {
}