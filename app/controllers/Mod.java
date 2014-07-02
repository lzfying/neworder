package controllers;

import java.util.List;

import models.Meal;
import play.mvc.Controller;

public class Mod extends Controller{
	
	public static void index() {
    	List<Meal> meals = Meal.all().fetch(0, 10);
    	 renderArgs.put("user", "游客");
        render(meals);
    }
	
	public static void register(){
		render();
	}

}
