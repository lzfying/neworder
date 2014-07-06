package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="dic_meal_type")
public class DicMealType extends Model {

	public int type;
	public String name;

}
