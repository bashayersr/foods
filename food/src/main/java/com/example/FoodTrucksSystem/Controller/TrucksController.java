package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.TrucksService;
import com.example.FoodTrucksSystem.model.Trucks;
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
@RequestMapping("/trucks")
public class TrucksController {

    Logger logger=LoggerFactory.getLogger(TrucksController.class);

    private final TrucksService trucksService;
    @Autowired



    @GetMapping
    public ResponseEntity<List<Trucks>> getTrucks(){
        logger.info("getAllTrucks =>called");
        List<Trucks> trk=trucksService.getAllTrucks();
        return ResponseEntity.status(HttpStatus.OK).body(trk);
    }

    @GetMapping("/search/{truckid}")
    public ResponseEntity<API> getTruckById(@PathVariable Long truckid)
    {
        logger.info("getTruckById =>called");
        Optional<Trucks> c = trucksService.getTruckById(truckid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search Truck : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("truckid is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addTruck(@RequestBody @Valid Trucks mytrk){
        logger.info("addNewTruck =>called");
        int statfunc=0;
        statfunc= trucksService.addNewTruck(mytrk);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Truck is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New Truck ! "+mytrk.toString(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{truckid}")
    ResponseEntity<API> editTruck(@PathVariable Long truckid,@RequestBody Trucks trck) {
        logger.info("editTruckById =>called");
        Optional<Trucks> result = trucksService.editTruckById(truckid, trck);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit Truck "+truckid+" for : "+result.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Truck id is valid",200));
    }

    @DeleteMapping("/delete/{truckid}")
    ResponseEntity<API> deleteTruckById(@PathVariable Long truckid) {
        logger.info("removePaymentType =>called");
        if (trucksService.removeTruck(truckid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete Truck "+truckid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found Truck "+truckid,200));
    }

}
