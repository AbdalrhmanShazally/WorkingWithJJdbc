package com.dao;

import com.revature.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
    @Override
    public List<Product> getAllProducts() {
    List<Product> listOfProduct = new ArrayList<>();
        try (Connection conn = ConnectionUtility.getConnection()) {
            String sql = "select * from products ;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");

                Product p = new Product();
                p.setId(id);
                p.setName(name);
                p.setDescription(desc);

                listOfProduct.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listOfProduct;
    }

    @Override
    public Product getProductById(long prodcutId) {
        Product product = new Product();

        try (Connection conn = ConnectionUtility.getConnection();) {
            String sql = "SELECT * FROM PRODUCTS WHERE ID = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,prodcutId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public Boolean addProduct(Product product) {
        boolean isAdded = false;
        //declare Connections
        try (Connection conn = ConnectionUtility.getConnection()) {
            //Create Prepared Statement:
            String sql = "INSERT INTO PRODUCTS (name, description) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
           // ps.setLong(1,product.getId());
            ps.setString(1,product.getName());
            ps.setString(2,product.getDescription());

           int rowsAffected = ps.executeUpdate();
           if (rowsAffected > 0){
               isAdded = true;
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isAdded;
    }

    @Override
    public Boolean updateProduct(Product product) {
        boolean isUpdated = false;

        //Create Connection:
        try (Connection conn = ConnectionUtility.getConnection()) {
            //Create Statement:
            //String sql = "Update products set name =  \'"+"?"+"\' where id = ?" ;
            String sql = "Update products set name = ? where id = ?" ;
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1,product.getName());
           ps.setLong(2,product.getId());

           int isUpdate = ps.executeUpdate();

           if(isUpdate > 0){
               isUpdated = true;
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  isUpdated;
    }

    @Override
    public Boolean deleteProduct(Product product) {
        boolean isDeleted = false;

        //Create Connection:
        try (Connection conn = ConnectionUtility.getConnection()){

            //Create Statement:
            String sql = "delete from products where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,product.getId());

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0){
                isDeleted = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isDeleted;
    }
}
