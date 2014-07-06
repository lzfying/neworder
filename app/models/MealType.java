package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="back_meal_type")
public class MealType extends Model {

	@ManyToOne(cascade=CascadeType.ALL)
	public Meal meal;

	public int mealType;

}
