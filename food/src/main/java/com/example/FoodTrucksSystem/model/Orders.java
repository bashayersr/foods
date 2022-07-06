package com.example.FoodTrucksSystem.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull(message = "Orderid is required")
    @Column(name="Orderid",nullable=false)
    Long OrderId;

    @ManyToOne
    @MapsId("Custid")
    @JoinColumn(name="Custid")
    @JsonIgnore
    private Customers customers;

    @ManyToOne
    @MapsId("Truckrid")
    @JoinColumn(name="Truckrid")
    @JsonIgnore
    private Trucks trucks;

    @ManyToOne
    @MapsId("Id")
    @JoinColumn(name="TruckOwnerId")
    @JsonIgnore
    private TruckOwners truckOwners;

    @ManyToOne
    @JoinColumn(name="Payid")
    @JsonIgnore
    private PaymentTypes paymentTypes;

    @NotNull(message = "Quantity is required")
    @Column(name="Quantity",nullable=false)
    Float Quantity;

    @NotNull(message = "Price is required")
    @Column(name="Price",nullable=false)
    Float Price;

    @NotNull(message = "Orderdate is required")
    @Column(name="Orderdate",nullable=false)
    Date OrderDate;

    public Orders() {

    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Trucks getTrucks() {
        return trucks;
    }

    public void setTrucks(Trucks trucks) {
        this.trucks = trucks;
    }

    public TruckOwners getTruckOwners() {
        return truckOwners;
    }

    public void setTruckOwners(TruckOwners truckOwners) {
        this.truckOwners = truckOwners;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public Float getQuantity() {
        return Quantity;
    }

    public void setQuantity(Float quantity) {
        Quantity = quantity;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }






}
