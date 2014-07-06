package controllers;

import java.util.List;

import models.Combo;
import models.ComboDetail;
import models.Meal;
import models.Order;
import models.OrderDetail;
import play.mvc.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AdminCombos extends Controller {

	public static void adminCombo() {
		render();
	}

	public static void getCombos (Integer page, Integer rows) {
    	int start = (page-1) * rows;
    	long count = 0;
    	List<Combo> comboList = null;
    	if (page != null && rows != null) {
    		comboList = Combo.find("order by id desc").from(start).fetch(rows);
    		count = Combo.count();
    	} else {
    		comboList = Combo.find("order by id desc").from(start).fetch(rows);
    		count = Combo.count();
    	}
    	renderText(generateJson(comboList, count));
    }

	public static JsonObject generateJson(List<Combo> list, long count) {
    	JsonObject json = new JsonObject();
        json.addProperty("total", count);
        JsonArray array = new JsonArray();
        for(Combo combo: list) {
            JsonObject obj = getJsonObj(combo);
            array.add(obj);
        }
        json.add("rows", array);
        return json;
    }

	public static JsonObject getJsonObj(Combo combo) {
    	JsonObject obj = new JsonObject();
    	obj.addProperty("id", combo.id);
        obj.addProperty("name", combo.name);
        obj.addProperty("price", combo.price.price);
        obj.addProperty("discount", combo.price.discount);
        obj.addProperty("des", combo.des);
        if (combo.details != null) {
        	JsonArray array = new JsonArray();
        	for (ComboDetail detail: combo.details) {
        		JsonObject mealobj = new JsonObject();
        		mealobj.addProperty("mealid", detail.meal.id);
        		mealobj.addProperty("mealname", detail.meal.name);
        		mealobj.addProperty("mealtype", detail.meal.type.mealType);
        		mealobj.addProperty("mealprice", detail.meal.price.price);
        		mealobj.addProperty("mealdiscount", detail.meal.price.discount);
        		array.add(mealobj);
        	}
        	obj.add("meals", array);
        }
        return obj;
    }
	
	public static void getComboInfo(Long id) {
		Combo combo = Combo.findById(id);
		renderText(getJsonObj(combo));
		
	}
	
	public static void saveCombo(Combo combo, Long mealid[]) {
		Combo saveCombo = null;
		if (combo.id != null) {
			saveCombo = Combo.findById(combo.id);
			if (saveCombo != null) {
				saveCombo.name = combo.name;
				saveCombo.des = combo.des;
				saveCombo.price = combo.price;
				saveCombo.details.clear();
			}
		} else {
			combo.save();
		}
		
		if (mealid != null && mealid.length > 0) {
    		for(int i=0;i<mealid.length;i++) {
    			Meal meal = Meal.findById(mealid[i]);
    			if (meal != null) {
    				ComboDetail detail = new ComboDetail();
    				detail.meal = meal;
    				detail.num = 1;
    				detail.save();
    				if (saveCombo != null) {
    					saveCombo.addDetail(detail);
    				} else {
    					combo.addDetail(detail);
    				}
    			}
    		}
    	}
	}
	
    public static void getMeals() {
    	List<Meal> mealList = Meal.findAll();
    	JsonObject json = new JsonObject();
    	JsonArray array = new JsonArray();
    	if (mealList != null) {
    		if (mealList.size() > 0) {
    			for (Meal meal: mealList) {
    				JsonObject obj = new JsonObject();
    				obj.addProperty("id", meal.id);
    				obj.addProperty("type", meal.type.mealType);
    				obj.addProperty("name", meal.name);
    				obj.addProperty("price", meal.price.price);
    				obj.addProperty("discount", meal.price.discount);
    				array.add(obj);
    			}
    		}
    	}
        json.addProperty("total", array.size());
        json.add("rows", array);
        renderText(json);
    }
    
    public static void deleteCombo(Long[] id) {
    	for (int i=0;i<id.length;i++) {
    		Combo combo = Combo.findById(id[i]);
    		combo.delete();
    	}
    }
}
