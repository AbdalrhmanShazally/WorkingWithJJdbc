package com.revature;

import com.dao.ConnectionUtility;
import com.dao.ProductDAOImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "test Connection" );
        try {
            System.out.println(ConnectionUtility.getConnection().isValid(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Test get all products:
        ProductDAOImpl productDAO = new ProductDAOImpl();
        List<Product> productList = productDAO.getAllProducts();

        //print all products:
        System.out.println("Product id "+ "Product Name         "+ "Product Description ");
        for (Product p :productList) {
                System.out.println(p.getId()+ "             "+ p.getName()+"             "+ p.getDescription());
        }


        //test get product by id:
        Product product = new Product();
        product = productDAO.getProductById(2);
        System.out.println(" getting Product No "+product.getId() + "#");
        System.out.println("Product id "+ "Product Name         "+ "Product Description ");
        System.out.println(product.getId()+ "             "+ product.getName()+"             "+ product.getDescription());

        //add new item
         boolean wasAdded = productDAO.addProduct(new Product("S21 ultra", "Samsung"));
         System.out.println("adding new Item Success = "+wasAdded);
        //print all products:
         System.out.println("Product id "+ "Product Name         "+ "Product Description ");
         for (Product p :productList) {
            System.out.println(p.getId()+ "             "+ p.getName()+"             "+ p.getDescription());
        }

        //System.out.println("Update products set name =  \'?\' where id = ?");
        //Update Item 2:
        product.setName("Tower PC");
        boolean isUpdated = productDAO.updateProduct(product);
        System.out.println("Updated Item NO "+ product.getId() + "# " + isUpdated);

        productList = productDAO.getAllProducts();

        //print all products:
        System.out.println("Product id "+ "Product Name         "+ "Product Description ");
        for (Product p :productList) {
            System.out.println(p.getId()+ "             "+ p.getName()+"             "+ p.getDescription());
        }


        //Test Delete
        product.setId(5);
        boolean isDeleted = productDAO.deleteProduct(product);
        System.out.println("Deleted Item NO "+ product.getId() + "# " + isDeleted);

        //print all products:
        System.out.println("Product id "+ "Product Name         "+ "Product Description ");
        for (Product p :productList) {
            System.out.println(p.getId()+ "             "+ p.getName()+"             "+ p.getDescription());
        }
    }
}
