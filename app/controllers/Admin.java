package controllers;
 
import play.*;
import play.mvc.*;
import play.data.validation.*;
 
import java.util.*;

import controllers.Secure.Security;
 
import models.*;
import models.AdminUser;
import models.Meal;
import models.MealDesc;

 
@With(Secure.class)
public class Admin extends Controller {
    
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
        	AdminUser user = AdminUser.find("byUserName", Security.connected()).first();
            renderArgs.put("user", user.username);
        }
    }
 
    public static void index() {
        List<Meal> meals = Meal.all().fetch(0, 10);
        render(meals);
    }
    
    public static void form(Long id) {
        if(id != null) {
        	Meal meal = Meal.findById(id);
            render(meal);
        }
        render();
    }
    
    public static void save(Long id, String name, String type, String type2,String type3,String url,String content) {
    	Meal meal;
    	MealDesc mealDesc;
        if(id == null) {
            // Create post
        	meal =new Meal();
        	meal.name= name;
        	meal.type = type;
        	meal.type2= type2;
        	meal.type3= type3;
        	meal.url= url;
        	
        	mealDesc = new MealDesc();
        	mealDesc.content = content;
        	mealDesc.save();
        	
        	meal.mealDesc = mealDesc;
        	
        } else {
            // Retrieve post
        	meal = Meal.findById(id);
        	meal.name= name;
        	meal.type = type;
        	meal.type2= type2;
        	meal.type3= type3;
        	meal.url= url;
        	
        	meal.mealDesc.content=content;
        	meal.mealDesc.save();
        	
        	
        }
        // Set tags list
      
        // Validate
//        validation.valid(post);
//        if(validation.hasErrors()) {
//            render("@form", post);
//        }
        // Save
        meal.save();
        index();
    }
    
    public static void orderList(){
    	
    	List<Order> orders = Order.all().fetch(0, 50);
    	render(orders);
    	
    }
    
    
    
}
