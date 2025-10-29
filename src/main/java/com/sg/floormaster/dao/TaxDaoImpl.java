package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Tax;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Component
public class TaxDaoImpl implements  TaxDao{

    private Map<String,Tax> taxes = new HashMap<>();
    private final String TAXES_FILE = "data/taxes.txt";
    private final String DELIMITER = ",";
    @Override
    public void loadTaxesFromFile() throws PersistenceException {
        this.taxes = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(TAXES_FILE)
            ));
        }
        catch (FileNotFoundException e){
            throw new PersistenceException("-_-, Could not load taxes data.");
        }
        //Current line from file
        String currentLine;
        //order decoded from current line
        Tax currentTax;
        //Skip Categories line
        scanner.nextLine();
        //Iterate through roster file and decode line into order
        //Continue whilst lines exist in the file
        while(scanner.hasNextLine()){
            //Get next line and unmarshall it to fit a order object
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);

            //Use order id as key for orders collection
            taxes.put(currentTax.getStateAbbr(), currentTax);
        }

        //Close scanner once no longer required
        scanner.close();
    }
    @Override
    public Tax unmarshallTax(String taxAsText){

        String[] taxTokens = taxAsText.split(DELIMITER);

        // The State Abbr is in index 0 of the array.
        String stateAbbr = taxTokens[0];

        // Which we can then use to create a new Tax object to satisfy
        // the requirements of the Tax constructor.
        // Index 1 - State Name
        String stateName = taxTokens[1];

        // Index 3  - Tax Rate
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);

        // We have now created a Tax Obj! Return it!
        return new Tax(stateAbbr,stateName,taxRate);
    }

    @Override
    //TODO: Consider caching
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }
}
