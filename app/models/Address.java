package models;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

public class Address extends Model{

    @Required
    public String address;
    
    @MinSize(6)
    @MaxSize(6)
    @Match(value="[1-9]\\d{5}(?!\\d)",message="Invalid post")
    public Integer postcode;

    @Required
    @MinSize(11)
    @MaxSize(11)
    public Integer cellphone;

    @MaxSize(20)
    @Match(value="^[+]{0,1}(\\d){1,4}[ ]{0,1}([-]{0,1}((\\d)|[ ]){1,12})+$", message="Invalid phone")
    public String phone;


}
