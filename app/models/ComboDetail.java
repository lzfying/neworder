package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="back_combodetail")
public class ComboDetail extends Model {

	@OneToOne
	public Meal meal;

	public int num;

}
