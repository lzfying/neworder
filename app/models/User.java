package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;



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
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    public UserDetail userDetail;
   
    public User(String name, String password, String username,String email) {
    	this.email = email;
        this.password = password;
        this.username = username;
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
