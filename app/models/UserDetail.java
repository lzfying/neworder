package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	
    @OneToMany(cascade=CascadeType.ALL)
    public List<UserAddress> address;
	
	
    @Email
    public String mail;
	
	@Required
    @MaxSize(1)
    public int sex;
	
	@OneToOne(mappedBy="userDetail", cascade=CascadeType.ALL)
    @Required
    @JoinColumn(name="user_id")
	public User user;
	
	public int point=0;//积分
	
	public UserDetail(String realname, List<UserAddress> address, String mail, int sex, User user, int point) {
		this.realname = realname;
		this.address = address;
		this.mail = mail;
		this.sex = sex;
		this.user = user;
		this.point = point;
	}

}
