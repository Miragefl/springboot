package com.viscum.chapter05;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter05Application {


    private static Logger logger = Logger.getLogger(Chapter05Application.class);

    public static void main(String[] args) {
        logger.info("springboot init");

        SpringApplication.run(Chapter05Application.class, args);
    }
}
