package lk.earth.earthuniversity.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$",message = "Invalid Name")
    private String name;
    @Basic
    @Column(name = "email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid Email Address")
    private String email;
    @Basic
    @Column(name = "tpmobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobile Number")
    private String tpmobile;
    @Basic
    @Column(name = "tpland")
    @Pattern(regexp = "^\\d{0,10}$", message = "Invalid Land phone Number")
    private String tpland;
    @Basic
    @Column(name = "regno")
    @Pattern(regexp = "^SP\\d{3}$", message = "Invalid Registration Number")
    private String regno;
    @Basic
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address")
    private String address;
    @Basic
    @Column(name = "doregistered")
    private Date doregistered;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "contactperson")
    @Pattern(regexp = "^(Mr|Mrs)\\s+[A-Za-z]+\\s+[A-Za-z]+\\s*[A-Za-z]*$",message = "Invalid Name")
    private String contactperson;
    @Basic
    @Column(name = "contactpersontp")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobile Number")
    private String contactpersontp;
    @ManyToOne
    @JoinColumn(name = "suppliertype_id", referencedColumnName = "id", nullable = false)
    private Suppliertype suppliertype;
    @ManyToOne
    @JoinColumn(name = "supplierstatus_id", referencedColumnName = "id", nullable = false)
    private Supplierstatus supplierstatus;
    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Supply> supplies;

    public Supplier(){}
    public Supplier(Integer id,String name){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTpmobile() {
        return tpmobile;
    }

    public void setTpmobile(String tpmobile) {
        this.tpmobile = tpmobile;
    }

    public String getTpland() {
        return tpland;
    }

    public void setTpland(String tpland) {
        this.tpland = tpland;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDoregistered() {
        return doregistered;
    }

    public void setDoregistered(Date doregistered) {
        this.doregistered = doregistered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getContactpersontp() {
        return contactpersontp;
    }

    public void setContactpersontp(String contactpersontp) {
        this.contactpersontp = contactpersontp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;
        if (email != null ? !email.equals(supplier.email) : supplier.email != null) return false;
        if (tpmobile != null ? !tpmobile.equals(supplier.tpmobile) : supplier.tpmobile != null) return false;
        if (tpland != null ? !tpland.equals(supplier.tpland) : supplier.tpland != null) return false;
        if (regno != null ? !regno.equals(supplier.regno) : supplier.regno != null) return false;
        if (address != null ? !address.equals(supplier.address) : supplier.address != null) return false;
        if (doregistered != null ? !doregistered.equals(supplier.doregistered) : supplier.doregistered != null)
            return false;
        if (description != null ? !description.equals(supplier.description) : supplier.description != null)
            return false;
        if (contactperson != null ? !contactperson.equals(supplier.contactperson) : supplier.contactperson != null)
            return false;
        if (contactpersontp != null ? !contactpersontp.equals(supplier.contactpersontp) : supplier.contactpersontp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tpmobile != null ? tpmobile.hashCode() : 0);
        result = 31 * result + (tpland != null ? tpland.hashCode() : 0);
        result = 31 * result + (regno != null ? regno.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (doregistered != null ? doregistered.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (contactperson != null ? contactperson.hashCode() : 0);
        result = 31 * result + (contactpersontp != null ? contactpersontp.hashCode() : 0);
        return result;
    }

    public Suppliertype getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(Suppliertype suppliertype) {
        this.suppliertype = suppliertype;
    }

    public Supplierstatus getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(Supplierstatus supplierstatus) {
        this.supplierstatus = supplierstatus;
    }

    public Collection<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(Collection<Supply> supplies) {
        this.supplies = supplies;
    }
}
