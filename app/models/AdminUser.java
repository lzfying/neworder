package models;

import javax.persistence.Entity;
import javax.persistence.Table;



import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="back_adminuser")
public class AdminUser extends Model{
	
	 	@Required
	    @MaxSize(15)
	    @MinSize(4)
	    @Match(value="^\\w*$", message="Not a valid username")
	    public String username;
	 	
	 	@Required
	    @MaxSize(15)
	    @MinSize(5)
	    public String password;
	 	
	 	
	 	@Required
	    @MaxSize(100)
	    public String showname;
	 	
	 	public boolean isAdmin;
	 	

	    public static AdminUser connect(String username, String password) {
	        return find("byUserNameAndPassword", username, password).first();
	    }

}
