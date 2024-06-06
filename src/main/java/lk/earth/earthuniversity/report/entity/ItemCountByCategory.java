package lk.earth.earthuniversity.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemCountByCategory {

    private Integer id;
    private String categroryname;
    private Long count;

    public ItemCountByCategory() {  }

    public ItemCountByCategory(String categroryname, Long count) {
        this.categroryname = categroryname;
        this.count = count;
    }

    @Id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategroryname() {
        return categroryname;
    }
    public void setCategroryname(String categroryname) {
        this.categroryname = categroryname;
    }

    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }

}
