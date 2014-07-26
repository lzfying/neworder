package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="back_order")
public class Order extends Model{
	
	@Required
	public String orderNum;
	
	
	@Required
	@ManyToOne
	public User user;
	
	@Required
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List<OrderDetail> orderDetails;
	
	public Date date;
	
	public String receiver_name;
	
	public String receiver_addr;
	
	public String receiver_tel;
	
	public String receiver_other;
	
	public String payWay;
	
	public String orderstate="0";//0:未处理 1：已发货 2：已签收 3：完成交易
	
	public Double orderPrice=0.0;
	
	public Order(User user,String orderNum,String  receiver_name,String receiver_addr,String receiver_tel,String receiver_other,String payWay){
		
		this.user=user;
		this.orderNum = orderNum;
		this.receiver_name =receiver_name;
		this.receiver_addr = receiver_addr;
		this.receiver_tel= receiver_tel;
		this.receiver_other =receiver_other;
		this.payWay = payWay;
		this.date = new Date();
		this.orderDetails= new ArrayList<OrderDetail>();   
		
	}
	
	public Order(String orderNum,String  receiver_name){
		
		
		this.orderNum = orderNum;
		this.receiver_name =receiver_name;
		
		this.date = new Date();
		this.orderDetails= new ArrayList<OrderDetail>();  
		
	}

	public Order(User user,String orderNum,String receiver_addr,String receiver_tel,String receiver_other,String payWay){

	
		this.user=user;
		this.orderNum = orderNum;
		
		this.receiver_addr = receiver_addr;
		this.receiver_tel= receiver_tel;
		this.receiver_other =receiver_other;
		this.payWay = payWay;
		this.date = new Date();
		this.orderDetails= new ArrayList<OrderDetail>();   
	
	}
	
	
	public Order addOrderDetail(OrderDetail orderDetail){
		this.orderDetails.add(orderDetail);
		this.save();
		return this;
		
	}
	

	

}
