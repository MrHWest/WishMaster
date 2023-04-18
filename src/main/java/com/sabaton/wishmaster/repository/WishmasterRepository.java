package com.sabaton.wishmaster.repository;

import com.sabaton.wishmaster.model.Wishlist;
import com.sabaton.wishmaster.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.sabaton.wishmaster.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishmasterRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;

    public ArrayList<Item> getAllItems() {
        System.out.println(DB_URL);

        String SELECT_QUERY = "SELECT * FROM wishmaster.item";
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement statement =  connection.prepareStatement(SELECT_QUERY);
            ResultSet result = statement.executeQuery();

            ArrayList<Item> items = new ArrayList<>();

            while(result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                String link = result.getString("link");
                int wishlistID = result.getInt("wishlistID");
                System.out.println(title + " " + link);
                items.add(new Item(id, title, link, wishlistID));
            }
            return items;


            /*ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(title, result.link));
            return items;*/


        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<Item> items = new ArrayList<>();
            return items;
        }
    }

    public ArrayList<Item> getItemsFromId(int wishlistId) {

        String SELECT_QUERY = "SELECT * FROM wishmaster.item WHERE wishlistID = ?";
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement statement =  connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, wishlistId);
            ResultSet result = statement.executeQuery();

            ArrayList<Item> items = new ArrayList<>();

            while(result.next()){
                int id = result.getInt("id");
                String title = result.getString("title");
                String link = result.getString("link");
                int wishlistID = result.getInt("wishlistID");
                System.out.println(title + " " + link);
                items.add(new Item(id, title, link, wishlistID));
            }
            return items;

        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<Item> items = new ArrayList<>();
            return items;
        }
    }

    public String getWishlistPassword(int wishlistId) {
        String SELECT_QUERY = "SELECT wishmaster.wishlist.password FROM wishmaster.wishlist WHERE ID = ?";
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement statement =  connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, wishlistId);
            ResultSet result = statement.executeQuery();

            String password = "";

            while(result.next()){
                password = result.getString("password");
            }
            return password;

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void createItem(String title, String link, int wishlistId) {
        String INSERT_QUERY = "INSERT INTO wishmaster.item (title, link, wishlistID) VALUES (?, ?, ?)";
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, title);
            statement.setString(2, link);
            statement.setInt(3, wishlistId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
