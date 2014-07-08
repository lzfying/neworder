package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import models.*;

public class Application extends Controller {

    public static void index() {
    	List<Meal> meals = Meal.all().fetch(0, 10);
        render(meals);
    }
    
    
    public static void index2() {
    	List<Meal> meals = Meal.all().fetch(0, 10);
        render(meals);
    }

    
    public static void login(String username, String password) {
        User user = User.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            session.put("user", user.username);
            flash.success("Welcome, " + user.username);
            //Hotels.index();         
        }
        // Oops
       // flash.put("username", username);
       // flash.error("Login failed");
        index();
    }
    
    
    public static void doOrder(){
    	
    	orderForm();
    }
    
    public static void orderForm(){
    	List<Meal> meals = Meal.all().fetch(0, 10);
        render(meals);
    	
    }
    
    public static void saveOrder(Long id ,String name ,Date orderTime ){
    	render();
    }
    
    
    public static void confirmOrder(String jsonData){
    	String[] arr =  jsonData.split("[*]");
    	for(int i=0;i<arr.length;i++){
    		Map m = parseData(arr[i]);
    		System.out.println(i+ "row  "+m);
    	}
    	
//    	Order order = new Order("no1234566","aaa");
//    	
//    	
//    	
//    	OrderDetail orderDetail = new OrderDetail();
//    	orderDetail.mealId= 9;
//    	orderDetail.num= 1;
//    	orderDetail.totalNum = 100;
//    	
//    	OrderDetail orderDetail2 = new OrderDetail();
//    	orderDetail2.mealId= 10;
//    	orderDetail2.num= 2;
//    	orderDetail2.totalNum= 120;
//    	
//    	
//    	order.addOrderDetail(orderDetail);
//    	order.addOrderDetail(orderDetail2);
//    	order.save();
    	
    
    	
    }
    
    private static Map<String, String> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType()); 
        return map;
    }
    
    
    
    
    
}