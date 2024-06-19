package lk.earth.earthuniversity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.earth.earthuniversity.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^[A-Z][a-z]*\\s[A-Z][a-z]*\\s(?:[A-Z][a-z]*\\s)?\\d{1,3}(kg|g|ml|l)$",message = "Invalid Item Name")
    private String name;

    @Basic
    @Column(name = "code")
    @Pattern(regexp = "^[I][\\d]{5}$", message = "Invalid Code")
    private String code;

    @Basic
    @Column(name = "sprice")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$\n", message = "Invalid Sale Price")
    private BigDecimal sprice;

    @Basic
    @Column(name = "pprice")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$\n", message = "Invalid Purchase Price")
    private BigDecimal pprice;

    @Basic
    @Column(name = "photo")
    private byte[] photo;

    @Basic
    @Column(name = "quantity")
    @Pattern(regexp = "^\\d{3}$", message = "Invalid Quantity")
    private Integer quantity;

    @Basic
    @Column(name = "rop")
    @Pattern(regexp = "^\\d{2}$", message = "Invalid ROP")
    private Integer rop;

    @Basic
    @Column(name = "dointroduced")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date dointroduced;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
    private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name = "unittype_id", referencedColumnName = "id", nullable = false)
    private Unittype unittype;

    @ManyToOne
    @JoinColumn(name = "itemstatus_id", referencedColumnName = "id", nullable = false)
    private Itemstatus itemstatus;
    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private Collection<Supply> supplies;

    public Item(){}

    public Item(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getSprice() {
        return sprice;
    }

    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRop() {
        return rop;
    }

    public void setRop(Integer rop) {
        this.rop = rop;
    }

    public Date getDointroduced() {
        return dointroduced;
    }

    public void setDointroduced(Date dointroduced) {
        this.dointroduced = dointroduced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (code != null ? !code.equals(item.code) : item.code != null) return false;
        if (sprice != null ? !sprice.equals(item.sprice) : item.sprice != null) return false;
        if (pprice != null ? !pprice.equals(item.pprice) : item.pprice != null) return false;
        if (!Arrays.equals(photo, item.photo)) return false;
        if (quantity != null ? !quantity.equals(item.quantity) : item.quantity != null) return false;
        if (rop != null ? !rop.equals(item.rop) : item.rop != null) return false;
        if (dointroduced != null ? !dointroduced.equals(item.dointroduced) : item.dointroduced != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (sprice != null ? sprice.hashCode() : 0);
        result = 31 * result + (pprice != null ? pprice.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (rop != null ? rop.hashCode() : 0);
        result = 31 * result + (dointroduced != null ? dointroduced.hashCode() : 0);
        return result;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Unittype getUnittype() {
        return unittype;
    }

    public void setUnittype(Unittype unittyped) {
        this.unittype = unittype;
    }

    public Itemstatus getItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(Itemstatus itemstatus) {
        this.itemstatus = itemstatus;
    }

    public Collection<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(Collection<Supply> supplies) {
        this.supplies = supplies;
    }
}
