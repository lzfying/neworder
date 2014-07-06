package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.db.jpa.Model;
@Entity
@Table(name="back_treenode")
public class FuncTreeNode extends Model {

    @MaxSize(20)
    public String label;

    public Integer parent;
    
    public Integer level;

    public String action;
}
