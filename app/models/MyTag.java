package models;
 
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
@Table(name="back_tag")
public class MyTag extends Model implements Comparable<MyTag> {
 
    @Required
    public String name;
    
    private MyTag(String name) {
        this.name = name;
    }
    
    public static MyTag findOrCreateByName(String name) {
    	MyTag tag = MyTag.find("byName", name).first();
        if(tag == null) {
            tag = new MyTag(name);
        }
        return tag;
    }
    
    public static List<Map> getCloud() {
        List<Map> result = MyTag.find(
            "select new map(t.name as tag, count(p.id) as pound) from Post p join p.tags as t group by t.name"
        ).fetch();
        return result;
    }
    
    public String toString() {
        return name;
    }
    
    public int compareTo(MyTag otherTag) {
        return name.compareTo(otherTag.name);
    }
 
}