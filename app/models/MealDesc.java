package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="back_mealdesc")
public class MealDesc extends Model{
	
	public String content;

}
