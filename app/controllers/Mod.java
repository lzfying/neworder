package controllers;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




import java.util.Set;


import models.Meal;
import models.Order;
import models.OrderDetail;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Params;

public class Mod extends Controller{
	
//	 	@Before
//	    static void addUser() {
//	 		
//	        User user = connected();
//	        if(user != null) {
//	            renderArgs.put("user", user);
//	        }
//	    }
	
	public static	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
	    
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
	
	public static void index(String from) {
		System.out.println("this is "+from);
		
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
        index("");
    }
	
	public static void cart(){
		 User user = connected();
	        if(user != null) {
	            renderArgs.put("user", user);
	        }
		render();
	}
	
	public static void order(String name) {
		
		String addr="",tel="",bak_tel="";
		
		int itemCount =0;
		
		itemCount= Integer.parseInt(params.get("itemCount")) ;
	       
	        String s = format.format(new Date());
	        addr=params.get("value_addr");
	        tel=params.get("value_tel");
	        bak_tel=params.get("value_tel_bk");
		
		Order order = new Order(connected(),s,addr,tel,bak_tel,"offline");
		for(int i=1;i<itemCount+1;i++){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.totalNum= Integer.parseInt(params.get("item_quantity_"+i));
			orderDetail.mealName=params.get("item_name_"+i);
			orderDetail.price=Double.valueOf(params.get("item_price_"+i));
			order.addOrderDetail(orderDetail);

		}
		
		
		order.save();
	
   
        index("");
    }
	
	public static void workByEntry(Map<Object, Object> map) {
        Set<java.util.Map.Entry<Object, Object>> set = map.entrySet();
        for (Iterator<Map.Entry<Object, Object>> it = set.iterator(); it.hasNext();) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
            System.out.println(entry.getKey() + "--->" + map.get(entry.getKey()));
        }
    }

	
	public static void vistMap(Map map){
		
		Iterator<String> iter = map.keySet().iterator();
		String key="",value="";

		while (iter.hasNext()) {

		    key = iter.next();

		    value = (String) map.get(key);
		    System.out.println("::::"+key);
		    System.out.println("::::"+value);

		}
	}
	
	
	
	

}
