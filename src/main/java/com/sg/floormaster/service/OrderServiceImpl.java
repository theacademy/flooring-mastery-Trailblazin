package com.sg.floormaster.service;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dao.OrderDao;
import com.sg.floormaster.dao.ProductDao;
import com.sg.floormaster.dao.TaxDao;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle David Rudy
 */
@Component
public class OrderServiceImpl implements OrderService {
    
    private TaxDao taxDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(TaxDao taxDao, ProductDao productDao, OrderDao orderDao) {
        this.taxDao = taxDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @Override
    public int getNextOrderNumber() {
        return orderDao.getLargestOrderNumber();
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void loadOrders() throws PersistenceException {
        orderDao.loadOrders();
    }
    public void loadTaxes() throws PersistenceException {
        taxDao.loadTaxesFromFile();
    }
    public void loadProducts() throws PersistenceException {
        productDao.loadProductsFromFile();
    }


    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }
    public Map<Integer, Order> getAllOrders() {
        return orderDao.getAllOrders();
    }


    @Override
    public void removeOrder(Order order) {
        orderDao.removeOrder(order);
    }


    @Override
    public List<Tax> getTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getAllProducts();
    }
}
