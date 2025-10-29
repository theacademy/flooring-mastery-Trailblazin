package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;

import java.util.List;

public interface TaxDao {
    void loadTaxesFromFile();
    List<Tax> getAllTaxes();
}
