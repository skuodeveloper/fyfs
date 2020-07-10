package com.nhga.fyfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nhga.fyfs.mapper")
public class FyfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FyfsApplication.class, args);
	}

}
