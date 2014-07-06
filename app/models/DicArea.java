package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
@Table(name="dic_area")
public class DicArea extends Model {

	@MaxSize(10)
	public int code;

	@MaxSize(100)
	public String name;

}
