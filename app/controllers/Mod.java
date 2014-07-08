package controllers;

import java.util.List;

import models.Meal;
import play.mvc.Controller;

public class Mod extends Controller{
	
	public static void index() {
    	List<Meal> meals = Meal.all().fetch(0, 10);
        render(meals);
    }

}
