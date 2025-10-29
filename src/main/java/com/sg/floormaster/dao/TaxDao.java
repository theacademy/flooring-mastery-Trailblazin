package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Tax;

import java.util.List;

public interface TaxDao {
    void loadTaxesFromFile() throws PersistenceException;



    Tax unmarshallTax(String taxAsText);

    List<Tax> getAllTaxes();
}
