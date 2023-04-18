package com.sabaton.wishmaster.repository;

import com.sabaton.wishmaster.model.Wishlist;
import com.sabaton.wishmaster.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.sabaton.wishmaster.model.Item;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

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

    public void addItem(Item item){

        String ADDITEM_QUERY = "INSERT INTO wishmaster.item(title, link) WHERE wishmaster.item.wishlistID = ?";
        ConnectionManager connectionManager = new ConnectionManager();
        try{
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement statement = connection.prepareStatement(ADDITEM_QUERY);
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getLink());
            statement.executeQuery();


        } catch (SQLException e){
            System.out.println("Kunne ikke oprette ny ønske");
            e.printStackTrace();

        }
    }

    public void updateItem(Item item){

        String UPDATE_QUERY = "UPDATE item SET title = =, link = ? WHERE id = ?, wishlistID=?";
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            String title = item.getTitle();
            String link = item.getLink();
            int id = item.getId();
            int wishlistID = item.getWishlistID();
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, link);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, wishlistID);

            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Kunne ikke opdatere produkt");
            e.printStackTrace();
        }

    }

    public void deleteById(int id, int wishlistID){
        String DELETE_QUERY = "DELETE FROM item WHERE id=?, wishlistID=?";
        ConnectionManager connectionManager = new ConnectionManager();
        try{
            Connection connection = connectionManager.getConnection(DB_URL, UID, PWD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.executeUpdate();


        } catch (SQLException e){
            System.out.println("Kan ikke slette item");
            e.printStackTrace();
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
}
