package controllers;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import models.Combo;
import models.Meal;
import models.MealType;
import models.Order;
import models.OrderDetail;
import models.User;
import models.UserAddress;
import models.UserDetail;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Params;
import smscode.SendTemplateSMS;

public class Mod extends Controller{
	
//	 	@Before
//	    static void addUser() {
//	 		
//	        User user = connected();
//	        if(user != null) {
//	            renderArgs.put("user", user);
//	        }
//	    }
	
	static SendTemplateSMS sms = new SendTemplateSMS();
	
	public static	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
	    
	static User connected() {
	        if(renderArgs.get("user") != null) {
	            return renderArgs.get("user", User.class);
	        }
	        String username = session.get("user");
	        if(username != null) {
	        	
	            return User.find("byUsername", username).first();
	        } 
	        return null;
	    }
	
	public static void index(String from) {
		
		  User user = connected();
	        if(user != null) {
	            renderArgs.put("user", user);
	        }
	    MealType type = new MealType();
	    type.mealType=1;
	    
    	List<Meal> mainmeals = Meal.find("type.mealType= ?", 1).fetch(4);
    	
    	type.mealType=3;
    	List<Meal> tangmeals = Meal.find("type.mealType= ?", 3).fetch(4);
    	
    	
    	List<Combo> combos = Combo.all().fetch(0, 4);
    	
        render(mainmeals,tangmeals,combos);
		
       // render();
    }
	
	public static void register(){
		render();
	}
	
	public static void registerUser(String adminname,String adminpass,String email){
		
		User user = new User( adminname, adminpass, email);
		user.save();
		session.put("user", adminname);
		renderArgs.put("user", user);
		
	}

	
	public static void login(String adminname,String adminpass){
		
        User user = User.find("byUsernameAndPassword", adminname, adminpass).first();
        if(user != null) {
            session.put("user", user.username);
            flash.success("Welcome, " + user.username);
            
        }else{
        	//response.print(true);
        }
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
		 UserAddress addr = new UserAddress();
	        if(user != null) {
	            renderArgs.put("user", user);
	            
	            if(user.address!=null&&user.address.size()>0){
	            	List<UserAddress> ls = user.address;
	            	 for(UserAddress u:ls){
	 					if(u.defvalue.equals("Y")){
	 						addr=u;
	 					}
	 				}
	            }
			
	        }
		render(addr);
	}
	
