package dashboard.service;

import java.util.Date;

import lombok.Data;

@Data
public class OrderCriteria {

    private String country;
    private String agent;
    private Date shipDateFrom;
    private Date shipDateTo;
    private String status;
    private String type;

    public void setShipDateFrom(Long shipDateFrom) {
        this.shipDateFrom = shipDateFrom != null ? new Date(shipDateFrom) : null;
    }
    
    public void setShipDateTo(Long shipDateTo) {
        this.shipDateTo = shipDateTo != null ? new Date(shipDateTo) : null;
    }
}
