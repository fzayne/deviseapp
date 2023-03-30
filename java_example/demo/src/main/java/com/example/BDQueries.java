package com.example;

import java.sql.*;

import com.example.main.type_conversion;

public class BDQueries {
    static Connection myConnection;
    BDQueries(){
        try{
            String dburl="jdbc:mysql://localhost:3306/mydb";
            String username="fzayne";
            String passwd="strongpassword";
            myConnection=DriverManager.getConnection(dburl,username,passwd);
           }catch(Exception e){
            System.out.println(e.getMessage());
           }
    }
    public static ResultSet query(String query) throws SQLException{
        Statement statement=myConnection.createStatement();
        ResultSet myResultSet=statement.executeQuery(query);
        return myResultSet;
    }
    public static void InsertIntoDB(double monnaie,type_conversion type){
        try{
        String url="https://v6.exchangerate-api.com/v6/6f1fa3f107b73889cf5c1651/pair/";
        Date cour_date;
        Double cours,monnaie_converti;
        if(type==type_conversion.dinar_euro){
            type=type_conversion.dinar_euro;
            url=url+"DZD/EUR";
            
        }else {
            url=url+"EUR/DZD";
            type=type_conversion.euro_dinar;
            
        }
        if(UrlConnection.getUrlContents(url)){
            cours=UrlConnection.rate; 
           cour_date=UrlConnection.date;
           }else{
            ResultSet result=query("SELECT lecours,date_cours from conversion WHERE conversion_type='"+type.toString()+"' ORDER BY date_cours DESC LIMIT 1");
            result.next();
            cours=result.getDouble("lecours");
            cour_date=result.getDate("date_cours");
           }
            monnaie_converti = monnaie*cours;
        String query="INSERT INTO conversion VALUES (id,?,curdate(),?,?,?,?)";
            PreparedStatement stm=myConnection.prepareStatement(query);
            stm.setString(1, type.toString());
            stm.setDouble(2, monnaie);
            stm.setDouble(3, cours);
            stm.setDate(4, cour_date);
            stm.setDouble(5, monnaie_converti);
            
    
           stm.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
           }
    }
}
