package lk.earth.earthuniversity.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "telephone")
    private String telephone;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "photo")
    private byte[] photo;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "contactperson")
    private String contactperson;
    @Basic
    @Column(name = "contactpersontelephone")
    private String contactpersontelephone;
    @ManyToOne
    @JoinColumn(name = "suppliertype_id", referencedColumnName = "id", nullable = false)
    private Suppliertype suppliertype;
    @ManyToOne
    @JoinColumn(name = "supplierstatus_id", referencedColumnName = "id", nullable = false)
    private Supplierstatus supplierstatus;
    @OneToMany(mappedBy = "supplier")
    private Collection<Supply> supplies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public String getContactpersontelephone() {
        return contactpersontelephone;
    }

    public void setContactpersontelephone(String contactpersontelephone) {
        this.contactpersontelephone = contactpersontelephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (code != null ? !code.equals(supplier.code) : supplier.code != null) return false;
        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;
        if (email != null ? !email.equals(supplier.email) : supplier.email != null) return false;
        if (telephone != null ? !telephone.equals(supplier.telephone) : supplier.telephone != null) return false;
        if (address != null ? !address.equals(supplier.address) : supplier.address != null) return false;
        if (!Arrays.equals(photo, supplier.photo)) return false;
        if (description != null ? !description.equals(supplier.description) : supplier.description != null)
            return false;
        if (contactperson != null ? !contactperson.equals(supplier.contactperson) : supplier.contactperson != null)
            return false;
        if (contactpersontelephone != null ? !contactpersontelephone.equals(supplier.contactpersontelephone) : supplier.contactpersontelephone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (contactperson != null ? contactperson.hashCode() : 0);
        result = 31 * result + (contactpersontelephone != null ? contactpersontelephone.hashCode() : 0);
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
