package controllers;
 
import java.util.List;

import models.AdminUser;
import models.FuncTreeNode;
import models.Meal;
import models.MealDesc;
import models.MealType;
import models.Order;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import controllers.Secure.Security;

 
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
    
    public static void save(Long id, String name, MealType type, String type2,String type3,String url,String content) {
    	Meal meal;
    	MealDesc mealDesc;
        if(id == null) {
            // Create post
        	meal =new Meal();
        	meal.name= name;
        	meal.type = type;
        	meal.url= url;
        	
        	mealDesc = new MealDesc();
        	mealDesc.content = content;
        	mealDesc.save();
        	
        } else {
            // Retrieve post
        	meal = Meal.findById(id);
        	meal.name= name;
        	meal.type = type;
        	meal.url= url;
        	
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
    
    public static void getFuncTreeNode() {
        FuncTreeNode root = FuncTreeNode.find("byParent", Integer.valueOf(0)).first();
        JsonArray rootArray = new JsonArray();
        JsonObject jsroot = new JsonObject();
        jsroot.addProperty("id", root.id);
        jsroot.addProperty("text", root.label);

        List<FuncTreeNode> secondTreeNodes = FuncTreeNode.find("byParent", Integer.valueOf(root.getId().intValue())).fetch();
        JsonArray jsarray = new JsonArray();
        for (FuncTreeNode node: secondTreeNodes) {
            JsonObject obj = new JsonObject();
            obj.addProperty("id", node.id);
            obj.addProperty("text", node.label);
            obj.addProperty("action", node.action);
            obj.addProperty("level", node.level);
            jsarray.add(obj);
        }

        jsroot.add("children", jsarray);
        rootArray.add(jsroot);
        response.setHeader("charset", "UTF-8");
        renderText(rootArray);
    }
    
    public static void admin() {
        render();
    }
}
