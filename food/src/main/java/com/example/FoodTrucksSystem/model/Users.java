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
public class Users {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @NotNull(message = "UsrId is required")
    @Column(name="Usrid",nullable=false)
    Long Usrid;

    @NotEmpty(message = "UsrName is required")
    @Column(name="Usrname",nullable=false)
    String Usrname;

    @NotEmpty(message = "Password is required")
    @Size(min = 3,max = 10,message = "Your password must be more than 3 and less than 10")
    @Column(name="Password",nullable=false)
    String Password;

    @NotNull(message = "IsAdmin is required")
    @Column(name="Isadmin",nullable=false)
    int Isadmin;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please send valid email")
    @Column(unique = true,nullable=false)
    String email;

    public Users() {

    }


    public void updateUsers(Users usr) {
        this.Usrname = usr.Usrname;
        this.Password = usr.Password;
        this.Isadmin = usr.Isadmin;
        this.email=usr.email;
    }

    public Long getUsrid() {
        return Usrid;
    }

    public void setUsrid(Long usrid) {
        Usrid = usrid;
    }

    public String getUsrname() {
        return Usrname;
    }

    public void setUsrname(String usrname) {
        Usrname = usrname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getIsadmin() {
        return Isadmin;
    }

    public void setIsadmin(int isadmin) {
        Isadmin = isadmin;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User Info [Usrid=" + Usrid + ", Usrname=" + Usrname + ", Password=" + Password + ", Email=" + email + ", IsAdmain=" + Isadmin + "]";
    }

}
