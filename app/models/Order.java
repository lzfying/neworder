package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.binding.As;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="back_order")
public class Order extends Model{
	
	public Order() {
		orderDetails = new ArrayList<OrderDetail>();
	}
	
	@Required
	public String orderNum;
	
	
	@Required
	@ManyToOne
	public User User;
	
	@Required
	@ManyToMany(cascade=CascadeType.ALL)
	public List<OrderDetail> orderDetails;
	
	@As("yyyy-MM-dd hh:mm:ss")
	public Date date;
	
	public String receiver_name;
	
	public String area;
	
	public String receiver_addr;
	
	public String receiver_tel;
	
	public String receiver_other;
	
	public String payWay;
	
	public String orderstate;
	
	public double orderPrice;
	
	public Order(User user,String orderNum,String  receiver_name,String receiver_addr,String receiver_tel,String receiver_other,String payWay){
		
		this.User=user;
		this.orderNum = orderNum;
		this.receiver_name =receiver_name;
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
