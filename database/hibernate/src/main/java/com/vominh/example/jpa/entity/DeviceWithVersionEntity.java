package com.vominh.example.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class DeviceWithVersionEntity {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "serial")
    private Integer serial;
    @Column(name = "name")
    private String name;
    @Version
    @Column(name = "version")
    private int version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
