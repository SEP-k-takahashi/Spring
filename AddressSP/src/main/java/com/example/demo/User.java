package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="address_sp")

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="name")
    private String name;
	@Column(name="address")
    private String address;
	@Column(name="tel")
    private String tel;
	@Column(name="derete_flg")
    private String derete_flg;
	@Column(name="categoryid")
    private long categoryid;

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
    public String getDelete_flg() {
        return derete_flg;
    }
    public void setDelete_flg(String delete_flg) {
        this.derete_flg = delete_flg;
    }
    public long getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @ManyToOne
    @JoinColumn(name="categoryid",insertable = false, updatable = false)
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
