package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.TruckOwnersService;
import com.example.FoodTrucksSystem.model.TruckOwners;
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
@RequestMapping("/truckowners")
public class TruckOwnersController {

    Logger logger=LoggerFactory.getLogger(TruckOwnersController.class);

    private final TruckOwnersService truckOwnersService;
    @Autowired


    @GetMapping
    public ResponseEntity<List<TruckOwners>> getTruckOwners(){
        logger.info("getAllTruckOwners =>called");
        List<TruckOwners> trckOwn=truckOwnersService.getAllTruckOwners();
        return ResponseEntity.status(HttpStatus.OK).body(trckOwn);
    }

    @GetMapping("/search/{Id}")
    public ResponseEntity<API> getTruckOwnerById(@PathVariable Long Id)
    {
        logger.info("getTruckOwnersById =>called");
        Optional<TruckOwners> c = truckOwnersService.getTruckOwnerById(Id);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search trckOwnomer : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Id is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addTruckOwner(@RequestBody @Valid TruckOwners mytrckOwn){
        logger.info("addNewTruckOwner =>called");
        int statfunc=0;
        statfunc= truckOwnersService.addNewTruckOwner(mytrckOwn);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("TruckOwner is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New TruckOwner ! "+mytrckOwn.toString(),200));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Email is Exists :"+mytrckOwn.getEmail(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{Id}")
    ResponseEntity<API> edittrckOwnomer(@PathVariable Long Id,@RequestBody TruckOwners trckOwn) {
        logger.info("edittrckOwnomerById =>called");
        Optional<TruckOwners> result = truckOwnersService.editTruckOwnerById(Id, trckOwn);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit TruckOwners "+Id+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Id is valid",200));
    }

    @DeleteMapping("/delete/{Id}")
    ResponseEntity<API> deletetrckOwnomerById(@PathVariable Long Id) {
        logger.info("removetrckOwnomer =>called");
        if (truckOwnersService.removeTruckOwner(Id))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete TruckOwner "+Id,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found TruckOwner "+Id,200));
    }

}
