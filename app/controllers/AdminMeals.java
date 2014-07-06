package controllers;

import java.util.List;

import models.DicMealType;
import models.Meal;
import play.mvc.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AdminMeals extends Controller {
	
	public static void adminMeal() {
		render();
	}

	public static void getMeals(Integer page, Integer rows) {
    	int start = (page-1) * rows;
    	long count = 0;
    	List<Meal> mealList = null;
    	if (page != null && rows != null) {
    		mealList = Meal.find("order by id desc").from(start).fetch(rows);
    		count = Meal.count();
    	} else {
    		mealList = Meal.find("order by id desc").from(start).fetch(rows);
    		count = Meal.count();
    	}
    	renderText(generateJson(mealList, count));
    }

	public static JsonObject generateJson(List<Meal> list, long count) {
    	JsonObject json = new JsonObject();
        json.addProperty("total", count);
        JsonArray array = new JsonArray();
        for(Meal meal: list) {
            JsonObject obj = getMealJsonObj(meal);
            array.add(obj);
        }
        json.add("rows", array);
        return json;
    }

	public static void getMealInfo(Long id) {
		Meal meal = Meal.findById(id);
		JsonObject obj = getMealJsonObj(meal);
		obj.remove("type");
		obj.addProperty("type", meal.type.mealType);
		renderText(obj);
	}

	public static JsonObject getMealJsonObj(Meal meal) {
    	JsonObject obj = new JsonObject();
    	obj.addProperty("id", meal.id);
        obj.addProperty("name", meal.name);
        obj.addProperty("price", meal.price.price);
        obj.addProperty("discount", meal.price.discount);
        obj.addProperty("des", meal.des);
        if (meal.type != null) {
        	DicMealType type = DicMealType.find("type=?", meal.type.mealType).first();
        	obj.addProperty("type", type.name);
        }
        return obj;
    }
	
	public static void saveMeal(Meal meal) {
		meal.save();
	}

	public static void getMealType() {
		List<DicMealType> typelist = DicMealType.findAll();
		JsonArray array = new JsonArray();
		
		for (DicMealType type: typelist) {
			JsonObject obj = new JsonObject();
			obj.addProperty("text", type.name);
			obj.addProperty("value", type.type);
			array.add(obj);
		}
//		System.out.println(array.toString());
		renderText(array);
	}
	
	public static void deleteMeal(Long[] id) {
		for (int i=0;i<id.length;i++) {
    		Meal meal = Meal.findById(id[i]);
    		meal.delete();
    	}
	}
	
}
