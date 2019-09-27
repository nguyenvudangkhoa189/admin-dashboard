package dashboard.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORDID")
    private int recordID;

    @Column(name = "ORDERID")
    private int orderID;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "SHIPCITY")
    private String shipCity;

    @Column(name = "COMPANYAGENT")
    private String companyAgent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SHIPDATE")
    private Date shipDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TYPE")
    private String type;

    public Order() {
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getCompanyAgent() {
        return companyAgent;
    }

    public void setCompanyAgent(String companyAgent) {
        this.companyAgent = companyAgent;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}