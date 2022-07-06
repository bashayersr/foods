package com.example.FoodTrucksSystem.Service;


import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.PaymentTypesRepository;
import com.example.FoodTrucksSystem.model.PaymentTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTypesService {

    private final PaymentTypesRepository paymentTypesRepository;
    @Autowired


    public List<PaymentTypes> getAllPaymentTypes() {
        return paymentTypesRepository.findAll();
    }
    public Optional<PaymentTypes> getPaymentTypeById(Long payid) {
        return paymentTypesRepository.findById(payid);
    }
    public int addNewPaymentType(PaymentTypes pay) {

        try
        {
            paymentTypesRepository.save(pay);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;

    }

    public  Optional<PaymentTypes> editPaymentTypeById(Long payid, PaymentTypes pay) {
        if (paymentTypesRepository.existsById(payid))
        {
            PaymentTypes paymentTypes = paymentTypesRepository.getById(payid);
            if(paymentTypes!=null)
            {
                paymentTypes.setPayType(pay.getPayType());
                return Optional.of(paymentTypesRepository.save(paymentTypes));
            }
        }
        return Optional.empty();
    }
    public  boolean removePaymentType(Long payid) {
        if (paymentTypesRepository.existsById(payid)) {
            paymentTypesRepository.deleteById(payid);
            return true;
        }
        return false;
    }

}
