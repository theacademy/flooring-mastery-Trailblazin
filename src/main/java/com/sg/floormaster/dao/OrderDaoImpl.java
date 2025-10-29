package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Seun :D
 */
@Component
public class OrderDaoImpl implements OrderDao {

    private Map<Integer, Order> orders;
    private final String ORDER_FILE = "data/orders_06012013.txt";
    private final String DELIMITER = ",";

    private static int largestOrderNumber = 0;

    @Override
    public Order unmarshallOrder(String orderAsText){

        String[] orderTokens = orderAsText.split(DELIMITER);

        // The order Id is in index 0 of the array.
        int orderId = Integer.parseInt(orderTokens[0]);

        // Which we can then use to create a new order object to satisfy
        // the requirements of the order constructor.
        // Index 1 - Customer Name
        String customerName = orderTokens[1];

        // Index 2 - State
        String state = orderTokens[2];

        // Index 3  - Tax Rate
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);

        // Index 4 - Product Type
        String productType = orderTokens[4];

        // Index 5 - Area
        BigDecimal area = new BigDecimal(orderTokens[5]);

        // Index 6 - Material Cost (SqFt)
        BigDecimal matCostPerSqFt = new BigDecimal(orderTokens[6]);
        // Index 7 - Labor Cost (SqFt)
        BigDecimal laborCostPerSqFt = new BigDecimal(orderTokens[7]);
        // Index 8 - Material Cost Total
        BigDecimal matCostTotal = new BigDecimal(orderTokens[8]);
        // Index 9 - Labor Cost Total
        BigDecimal laborCostTotal = new BigDecimal(orderTokens[9]);
        // Index 10 - Tax
        BigDecimal tax = new BigDecimal(orderTokens[10]);
        // Index 11 - Total
        BigDecimal total = new BigDecimal(orderTokens[11]);

        // We have now created a order! Return it!
        return new Order(orderId, LocalDate.of(2013, 6, 1), customerName,
                state,productType,area, matCostPerSqFt, laborCostPerSqFt, matCostTotal, laborCostTotal, tax, taxRate, total);
    }
    
    @Override
    public void loadOrders() throws PersistenceException {
        this.orders = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(ORDER_FILE)
            ));
        }
        catch (FileNotFoundException e){
            throw new PersistenceException("-_-, Could not load order data.");
        }
        //Current line from file
        String currentLine;
        //order decoded from current line
        Order currentOrder;
        //Skip Categories line
        scanner.nextLine();
        //Iterate through roster file and decode line into order
        //Continue whilse lines exist in the file
        while(scanner.hasNextLine()){
            //Get next line and unmarshall it to fit a order object
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);

            //Use order id as key for orders collection
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        //Close scanner once no longer required
        scanner.close();

        largestOrderNumber = orders.size();
    }

    @Override
    public int getLargestOrderNumber(){
        return largestOrderNumber;
    }

    public void setLargestOrderNumber(){
        largestOrderNumber++;
    }
    @Override
    public Order getOrder(LocalDate date, int orderNo) {
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
        if (orderMatch != null && orderMatch.getOrderDate() == date) {
            return orderMatch;
        }
        // Should never happen,  we can't be using the same ID for multiple dates
        else {
            return null;
        }
    }
    @Override
    public List<Order> getOrdersForDate(LocalDate userDate) {
        List<Order> ordersList = new ArrayList<>();
        for (Order order : orders.values()){
            if(order.getOrderDate() == userDate){
                ordersList.add(order);
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }
    @Override
    public Map<Integer, Order> getAllOrders() {
        return this.orders;
    }

    @Override
    public void addOrder(Order order) {
        orders.put(order.getOrderNumber(), order);
    }

    @Override
    public void removeOrder(Order order) {
        orders.remove(order.getOrderNumber());
    }
}