	 /** 
     * double 乘法 
     * @param d1 
     * @param d2 
     * @return 
     */ 
	public static double mul(double d1,int d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.multiply(bd2).doubleValue(); 
    } 

	
	public static void order(String name) {
		
		String addr="",tel="",bak_tel="";
		
		int itemCount =0;
		
		
		
		itemCount= Integer.parseInt(params.get("itemCount")) ;
	       
	        String s = format.format(new Date());
	        addr=params.get("value_addr");
	        tel=params.get("value_tel");
	        bak_tel=params.get("value_tel_bk");
	        
	        User user = connected();
			 if(user == null) {
				 user = new User( tel,  "123456",  tel);
				 user.save();
		     }    
		
		
		Double total =0.0;
		Order order = new Order(connected(),s,addr,tel,bak_tel,"offline");
		for(int i=1;i<itemCount+1;i++){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.totalNum= Integer.parseInt(params.get("item_quantity_"+i));
			orderDetail.mealName=params.get("item_name_"+i);
			orderDetail.price=Double.valueOf(params.get("item_price_"+i));
			
			total=total+mul(Double.valueOf(params.get("item_price_"+i)),orderDetail.totalNum)      ;
			orderDetail.save();
			order.addOrderDetail(orderDetail);

		}
		order.orderPrice=total;
		order.user=user;
		order.save();
	
   
		profile(0,"0");
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
		  //  System.out.println("::::"+value);

		}
	}
	
	
	public static void profile(Integer page,String orderstate){
		User user = connected();
		page = page != null ? page : 1;
		if(user != null){
			renderArgs.put("user", user);
			List<Order> orders = Order.find("byUserAndOrderstate", user,orderstate).fetch(page, 5);
			
			render(orders,page);
			
		}else{
			
			index("");
		}
		
	}
	
	
	public static void personal(){
		User user = connected();
		if(user != null){
			renderArgs.put("user", user);
			render();
			
		}else{
			index("");
		}
		
	}
	
	public static void address(){

		User user = connected();
		if(user != null){
			renderArgs.put("user", user);
			render();
			
		}else{
			
			index("");
		}
		
		
	}
	
	public static void saveaddress(UserAddress userAddress){
		
		User user = connected();
	       
		if(user != null){
			renderArgs.put("user", user);
			Long addr_id=Long.parseLong(params.get("address_id")==null?"19999":params.get("address_id"));
			userAddress.phone=params.get("phone")==null?"":params.get("phone");
			userAddress.address=params.get("address")==null?"":params.get("address");
			userAddress.bakphone=params.get("bakphone")==null?"":params.get("bakphone");
			
			UserAddress address= UserAddress.findById(addr_id);
			if(address==null){
				user.address.add(userAddress);
				
			}else{
				user.address.remove(address);
				user.address.add(userAddress);
			}
			user.save();
		
			address();
			
		}else{
			
			index("");
		}
		
	}
	
	
	public static void setdefault(Long id){
		
		User user = connected();
		if(user != null){
			renderArgs.put("user", user);
			List<UserAddress> ls = user.address;
			for(UserAddress u:ls){
				if(u.defvalue.equals("Y")){
					u.defvalue="N";
					u.save();
				}
			}
			UserAddress userAddress = UserAddress.findById(id);
			userAddress.defvalue="Y";
			userAddress.save();
			address();
		}else{
			index("");
		}
		
		
		
	}
	
	
	public static void editaddress(Long id){
		User user = connected();
	       
		if(user != null){
			renderArgs.put("user", user);
			UserAddress userAddress = UserAddress.findById(id);
			render(userAddress);
			
		}else{
			
			index("");
		}
		
	}
	
	
	public static void deladdress(Long id){
		User user = connected();
	       
		if(user != null){
			renderArgs.put("user", user);
			for(int i=0;i<user.address.size();i++){
				UserAddress u = user.address.get(i);
				if(u.id==id){
					user.address.remove(u);
					
				}
				
			}
			user.save();
			
			address();
			
		}else{
			
			index("");
		}
		
	}
	
	
	public static void checkUser(String name){

		User user =User.find("byUsername", name).first();
		if(user!=null){
			response.print(false);
		}else{
			response.print(true);
			
		}
		
	}
	
	
	public static void savepersonal(String realname, int sex){
		
		User user = connected();
		if(user != null){
			//UserDetail useDetail = new UserDetail(realname,sex,user);
			UserDetail useDetail = user.userDetail;
			if(useDetail!=null){
				useDetail.realname=realname;
				useDetail.sex=sex;
			}else{
				useDetail = new UserDetail(realname,sex,user);
				
			}
			
			renderArgs.put("user", user);
			user.userDetail= useDetail;
			user.userDetail.save();
			personal();
		}else{
			
			index("");
		}
		
		
		
		
	}
	
	
	public static void checkUserLogin(String name,String password){

		User user = User.find("byUsernameAndPassword", name, password).first();
		if(user!=null){
			response.print(true);
		}else{
			response.print(false);
			
		}
		
	}
	
	
	
	
	public static void confirm(String tel){
		System.out.println("dddff"+tel);
		String randomnum= getFixLenthString(6);
		System.out.println("   GGGGG   "+randomnum);
		
		sms.resendSMSCode(tel, randomnum, "1");
			session.put("smscode", randomnum);
			
			render(tel);
		
			
			index("");
		
		
		
		
	}
	
	
	
	/* 
	 * 返回长度为【strLength】的随机数，在前面补0 
	 */  
	private static String getFixLenthString(int strLength) {  
	      
	    Random rm = new Random();  
	      
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	}  
	 
	 
	public static void sendSMS(String tel){
		
		String randomnum= getFixLenthString(6);
		System.out.println("   GGGGG "+randomnum);
		
		if(sms.resendSMSCode(tel, randomnum, "1")){
			session.put("smscode", randomnum);
			renderJSON("{su:true}");
			
		}else{
			renderJSON("{su:false}");
			
		}
		
	}
	
	public static void confirmSms(String code){
		String lcode = session.get("smscode")==null?"":session.get("smscode");
		if(code.equals(lcode)){
			
			renderJSON("{\"messages\":true}");
		}else{
			renderJSON("{\"messages\":false}");
			
		}
		
	}
	
	
	public static void previousPage(int startPosition){
		User user = connected();
	       
		if(user != null){
			renderArgs.put("user", user);
			int totalnum = Order.find("byUser", user).fetch().size();
			if(startPosition == 0) {  
	            startPosition = startPosition;  
	        }  
	        else {  
	            startPosition = startPosition - 1;  
	        }  

			
			 profile(startPosition,"0");
			/////
			
			
		}else{
			
			index("");
		}
		
		
	}
	
	
	public static void nextPage(int startPosition){
		
		User user = connected();
		if(user != null){
			int totalnum = Order.find("byUser", user).fetch().size(); 
	        if(startPosition >= totalnum/5) {  
	            startPosition = startPosition;  
	        }  
	        else {  
	            startPosition = startPosition + 1;  
	        }  
	        profile(startPosition,"0");
			
		}else{
			
			index("");
			
		}
		
		
	}
	
	
}
