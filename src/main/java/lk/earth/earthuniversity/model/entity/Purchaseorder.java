package lk.earth.earthuniversity.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Purchaseorder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "number")
    @Pattern(regexp = "^PO\\d{3}$", message = "Invalid Number")
    private String number;
    @Basic
    @Column(name = "doplaced")
    private Date doplaced;
    @Basic
    @Column(name = "doexpected")
    private Date doexpected;
    @Basic
    @Column(name = "grandtotal")
    private BigDecimal grandtotal;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "paymenttype_id", referencedColumnName = "id", nullable = false)
    private Paymenttype paymenttype;
    @ManyToOne
    @JoinColumn(name = "purchaseorderstatus_id", referencedColumnName = "id", nullable = false)
    private Purchaseorderstatus purchaseorderstatus;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "purchaseorder",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Purchaseorderitem> purchaseorderitems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDoplaced() {
        return doplaced;
    }

    public void setDoplaced(Date doplaced) {
        this.doplaced = doplaced;
    }

    public Date getDoexpected() {
        return doexpected;
    }

    public void setDoexpected(Date doexpected) {
        this.doexpected = doexpected;
    }

    public BigDecimal getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(BigDecimal grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchaseorder that = (Purchaseorder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (doplaced != null ? !doplaced.equals(that.doplaced) : that.doplaced != null) return false;
        if (doexpected != null ? !doexpected.equals(that.doexpected) : that.doexpected != null) return false;
        if (grandtotal != null ? !grandtotal.equals(that.grandtotal) : that.grandtotal != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (doplaced != null ? doplaced.hashCode() : 0);
        result = 31 * result + (doexpected != null ? doexpected.hashCode() : 0);
        result = 31 * result + (grandtotal != null ? grandtotal.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Paymenttype getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Paymenttype paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Purchaseorderstatus getPurchaseorderstatus() {
        return purchaseorderstatus;
    }

    public void setPurchaseorderstatus(Purchaseorderstatus purchaseorderstatus) {
        this.purchaseorderstatus = purchaseorderstatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<Purchaseorderitem> getPurchaseorderitems() {
        return purchaseorderitems;
    }

    public void setPurchaseorderitems(Collection<Purchaseorderitem> purchaseorderitems) {
        this.purchaseorderitems = purchaseorderitems;
    }
}
