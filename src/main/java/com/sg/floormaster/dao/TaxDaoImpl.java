package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaxDaoImpl implements  TaxDao{

    private Map<String,Tax> taxes = new HashMap<>();
    private final String ORDER_FILE = "data/taxes.txt";
    private final String DELIMITER = ",";
    @Override
    public void loadTaxesFromFile() {

    }

    @Override
    //TODO: Consider caching
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }
}
