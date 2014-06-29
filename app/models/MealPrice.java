package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="back_mealprice")
public class MealPrice extends Model{
	//价格策略 1:原价 2：8折 3：6折 
	public String strategy ="1";
	
	public int price;//原价

}
