package com.dao;

import com.revature.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();
    Product getProductById(long id);
    Boolean addProduct (Product product);
    Boolean updateProduct (Product product);
    Boolean deleteProduct (Product product);

}
