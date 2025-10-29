package com.sg.floormaster;

import com.sg.floormaster.controller.OrderController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kyle David Rudy
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.floorMaster");
        appContext.refresh();
        
        OrderController controller = appContext.getBean("orderController", OrderController.class);
        controller.run();
    }
}
