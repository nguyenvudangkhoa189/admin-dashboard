package dashboard.service;

import java.util.Date;

public class OrderCriteria {

    private String country;
    private String agent;
    private Date shipDateFrom;
    private Date shipDateTo;
    private String status;
    private String type;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getShipDateFrom() {
        return shipDateFrom;
    }

    public void setShipDateFrom(Date shipDateFrom) {
        this.shipDateFrom = shipDateFrom;
    }

    public void setShipDateFrom(Long shipDateFrom) {
        this.shipDateFrom = shipDateFrom != null ? new Date(shipDateFrom) : null;
    }

    public Date getShipDateTo() {
        return shipDateTo;
    }

    public void setShipDateTo(Date shipDateTo) {
        this.shipDateTo = shipDateTo;
    }

    public void setShipDateTo(Long shipDateTo) {
        this.shipDateTo = shipDateTo != null ? new Date(shipDateTo) : null;
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
