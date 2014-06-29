package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="back_comment")
public class Comment extends Model{
	
	@ManyToOne
    @Required
	public  Meal meal ;
	
	
	@Required
	public String author ;
	
	public Date date ;
	
	
	public String content;//评价内容
	
	public String commentstate;//0:赞 >1 踩 1:速度 2：口味 3:餐量 4:其他
	
	
	public Comment(Meal meal, String author, String content,String commentstate) {
        this.meal = meal;
        this.author = author;
        this.content = content;
        this.date = new Date();
        this.commentstate= commentstate;
    }
	
	

}
