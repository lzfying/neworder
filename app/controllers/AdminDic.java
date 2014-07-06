package controllers;

import java.util.List;

import models.DicArea;
import play.mvc.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AdminDic extends Controller {
	
	
	public static void getAreaInfo() {
		List<DicArea> areaList = DicArea.findAll();
		
		JsonArray array = new JsonArray();
		
		for (DicArea area: areaList) {
			JsonObject obj = new JsonObject();
			obj.addProperty("code", area.code);
			obj.addProperty("name", area.name);
			array.add(obj);
		}
		
		renderText(array.toString());
	}
	
}
