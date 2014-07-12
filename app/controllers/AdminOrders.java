package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Combo;
import models.Meal;
import models.Order;
import models.OrderDetail;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.With;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Check("admin")
@With(Secure.class)
public class AdminOrders extends Controller {
    
    public static void adminOrder() {
        render();
    }

    public static void getOrders(Integer page, Integer rows, String startdate, String enddate) throws ParseException {
    	int start = (page-1) * rows;
    	List<Order> orderList;
    	long count = 0;
    	if (startdate !=null && enddate != null) {
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    		Date sDate = sf.parse(startdate);
    		Date eDate = sf.parse(enddate);
    		orderList = Order.find("date >= ? and date <= ? order by date desc",sDate, eDate).from(start).fetch(rows);
    		count = orderList.size();
    	} else {
    		orderList = Order.find("order by date desc").from(start).fetch(rows);
    		count = Order.count();
    	}
        
        renderText(generateJson(orderList, count));
    }

    public static void editOrderDetail(long id) {
        Order order = Order.findById(Long.valueOf(id));
        render(order);
    }

    public static JsonObject generateJson(List<Order> list, long count) {
        JsonObject json = new JsonObject();
        json.addProperty("total", count);
        JsonArray array = new JsonArray();
        for(Order order: list) {
            JsonObject obj = getOrderJsonObj(order);
            array.add(obj);
        }
        json.add("rows", array);
        return json;
    }
    
    public static void getOrderInfo(String id) {
    	Order order = Order.find("byOrderNum", id).first();
    	JsonObject obj = getOrderJsonObj(order);
    	renderText(obj);
    }
    
    private static JsonObject getOrderJsonObj(Order order) {
    	JsonObject obj = new JsonObject();
    	if (order == null) {
    		return obj;
    	}
        obj.addProperty("orderid", order.id);
        obj.addProperty("ordernum", order.orderNum);
        if (order.date != null) {
        	obj.addProperty("date", order.date.toLocaleString());
        }
        obj.addProperty("orderprice", order.orderPrice);
        obj.addProperty("state", order.orderstate);
        obj.addProperty("address", order.receiver_addr);
        obj.addProperty("tel", order.receiver_tel);
        obj.addProperty("name", order.receiver_name);
        obj.addProperty("others", order.receiver_other);
//        obj.addProperty("area", order.area);
        
        if (order.orderDetails != null && order.orderDetails.size() > 0) {
        	StringBuffer sb = new StringBuffer();
        	JsonArray detailarray = new JsonArray();
        	for (OrderDetail detail: order.orderDetails) {
        		JsonObject mealJson = new JsonObject();
        		JsonObject id = new JsonObject();
        		if (detail.meal != null) {
        			mealJson.addProperty("id", detail.meal.id);
        			mealJson.addProperty("name", detail.meal.name);
        			mealJson.addProperty("type", "菜品");
        			if (detail.meal.price != null) {
        				mealJson.addProperty("price", detail.meal.price.price);
        			}
        			mealJson.addProperty("discount", detail.meal.price.discount);
        			detailarray.add(mealJson);
        			
        			sb.append(detail.meal.name);
        			id.addProperty("meal", detail.meal.id);
        		} else if (detail.combo != null) {
        			mealJson.addProperty("id", detail.combo.id);
        			mealJson.addProperty("name", detail.combo.name);
        			mealJson.addProperty("type", "套餐");
        			if (detail.combo.price != null) {
        				mealJson.addProperty("price", detail.combo.price.price);
        			}
        			mealJson.addProperty("discount", detail.combo.price.discount);
        			detailarray.add(mealJson);
        			
        			sb.append(detail.combo.name);
        			id.addProperty("combo", detail.combo.id);
        		}
        		sb.append("<br>");
        	}
        	obj.addProperty("content", sb.toString());
        	obj.add("meals", detailarray);
        }
        return obj;
    }
    
