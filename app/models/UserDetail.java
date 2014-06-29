package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="meta_customerdetail")
public class UserDetail extends Model{
	
	@Required
    @MaxSize(100)
    public String realname;
	
	
    @MaxSize(100)
    public String address;
	
	
    @MaxSize(100)
    public String post;
	
	
    @MaxSize(100)
    public String tel;
	
  
    @MaxSize(100)
    public String phone;
	
	
	
    @Email
    public String mail;
	
	@Required
    @MaxSize(2)
    public String sex;
	
	@OneToOne
    @Required
    @JoinColumn(name="user_id")
	public User user;
	
	
	public int point=0;//积分
	
	

}
