package models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="back_meal")
public class Meal extends Model{
	
	@Required
    @MaxSize(200)
    public String name;
	
    @OneToOne(cascade=CascadeType.ALL)
    public MealType type;//菜品类别，主食、汤类、饮料
	
    @MaxSize(255)
    public String url;
    
    public String des;
    
    @OneToOne(cascade=CascadeType.ALL)
    public Price price;
    
    @OneToMany(cascade=CascadeType.PERSIST)
    public List<Comment> comments;
    
    @ManyToOne
    public ComboDetail combodetail;
 
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<MyTag> tags;
    
    
    
    public Meal addComment(String author, String content,String commentstate) {
        Comment newComment = new Comment(this,  author,  content, commentstate);
        this.comments.add(newComment);
        this.save();
        return this;
    }
    
  
    
    public Meal tagItWith(String name) {
        tags.add(MyTag.findOrCreateByName(name));
        return this;
    }
    
    public static List<Meal> findTaggedWith(String tag) {
        return Meal.find(
            "select distinct p from Post p join p.tags as t where t.name = ?",
            tag
        ).fetch();
    }
    
    public static List<Meal> findTaggedWith(String... tags) {
        return Meal.find(
            "select distinct p.id from Post p join p.tags as t where t.name in (:tags) group by p.id having count(t.id) = :size"
        ).bind("tags", tags).bind("size", tags.length).fetch();
    }

}
