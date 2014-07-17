package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import play.data.validation.Email;
import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;



@Entity
@Table(name="meta_customer")
public class User extends Model {
    
    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;
    
    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;
    
    @Email
    @Required
    public String email;
    
    
  
    @Required
    public String tel;
    
    @Required
    @OneToOne(cascade=CascadeType.ALL)
    public UserDetail userDetail;
    
    
    @OneToMany(cascade=CascadeType.ALL)
    public List<UserAddress> address;
    

    public User(String name, String password, String username,String email) {
    	this.email = email;
        this.password = password;
        this.username = username;
    }
    
    public User(String username,String password,String tel) {
        this.password = password;
        this.username = username;
        this.tel=tel;
    }
    
    public User(String name, String password, String username,UserDetail userDetail) {
        this.password = password;
        this.username = username;
        this.userDetail= userDetail;
    }

    public String toString()  {
        return "User(" + username + ")";
    }
    
}
