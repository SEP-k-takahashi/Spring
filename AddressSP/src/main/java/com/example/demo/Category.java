package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryid;
    private String categoryname;

    public long getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }
    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @OneToMany(mappedBy="categoryid", cascade=CascadeType.ALL)
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setEquipments(List<User> user) {
        this.user = user;
    }
}
