package com.example;

import java.net.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import com.google.gson.*;
public class UrlConnection {
 /*    public static void main(String[] args)  
  {
    String url="https://v6.exchangerate-api.com/v6/6f1fa3f107b73889cf5c1651/pair/EUR/DZD";  
    System.out.println(getUrlContents(url));  
    
  }  
  */
  static Date date;
  static double rate;
  public static boolean getUrlContents(String theUrl)  
  {  
   // StringBuilder content = new StringBuilder();  
  // Use try and catch to avoid the exceptions  
  //String req_result="";
 // double rate=0;
    try  
    {  
      URL url = new URL(theUrl); // creating a url object  
      HttpURLConnection request = (HttpURLConnection) url.openConnection(); // creating a urlconnection object  
      request.connect();

      JsonParser jp = new JsonParser();
      JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
      JsonObject jsonobj = root.getAsJsonObject();
      System.out.println(jsonobj.get("result").getAsString());
       rate = jsonobj.get("conversion_rate").getAsDouble();
      String date_str=jsonobj.get("time_last_update_utc").getAsString();

      SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
      java.util.Date long_date=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").parse(date_str);
      System.out.println(long_date);
      String date_formatted=formater.format(long_date);
      long_date=formater.parse(date_formatted);
      date= new Date(long_date.getTime());
     // System.out.println(date);
     // System.out.println(rate);
      // wrapping the urlconnection in a bufferedreader  
     /* * BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)urlConnection.getContent()));  
      String line;  
      // reading from the urlconnection using the bufferedreader  
      while ((line = bufferedReader.readLine()) != null)  
      {  
        content.append(line + "\n");  
      }  
      bufferedReader.close(); 
      */
      return true;
    }  
    catch(Exception e)  
    {  
      e.printStackTrace();  
      return false;
    }  
      
   
  }  
}  

