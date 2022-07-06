package com.example.FoodTrucksSystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class Trucks {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @NotNull(message = "TruckrId is required")
    @Column(name="Truckrid",nullable=false)
    Long Truckrid;

    @NotEmpty(message = "Truckname is required")
    @Column(name="Truckname",nullable=false)
    String Truckname;

    @ManyToOne
    @JoinColumn(name="Deptid")
    @JsonIgnore
    private  Departments departments;

    @ManyToOne
//    @MapsId("Id")
    @JoinColumn(name="TruckOwnerId")
    @JsonIgnore
    private TruckOwners truckOwners;

    public Trucks() {

    }


    public Long getTruckrid() {
        return Truckrid;
    }

    public void setTruckrid(Long truckrid) {
        Truckrid = truckrid;
    }

    public String getTruckname() {
        return Truckname;
    }

    public void setTruckname(String truckname) {
        Truckname = truckname;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }
    public TruckOwners getTruckOwners() {
        return truckOwners;
    }
    public void setTruckOwners(TruckOwners truckOwners) {
        this.truckOwners = truckOwners;
    }

    @Override
    public String toString() {
        return "Trucks [Truckrid=" + Truckrid + ", Truckname=" + Truckname + ", departments=" + departments.toString()
                + ", truckOwners=" + truckOwners.toString() + "]";
    }


}
