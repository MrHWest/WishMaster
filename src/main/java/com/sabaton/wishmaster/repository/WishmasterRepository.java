package com.sabaton.wishmaster.repository;

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
    private String DB_UID;

    @Value("${spring.datasource.password}")
    private String Password;

    public List<Item> getAll(){
        List<Item> itemList = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection(DB_URL, DB_UID, Password);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM wishmaster.item";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String link = resultSet.getString(3);
                Item item = new Item(id, title, link);
                itemList.add(item);
                System.out.println(item);
            }
        }
        catch(SQLException e){
            System.out.println("Could not query Database");
            e.printStackTrace();
        }
        return itemList;
    }

    public void addItem(Item item){
        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, DB_UID, Password);
            final String CREATE_QUERY = "INSERT INTO item(title, link) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getLink());

            preparedStatement.executeUPdate();

        } catch (SQLException e){
            System.out.println("Could not create item");
            e.printStackTrace();
        }
        return itemList;
    }

   public void updateItem(Item item){
        final String UPDATE_QUERY = "UPDATE item SET title = ?, link = ? WHERE id = ?";

        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, DB_UID, Password);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            String title = item.getTitle();
            String link = item.getLink();
            int id = item.getId();
            preparedStatement.setString(1, title);
            preparedStatement.setLink(2, link);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.prinln("Could not update product");
            e.printStackTrace();
        }
   }

   public Item findItemById(int id){
        final String FIND_QUERY = "SELECT * FROM item WHERE id = ?";
        Item item = new Item();
        item.setId(id);
        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, Password);
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            String title = resultSet.getString(2);
            String link = resultSet.getLink(3);
            item.setTitle(title);
            item.setLink(link);
        } catch (SQLException){
            System.out.println("Could not find item");
            e.printStackTrace();
        }
        System.out.println(item);
        return item;
   }

   public void deleteById(int id){
        final String DELETE_QUERY = "DELETE FROM item WHERE id = ?";

        try{
            Connection connection = ConnectionManager.getConnection(DB_URL, Password);
            PreparedStatement preparedStatement = connection.preparedStatement(DELETE_QUERY);
            PreparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Could not delete from item");
            e.printStackTrace();
        }
   }

}
