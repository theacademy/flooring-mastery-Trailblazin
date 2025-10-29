package com.sg.floormaster.ui;

import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Kyle David Rudy
 */
@Component
public class OrderView {

    private UserIO io;

    @Autowired
    public OrderView(UserIO io) {
        this.io = io;
    }
    /*
        @Autowired
        private UserIO io;
     */

    public void displayOrderTrackerBanner() {
        io.print("Welcome to the Flooring Program:");
    }

    public int displayMenuAndGetChoice() {
        io.print("");
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Exit");

        return io.readInt("Please select an option:", 1, 6);
    }

    public void displayError(String error) {
        io.print("ERROR: " + error);
    }

    public void displayOrders(List<Order> orders) {
        io.print("");
        io.print("Show All Orders");

        LocalDate date = null;
        while (date == null){
            date = io.readDate("Enter a valid order date:");
        }

        //Iterate and output all orders that match date
        for (Order order : orders)
        {
            if(order.getOrderDate().isEqual(date))
            {
                displayOrderDetails(order);
            }
        }
    }


    public void displayOrderDetails(Order order) {
        io.print("Order details for Order Number: " + order.getOrderNumber());
        //TODO: Format toString
        io.print(order.toString() + "\n");
    }

    //Use Case: Add new order
    public Order getNewOrder(LocalDate originalDate, List<Tax> taxes, List<Product> products) {
        io.print("");
        io.print("Enter new order info");
        LocalDate orderDate = io.readDate("Please enter a valid order date:", originalDate);
        String customerName = io.readString("Please enter a valid customer name:");
        List<String> taxState = taxes.stream().map(Tax::getStateAbbr).collect(Collectors.toList());
        String state = io.readString("Please enter a valid state: (" + taxState + ")");
        List<String> productTypes = products.stream().map(Product::getProductType).collect(Collectors.toList());
        String productType = io.readString("Please enter a valid product type: (" + productTypes + ")");
        BigDecimal area = io.readDecimal("Please enter an area (sq.ft) for your order (>= 100 ): ");
        Order order = new Order(orderDate, customerName, state, productType, area);

        //Returns the order or null, depending on if user wants to make changes
        return confirmOrderChanges(order, "create");
    }

    private Order confirmOrderChanges(Order order, String modification) {
        displayOrderDetails(order);

        if (confirmOrderModification(modification))
            return order;
        return null;
    }

    public boolean confirmOrderModification(String modification) {
        return io.readString(String.format("Would you like to %s this order? " +
                        "Press Y or y for Yes, anything else for No.",modification))
                .equalsIgnoreCase("y");
    }

    public void displayAddSuccess() {
        io.print("Order added successfully");
    }


    //Helper method, get order by date and order no

    public Order getOrder(Map<Integer, Order> orders) {
        io.print("");
        LocalDate date = io.readDate("Enter date for the orders to retrieve:");
        for (Order order : orders.values())
        {
            if(order.getOrderDate().isEqual(date))
            {
                displayOrderDetails(order);
            }

        }
        int orderNo = io.readInt("Enter the Order No. for the order to retrieve:");
        Order orderMatch = orders.get(orderNo);
        return orderMatch;
    }

    //UseCase update an order

    //TODO: Why return, we can just update by Order No as key in orders
    public Order updateOrder(Order orderToUpdate) {
        io.print("Updating Order: " + orderToUpdate.getOrderNumber());

        String currState = orderToUpdate.getState();
        String currProductType = orderToUpdate.getProductType();

        String newCustomerName = io.readName("Enter the new customer name (Current: " + orderToUpdate.getCustomerName());
        String newStateAbbr = io.readString("Enter the new state (Current: " + orderToUpdate.getState());
        String newProductType = io.readString("Enter the new state (Current: " + orderToUpdate.getProductType());

        int newArea = io.readInt("Enter the new state (Current: " + orderToUpdate.getArea(), 100, "");
        // Can cause overflow but should not
        int currArea = orderToUpdate.getArea().intValue();
        //Could refactor, is -1 denotes an ignore input
        //User does not want to change the current area
        if (newArea == -1) {
            newArea = currArea;
        }


        boolean needsRecalc = false;

        if (!Objects.equals(currState, newStateAbbr) ||
                !Objects.equals(currProductType, newProductType) || newArea != currArea) {
            needsRecalc = true;
        }

        if (!newCustomerName.equalsIgnoreCase("")) {
            orderToUpdate.setCustomerName(newCustomerName);
        }
        if (!newStateAbbr.equalsIgnoreCase("")) {
            orderToUpdate.setState(newStateAbbr);
        }
        if (!newProductType.equalsIgnoreCase("")) {
            orderToUpdate.setProductType(newProductType);
        }
        if (newArea != currArea) {
            //Convert new area to string and use to create new Big Decimal with new value
            orderToUpdate.setArea(new BigDecimal(Integer.toString(newArea)));
        }
        if (needsRecalc)
            orderToUpdate.calculateOrderDetails();

        //Returns the order or null, depending on if user wants to make changes
        return confirmOrderChanges(orderToUpdate, "update");

    }

    public void displayUpdateSuccess() {
        io.print("Order updated successfully");
    }

    public Order deleteOrder(Map<Integer, Order> orders) {
        io.print("Preparing to Delete Order:");
        // Will get order given input, ask if they want to delete order
        // If order is returned, delete is wanted; if null, do not delete
        io.print(orders.values().toString());
        return confirmOrderChanges(getOrder(orders),"delete");
    }

    public void displayDeleteSuccess() {
        io.print("Order deleted successfully");
    }


    public void displayExit() {
        io.print("Exiting Floor Master");
    }
}
