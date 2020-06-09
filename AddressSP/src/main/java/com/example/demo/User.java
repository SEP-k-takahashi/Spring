package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="address_sp")

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    private String name;
    private String address;
    private String tel;
    private String derete_flg;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getDerete_flg() {
        return derete_flg;
    }
    public void setDrete_flg(String derete_flg) {
        this.derete_flg = derete_flg;
    }

}
