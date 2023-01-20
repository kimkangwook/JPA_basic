package hellojpa;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@TypeDefs(
        {@TypeDef( name="json1", typeClass = JsonType.class),
        @TypeDef( name="json2", typeClass = JsonType.class)})
@Entity
public class ParentEntity {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChildParam> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildParam> childs) {
        this.childs = childs;
    }

    @Type(type="json1")
    @Column(columnDefinition = "json")
    private List<ChildParam> childs= new ArrayList<>();

    public String getMbr() {
        return mbr;
    }

    public void setMbr(String mbr) {
        this.mbr = mbr;
    }

    @Type(type="json2")
    @Column(columnDefinition = "json")
    private String mbr;
}

