package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="back_orderdetail")
public class OrderDetail extends Model{
	
	public int mealId;
	
	public int num;
	
	public String priceType;
	
	public int totalNum;

}