    @Transactional
    public static void saveOrder(Order order, Long[] mealid, Long[] comboid) {
    	Order saveOrder = null;
    	if (order.id != null) {
    		saveOrder = Order.find("byOrderNum", order.orderNum).first();
        	if (saveOrder != null) {
        		saveOrder.date = order.date;
        		saveOrder.receiver_name = order.receiver_name;
        		saveOrder.receiver_addr = order.receiver_addr;
        		saveOrder.receiver_tel = order.receiver_tel;
        		saveOrder.receiver_other = order.receiver_other;
        		saveOrder.payWay = order.payWay;
        		saveOrder.orderstate = order.orderstate;
        		saveOrder.orderPrice = order.orderPrice;
//        		saveOrder.area = order.area;
        		saveOrder.orderDetails.clear();
        	}
    	} else {
    		order.save();
    	}
    	
    	if (mealid != null && mealid.length > 0) {
    		for(int i=0;i<mealid.length;i++) {
    			Meal meal = Meal.findById(mealid[i]);
    			if (meal != null) {
    				OrderDetail detail = new OrderDetail();
    				detail.meal = meal;
    				detail.price = meal.price.price;
    				detail.num = 1;
    				detail.save();
    				if(saveOrder != null) {
    					saveOrder.addOrderDetail(detail);
    				} else {
    					order.addOrderDetail(detail);
    				}
    			}
    		}
    	}
    	if (comboid != null && comboid.length > 0) {
    		for (int i=0;i<comboid.length;i++) {
    			Combo combo = Combo.findById(comboid[i]);
    			if (combo != null) {
    				OrderDetail detail = new OrderDetail();
    				detail.combo = combo;
    				detail.price = combo.price.price;
    				detail.num = 1;
    				detail.save();
    				if (saveOrder != null) {
    					saveOrder.addOrderDetail(detail);
    				} else {
    					order.addOrderDetail(detail);
    				}
    			}
    		}
    	}
    }

    public static void deleteOrder(Long[] id) {
    	for (int i=0;i<id.length;i++) {
    		Order order = Order.findById(id[i]);
    		order.delete();
    	}
    }

    public static void updateOrder(Order data) {
    	Order order = Order.find("byOrderNum", data.orderNum).first();
    	order.save();
    }

    private static SimpleDateFormat sPiddatesf = new SimpleDateFormat("yyMMddHHmmss");
    private static SimpleDateFormat sOrdersf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void getOrderPaymentId() {
    	Date now = new Date();
    	String pdate = sPiddatesf.format(now);
    	String odate = sOrdersf.format(now);
    	int r1=(int)(Math.random()*(10));//产生3个0-9的随机数
    	int r2=(int)(Math.random()*(10));
    	int r3=(int)(Math.random()*(10));
    	String paymentID =new StringBuffer(pdate).append(r1).append(r2).append(r3).toString();// 订单ID
    	JsonObject obj = new JsonObject();
    	obj.addProperty("paymentid", paymentID);
    	obj.addProperty("date", odate);
    	renderText(obj);
    }
    
    
    public static void getComboMeals() {
    	List<Meal> mealList = Meal.findAll();
    	List<Combo> comboList = Combo.findAll();
    	JsonObject json = new JsonObject();
    	JsonArray array = new JsonArray();
    	if (mealList != null || comboList != null) {
    		if (mealList.size() > 0) {
    			for (Meal meal: mealList) {
    				JsonObject obj = new JsonObject();
    				obj.addProperty("id", meal.id);
    				obj.addProperty("type", "菜品");
    				obj.addProperty("name", meal.name);
    				obj.addProperty("price", meal.price.price);
    				obj.addProperty("discount", meal.price.discount);
    				array.add(obj);
    			}
    		}
    		if (comboList.size() > 0) {
    			for (Combo combo: comboList) {
    				JsonObject obj = new JsonObject();
    				obj.addProperty("id", combo.id);
    				obj.addProperty("type", "套餐");
    				obj.addProperty("name", combo.name);
    				obj.addProperty("price", combo.price.price);
    				obj.addProperty("discount", combo.price.discount);
    				array.add(obj);
    			}
    		}
    	}
        json.addProperty("total", array.size());
        json.add("rows", array);
        renderText(json);
    }
}
