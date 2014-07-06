package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
@Table(name="back_combo")
public class Combo extends Model {

	public String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	public List<ComboDetail> details;

	@OneToOne(cascade=CascadeType.ALL)
	public Price price;

    @MaxSize(255)
    public String url;
    
    public String des;

    @OneToMany(mappedBy="combo", cascade=CascadeType.ALL)
    public List<Comment> comments;

    public Combo() {
    	this.details = new ArrayList<ComboDetail>();
    }

    public Combo addDetail(ComboDetail detail) {
    	this.details.add(detail);
    	this.save();
    	return this;
    }
    
}
