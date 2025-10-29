package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Product;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Component
public class ProductDaoImpl implements  ProductDao{
    private Map<String, Product> products = new HashMap<>();
    private final String PRODUCT_FILE = "data/products.txt";
    private String DELIMITER = ",";

    @Override
    public Product unmarshallProduct(String productsAsText){
        String[] productTokens = productsAsText.split(DELIMITER);

        String productType = productTokens[0];


        BigDecimal matCostPerSqFt = new BigDecimal(productTokens[1]);

        BigDecimal labourCostPerSqFt = new BigDecimal(productTokens[2]);

        // We have now created a Product Obj! Return it!
        return new Product(productType,matCostPerSqFt,labourCostPerSqFt);
    }

    @Override
    public void loadProductsFromFile() throws PersistenceException {
        this.products = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(PRODUCT_FILE)
            ));
        }
        catch (FileNotFoundException e){
            throw new PersistenceException("-_-, Could not load product data.");
        }
        //Current line from file
        String currentLine;
        //order decoded from current line
        Product currentProduct;
        //Skip Categories line
        scanner.nextLine();
        //Iterate through roster file and decode line into order
        //Continue whilst lines exist in the file
        while(scanner.hasNextLine()){
            //Get next line and unmarshall it to fit a order object
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);

            //Use order id as key for orders collection
            products.put(currentProduct.getProductType(), currentProduct);
        }
        //Close scanner once no longer required
        scanner.close();
    }

    @Override
    //TODO: Consider caching
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
