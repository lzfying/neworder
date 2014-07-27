package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="back_orderdetail")
public class OrderDetail extends Model {
	
	@OneToOne
	public Meal meal;
	
	@OneToOne
	public Combo combo;

	public double price;

	public int num;
	
	public String priceType;
	
	public int totalNum;
	
	public String des;
	
	public String url;
	
	public String mealName;

}
