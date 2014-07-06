package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="back_mealprice")
public class Price extends Model{
	
	public double price;

	public double discount = 10;

	@OneToOne(mappedBy="price",cascade=CascadeType.ALL)
	public Meal meal;

	@OneToOne(mappedBy="price",cascade=CascadeType.ALL)
	public Combo combo;

}
