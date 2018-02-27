package io.sentry.example;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    private static final Logger logger = LogManager.getLogger("example.Application");

    @RequestMapping("/")
    @ResponseBody
    String home() {
        ThreadContext.put("customKey1", "customValue1");
        ThreadContext.put("customKey2", "customValue2");
        logger.warn("Warn message"); //warning message that will be sent to Sentry

        int x = 1 / 0; //uncaught exception that will be sent to Sentry

        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
