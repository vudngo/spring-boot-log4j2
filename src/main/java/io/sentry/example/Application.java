package io.sentry.example;

import org.apache.logging.log4j.ThreadContext;
import org.apache.tomcat.jni.Error;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    private static final Logger logger = LogManager.getLogger("example.Application");

    private static Map<String, Integer> inventory = new HashMap<>();

    private void checkout(List<Item> cart) {
        Map<String, Integer> tempInventory = inventory;

        for (Item item : cart) {
            int currentInventory = tempInventory.get(item.getId());
            if (currentInventory <= 0) {
                throw new RuntimeException("No inventory for " + item.getId());
            }

            tempInventory.put(item.getId(), currentInventory-1);
        }
        inventory = tempInventory;
    }

    @PostMapping(value="/checkout", headers="Accept=application/json")
    @ResponseBody
    public String checkoutCart(@RequestBody Order order){
        logger.info("Processing order for: " + order.getEmail());
        checkout(order.getCart());
        return "Success";
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        ThreadContext.put("customKey1", "customValue1");
        ThreadContext.put("customKey2", "customValue2");

        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message"); // warning message that will be sent to Sentry

        handledError();
        unhandledError();

        return "Hello World";
    }

    // changing stack trace: attempt 1
    private void handledError() {
        try {
            int example = 1 / 0;
        } catch (Exception e) {
            // caught exception that will be sent to Sentry
            logger.error("Caught exception!", e);
        }
    }

    private void unhandledError() {
        throw new RuntimeException("Unhandled exception!");
    }

    public static void main(String[] args) {
        inventory.put("wrench", 0);
        inventory.put("nails", 0);
        inventory.put("hammer", 1);
        SpringApplication.run(Application.class, args);
    }
}
