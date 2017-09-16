package org.dcm4che3.tool.storescp.domain;

import javax.persistence.*;

@Entity
public class Institute {
    @Id
    private String name;
    @Column
    private String address;
    @Column
    private String ip_address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
