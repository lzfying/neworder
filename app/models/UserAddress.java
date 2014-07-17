package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="back_customeraddress")
public class UserAddress extends Model {

    @ManyToOne
    public UserDetail userDetail;

    public String area;
    public String address;
    public String phone;
    public String bakphone;

}
