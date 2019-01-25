package com.qs.p2p.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by scq on 2018-01-26 11:34:55
 */
@SpringBootApplication
@MapperScan("com.qs.p2p.generator")
public class ApplicationGenerator {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGenerator.class, args);
	}
}
