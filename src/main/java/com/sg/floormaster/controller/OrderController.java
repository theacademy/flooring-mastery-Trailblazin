package com.sg.floormaster.controller;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import com.sg.floormaster.service.OrderService;
import com.sg.floormaster.ui.OrderView;
import com.sg.floormaster.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
@Controller
public class OrderController {

    private final OrderServiceImpl service;
    private final OrderView view;

    @Autowired
    public OrderController(OrderServiceImpl service, OrderView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run()  {
        try {
            service.loadOrders();
            service.loadProducts();
            service.loadTaxes();
        }
        catch (Exception e)
        {
            System.out.println("Error in loading files for application: " + e.getMessage());
            System.exit(0);
        }
        view.displayOrderTrackerBanner();
        
        while(true) {
            int choice = view.displayMenuAndGetChoice();
            
            switch(choice) {
                case 1: //view all orders
                    view.displayOrders(service.getOrders());
                    break;
                case 2: //add one new order
                    //Provide date from first order to get orders
                    Order newOrder = view.getNewOrder(service.getOrders().get(0).getOrderDate(),service.getTaxes(),service.getProducts());
                    if (newOrder != null){
                        service.addOrder(newOrder);
                        view.displayAddSuccess();
                    }
                    break;
                case 3: //Edit an Order
                    Order orderToEdit =  view.getOrder(service.getAllOrders());
                    Order orderChanges = view.updateOrder(orderToEdit);
                    if(orderChanges != null) {
                        service.addOrder(orderChanges);
                        view.displayUpdateSuccess();
                    } else {
                        view.displayError("Order does not exist");
                    }
                    break;
                case 4: //Remove an Order
                    Order orderToDelete =  view.deleteOrder(service.getAllOrders());
                    if(orderToDelete != null) {
                        service.removeOrder(orderToDelete);
                        view.displayDeleteSuccess();
                    } else {
                        view.displayError("Order doesn't exist");
                    }
                    break;
                case 5: //Export All Data
                    view.displayError("Operation unsupported");
                    break;
                case 6: //exit
                    view.displayExit();
                    System.exit(0);
                    break;
                default: //unknown
                    view.displayError("Unknown Option");
                    break;
            }
        }
    }
    
}
