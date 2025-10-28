package com.sg.floormaster.controller;

import com.sg.floormaster.dto.Tax;
import com.sg.floormaster.ui.OrderView;
import com.sg.floormaster.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
@Controller
public class OrderController {

    private OrderService service;
    private OrderView view;

    @Autowired
    public OrderController(OrderService service, OrderView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        view.displayBookTrackerBanner();
        
        while(true) {
            int choice = view.displayMenuAndGetChoice();
            
            switch(choice) {
                case 1: //view all
                    List<Tax> books = service.getAllBooks();
                    view.displayOrders(books);
                    break;
                case 2: //view one
                    String title = view.getOrderByDate();
                    Tax Order = service.getBookByTitle(title);
                    if(Order != null) {
                        view.displayOrderDetails(Order);
                    } else {
                        view.displayError("Order does not exist");
                    }
                    break;
                case 3: //add
                    Tax newBook = view.getNewOrder();
                    service.addBook(newBook);
                    view.displayAddSuccess();
                    break;
                case 4: //update
                    String updateTitle = view.getOrderToUpdate();
                    Tax updateBook = service.getBookByTitle(updateTitle);
                    if(updateBook != null) {
                        updateBook = view.getUpdateOrder(updateBook);
                        service.updateBook(updateBook);
                        view.displayUpdateSuccess();
                    } else {
                        view.displayError("Order doesn't exist");
                    }
                    break;
                case 5: //delete
                    String deleteTitle = view.getOrderToDelete();
                    Tax deleteBook = service.getBookByTitle(deleteTitle);
                    if(deleteBook != null) {
                        service.deleteBookByTitle(deleteTitle);
                        view.displayDeleteSuccess();
                    } else {
                        view.displayError("Order doesn't exist");
                    }
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
