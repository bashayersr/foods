package com.example.FoodTrucksSystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class Departments {



    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @NotNull(message = "DeptId is required")
    @Column(name="Deptid",nullable=false)
    Long Deptid;

    @NotEmpty(message = "DeptName is required")
    @Column(name="Deptname",nullable=false)
    String Deptname;

    @OneToMany(mappedBy = "departments")
    List<Trucks> trucks;

    public Departments() {
    }


    public void updateDepartment(Departments dept) {
        this.Deptname = dept.Deptname;
    }

    public Long getDeptid() {
        return Deptid;
    }

    public void setDeptid(Long deptid) {
        Deptid = deptid;
    }

    public String getDeptname() {
        return Deptname;
    }

    public void setDeptname(String deptname) {
        Deptname = deptname;
    }
    public List<Trucks> getTrucks() {
        return trucks;
    }
    public void setTrucks(List<Trucks> trucks) {
        this.trucks = trucks;
    }

    @Override
    public String toString() {
        return "Departments [Deptid=" + Deptid + ", Deptname=" + Deptname + "]";
    }



}
