package com.example.FoodTrucksSystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class Customers {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
//    @Column(name="Custid",nullable=false)
    Long CustId;

    @NotEmpty(message = "CustName is required")
    @Column(name="Custname",nullable=false)
    String CustName;


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

    public Customers() {

    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getCustId() {
        return CustId;
    }

    public void setCustId(Long custId) {
        CustId = custId;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
    @Override
    public String toString() {
        return "Customer Info [CustId=" + CustId + ", CustName=" + CustName + ", Password=" + Password + ", Email=" + email + ", Phone=" + Phone + ", Address=" + Address + "]";
    }

}
