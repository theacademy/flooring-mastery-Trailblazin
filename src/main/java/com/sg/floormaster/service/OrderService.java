package com.sg.floormaster.service;

import com.sg.floormaster.dto.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
@Component
public class OrderService {
    
    private BookDao dao;
    @Autowired
    public OrderService(BookDao dao) {
        this.dao = dao;
    }

    public Tax getBookByTitle(String title) {
        return dao.getBookByTitle(title);
    }
    
    public List<Tax> getAllBooks() {
        return dao.getAllBooks();
    }
    
    public Tax addBook(Tax Order) {
        return dao.addBook(Order);
    }
    
    public void updateBook(Tax Order) {
        dao.updateBook(Order);
    }
    
    public void deleteBookByTitle(String title) {
        dao.deleteBookByTitle(title);
    }
}
