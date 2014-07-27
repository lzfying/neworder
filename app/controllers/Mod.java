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
	        
	        String userid = session.get("userid");
	       
	        if(userid != null) {
	            return User.findById(Long.valueOf(userid));
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
	 
	    
    	List<Meal> mainmeals = Meal.find("type.mealType= ? and ishide=?", 1,0).fetch(4);
    	
    	
    	List<Meal> tangmeals = Meal.find("type.mealType= ? and ishide=?", 3,0).fetch(4);
    	
    	
    	List<Combo> combos = Combo.all().fetch(0, 4);
    	
        render(mainmeals,tangmeals,combos,from);
		
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
        index("clear");
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
		
		String addr="",tel="",bak_tel="",receiver_name="",receiver_other="";
		
		int itemCount =0;
		
		
		
		itemCount= Integer.parseInt(params.get("itemCount")) ;
	       
	        String s = format.format(new Date());
	        addr=params.get("value_addr");
	        tel=params.get("value_tel");
	        bak_tel=params.get("value_tel_bk");
	        receiver_name=params.get("value_receiver_name");
	        receiver_other=params.get("value_receiver_other");
	        
	        User user = connected();
			 if(user == null) {
				 user = new User( tel,  tel,  tel);
				 user.save();
				 session.put("userid", user.id);
		     } else{
		    	 String addrid=params.get("value_addr_id") ;
		    	 
		    	 if(addrid==null||addrid.equals("")){
		    		 UserAddress userAddress =new UserAddress();
		    		 userAddress.address=addr;
			    	 userAddress.phone=tel;
			    	 userAddress.bakphone=bak_tel;
			    	 userAddress.defvalue="Y";
			    	 user.address.add(userAddress);
			    	 user.save();
		    	 }
	
		     }   
		
		
		Double total =0.0;
		Order order = new Order(connected(),s,addr,tel,receiver_other,"offline");
		order.receiver_name=receiver_name;
		order.bak_tel=bak_tel;
		for(int i=1;i<itemCount+1;i++){
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.totalNum= Integer.parseInt(params.get("item_quantity_"+i));
			orderDetail.mealName=params.get("item_name_"+i);
			orderDetail.price=Double.valueOf(params.get("item_price_"+i));
			orderDetail.url=params.get("item_url_"+i);
			
			total=total+mul(Double.valueOf(params.get("item_price_"+i)),orderDetail.totalNum)      ;
			orderDetail.save();
			order.addOrderDetail(orderDetail);

		}
		order.orderPrice=total;
		order.user=user;
		order.save();
	
   
		profile(0,"0");
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
		
		List<Order> orders=null ;
		if(user != null){
			renderArgs.put("user", user);
			if(orderstate.equals("3")){
				
				orders = Order.find("byUserAndOrderstate", user,orderstate).fetch(page, 5);
				//hisprofile(orders,page);
				renderTemplate("Mod/hisprofile.html", orders,page);
			}else{
				
				orders = Order.find("user=? and orderstate!=? order by date desc", user,"3").fetch(page, 5);
				render(orders,page);
			}
			
			
		}else{
			
			index("");
		}
		
	}
	
	
	public static void hisprofile(List<Order> orders,Integer page){
		render(orders,page);
		
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
				userAddress.defvalue=address.defvalue;
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
