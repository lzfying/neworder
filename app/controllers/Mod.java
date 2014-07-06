package controllers;

import java.util.List;

import models.Meal;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class Mod extends Controller{
	
//	 	@Before
//	    static void addUser() {
//	 		
//	        User user = connected();
//	        if(user != null) {
//	            renderArgs.put("user", user);
//	        }
//	    }
	    
	    static User connected() {
	        if(renderArgs.get("user") != null) {
	            return renderArgs.get("user", User.class);
	        }
	        String username = session.get("user");
	        if(username != null) {
	        	System.out.println("cccc  "+User.find("byUsername", username).first());
	            return User.find("byUsername", username).first();
	        } 
	        return null;
	    }
	
	public static void index() {
		
		  User user = connected();
	        if(user != null) {
	            renderArgs.put("user", user);
	        }
    	//List<Meal> meals = Meal.all().fetch(0, 10);
    	
        //render(meals);
		System.out.println("*******");
        render();
    }
	
	public static void register(){
		render();
	}
	
	public static void registerUser(String adminname,String adminpass,String email){
		System.out.println("122333userName ::"+adminname);
		//System.out.println("password ::"+password);
		
		User user = new User(adminname, adminpass, adminname, email);
		user.save();
		session.put("user", adminname);
		renderArgs.put("user", user);
		
	}

	
	public static void login(){
		render();
	}
	public static void loginUser(){
		render();
	}
	
	public static void logout() {
        session.clear();
        index();
    }
}
