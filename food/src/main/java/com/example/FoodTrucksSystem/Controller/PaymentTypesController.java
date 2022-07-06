package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.PaymentTypesService;
import com.example.FoodTrucksSystem.model.PaymentTypes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/paymenttypes")
public class PaymentTypesController {

    Logger logger=LoggerFactory.getLogger(PaymentTypesController.class);

    private final PaymentTypesService paymentTypesService;
    @Autowired




    @GetMapping
    public ResponseEntity<List<PaymentTypes>> getPaymentTypes(){
        logger.info("getAllPaymentTypes =>called");
        List<PaymentTypes> pay=paymentTypesService.getAllPaymentTypes();
        return ResponseEntity.status(HttpStatus.OK).body(pay);
    }

    @GetMapping("/search/{payid}")
    public ResponseEntity<API> getPaymentTypeById(@PathVariable Long payid)
    {
        logger.info("getPaymentTypesById =>called");
        Optional<PaymentTypes> c = paymentTypesService.getPaymentTypeById(payid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search PaymentType : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("payid is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addPaymentType(@RequestBody @Valid PaymentTypes mypay){
        logger.info("addNewPaymentType =>called");
        int statfunc=0;
        statfunc= paymentTypesService.addNewPaymentType(mypay);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("PaymentType is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New PaymentType ! "+mypay.toString(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{payid}")
    ResponseEntity<API> editPaymentTypes(@PathVariable Long payid,@RequestBody PaymentTypes pay) {
        logger.info("editPaymentTypeById =>called");
        Optional<PaymentTypes> result = paymentTypesService.editPaymentTypeById(payid, pay);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit PaymentType "+payid+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("payid is valid",200));
    }

    @DeleteMapping("/delete/{payid}")
    ResponseEntity<API> deletePaymentTypeById(@PathVariable Long payid) {
        logger.info("removePaymentType =>called");
        if (paymentTypesService.removePaymentType(payid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete PaymentType "+payid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found PaymentType "+payid,200));
    }

}
