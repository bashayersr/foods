package com.example.FoodTrucksSystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table
public class PaymentTypes {


    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @NotNull(message = "PayId is required")
    @Column(name="Payid",nullable=false)
    Long PayId;

    @NotEmpty(message = "PayType is required")
    @Column(name="Paytype",nullable=false)
    String PayType;

    public PaymentTypes() {

    }

    public Long getPayId() {
        return PayId;
    }

    public void setPayId(Long payId) {
        PayId = payId;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    @Override
    public String toString() {
        return "PaymentTypes [PayId=" + PayId + ", PayType=" + PayType + "]";
    }



}
