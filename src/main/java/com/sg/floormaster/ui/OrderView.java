package com.sg.floormaster.ui;

import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    public void displayBookTrackerBanner() {
        io.print("Welcome to the Flooring Program:");
    }

    public int displayMenuAndGetChoice() {
        io.print("");
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("3. Add Order");
        io.print("2. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Exit");

        return io.readInt("Please select an option:", 1, 6);
    }

    public void displayError(String error) {
        io.print("ERROR: " + error);
    }

    public void displayOrders(Map<Integer, Order> orders) {
        io.print("");
        io.print("All Orders");

        LocalDate date = io.readDate("Enter order date:");
        //Steam Orders in Order to get all order information
        orders.entrySet()
                //Stream collection of Orders
                .stream()
                //Get all orders that match the date
                .filter(order -> order.getValue().getOrderDate().equals(date))
                //Print Details of the order
                // .forEach(order -> io.print(order.toString()));
                .forEach(order -> displayOrderDetails((Order) order));
    }


    public void displayOrderDetails(Order order) {
        io.print("");
        //TODO: Format toString
        io.print(order.toString());
    }

    //Use Case: Add new order
    public Order getNewOrder() {
        io.print("");
        io.print("Enter new order info");
        LocalDate orderDate = io.readDate("Please enter a valid order date:", LocalDate.now());
        String customerName = io.readString("Please enter a valid customer name:");
        // TODO: Make States and Product Type Sets to be read from memore
        //TODO Make Tax Dict with State and value
        String state = "";
        String productType = "";
        BigDecimal area = io.readDecimal("Please enter an area (sq.ft) for your order (>= 100 ): ");
        Order order = new Order(orderDate, customerName, state, productType, area);
        //TODO: Need to get order number from Latest Order number in set/map
        // order.setOrderNumber(orders.size()++);
        order.setOrderNumber(0);
        //TODO: Modify to correctly read from tax
        order.setTaxRate(new BigDecimal("0"));
        //TODO: Modify to correctly read from product
        order.setMatCostPerSqFoot(new BigDecimal("0"));
        order.setLabourCostPerSqFoot(new BigDecimal("0"));

        //Do automatic calculations for remaining product values
        //TODO: Move to one helper method in Order
        order.setTax();
        order.setMaterialCost();
        order.setLabourCost();
        order.setTotal();

        displayOrderDetails(order);

        if (confirmOrderUpdate())
            return order;

        //If use does not want to make update
        return null;
    }

    public boolean confirmOrderUpdate() {
        return io.readString("Would you like to place this order? " +
                        "Press Y or y for Yes, anything else for No.")
                .equalsIgnoreCase("y");
    }

    public void displayAddSuccess() {
        io.print("Order added successfully");
    }

    public Order getOrderToUpdate(Map<Integer, Order> orders) {
        io.print("");
        LocalDate date = io.readDate("Enter date for the orders to update:");
        //TODO: Add a print statement to display all orders which match the date
        int orderNo = io.readInt("Enter the Order No. for the order to update:");
        //Stream Orders in Order to get all order information
        /*
       Order orderMatch = (Order) orders.entrySet()
                //Stream collection of Orders
                .stream()
                //Get all orders that match the date and number - should be one
                .filter(entry -> entry.getValue().getOrderDate().equals(date)
                        && entry.getValue().getOrderNumber() == orderNo)
                //Hence, why findFirst is used
                .findFirst()
                .orElse(null);
         */
        Order orderMatch = orders.get(orderNo);
        if(orderMatch != null && orderMatch.getOrderDate() == date){
            return orderMatch;
        }
        // Should never happen,  we can't be using the same ID for multiple dates
        else {
            return null;
        }
    }

    public Order getUpdateOrder(Order orderToUpdate) {
        io.print("Updating Order: " + orderToUpdate.getOrderNumber());

        String currCustomerName = orderToUpdate.getCustomerName();
        String currState = orderToUpdate.getState();
        String currProductType = orderToUpdate.getProductType();

        String newCustomerName = io.readName("Enter the new customer name (Current: " + orderToUpdate.getCustomerName());
        String newStateAbbr = io.readString("Enter the new state (Current: " + orderToUpdate.getState());
        String newProductType = io.readString("Enter the new state (Current: " + orderToUpdate.getProductType());

        int newArea = io.readInt("Enter the new state (Current: " + orderToUpdate.getArea(), 100, "");
        // Can cause overflow but should not
        int currArea = orderToUpdate.getArea().intValue();
        if(newArea == -1)
        {
            newArea = currArea;
        }


        boolean needsRecalc = false;

        if(!Objects.equals(currState, newStateAbbr) || !Objects.equals(currProductType, newProductType) || newArea != currArea)
        {
            needsRecalc = true;
        }

        if(!newCustomerName.equalsIgnoreCase("")){
            orderToUpdate.setCustomerName(newCustomerName);
        }
        if(!newStateAbbr.equalsIgnoreCase("")){
            orderToUpdate.setState(newStateAbbr);
        }
        if(!newProductType.equalsIgnoreCase("")){
            orderToUpdate.setProductType(newProductType);
        }
        if(newArea != currArea){
            //Convert new area to string and use to create new Big Decimal with new value
            orderToUpdate.setArea(new BigDecimal(Integer.toString(newArea)));
        }


    }

    public void displayUpdateSuccess() {
        io.print("Order updated successfully");
    }

    public Order getOrderToDelete() {
        io.print("");
        io.readString("Enter Order to delete:");
    }

    public void displayDeleteSuccess() {
        io.print("Order deleted successfully");
    }


    public void displayExit() {
        io.print("Exiting Floor Master");
    }
}
