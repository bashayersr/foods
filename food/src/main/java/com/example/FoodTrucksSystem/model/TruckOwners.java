package com.example.FoodTrucksSystem.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class TruckOwners {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @NotNull(message = "Id is required")
    @Column(name="Id",nullable=false)
    Long Id;

    @NotEmpty(message = "Name is required")
    @Column(name="Name",nullable=false)
    String Name;


    @NotNull(message = "State is required")
    @Column(name="State",nullable=false)
    int State;

    @NotEmpty(message = "Address is required")
    @Column(name="Address",nullable=false)
    String Address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please send valid email")
    @Column(unique = true,nullable=false)
    String email;

    @NotEmpty(message = "Phone is required")
    @Column(name="Phone",nullable=false)
    String Phone;



    @NotEmpty(message = "Password is required")
    @Size(min = 3,max = 10,message = "Your password must be more than 3 and less than 10")
    @Column(name="Password",nullable=false)
    String Password;

    @OneToMany(mappedBy = "truckOwners")
    List<Trucks> trucks;

    public TruckOwners() {

    }

    public String getPassword() {
        return Password;
    }


    public void setPassword(String password) {
        Password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return Phone;
    }


    public void setPhone(String phone) {
        Phone = phone;
    }






    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        Id = id;
    }


    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }


    public int getState() {
        return State;
    }


    public void setState(int state) {
        State = state;
    }


    public String getAddress() {
        return Address;
    }


    public void setAddress(String address) {
        Address = address;
    }


    public List<Trucks> getTrucks() {
        return trucks;
    }


    public void setTrucks(List<Trucks> trucks) {
        this.trucks = trucks;
    }


    @Override
    public String toString() {
        return "TruckOwners [Id=" + Id + ", Name=" + Name + ", State=" + State + ", Address=" + Address + ", email="
                + email + ", Phone=" + Phone + ", Password=" + Password + ", trucks=" + trucks.toString() + "]";
    }



}
