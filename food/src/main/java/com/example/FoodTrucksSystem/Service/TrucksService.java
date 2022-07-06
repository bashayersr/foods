package com.example.FoodTrucksSystem.Service;


import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.DepartmentsRepository;
import com.example.FoodTrucksSystem.Repository.TruckOwnersRepository;
import com.example.FoodTrucksSystem.Repository.TrucksRepository;
import com.example.FoodTrucksSystem.model.Trucks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrucksService {


    private final TrucksRepository trucksRepository;
    private final DepartmentsRepository departmentsRepository;
    private final TruckOwnersRepository truckOwnersRepository ;
    @Autowired




    public List<Trucks> getAllTrucks() {
        return trucksRepository.findAll();
    }
    public Optional<Trucks> getTruckById(Long truckid) {
        return trucksRepository.findById(truckid);
    }
    public int addNewTruck(Trucks truck) {

        try
        {
            trucksRepository.save(truck);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;

    }

    public  Optional<Trucks> editTruckById(Long truckid, Trucks truck) {
        if (trucksRepository.existsById(truckid))
        {
            Trucks trucks = trucksRepository.getById(truckid);
            if(trucks!=null)
            {
                trucks.setTruckname(truck.getTruckname());
                trucks.setDepartments(truck.getDepartments());
                trucks.setTruckOwners(truck.getTruckOwners());
                return Optional.of(trucksRepository.save(trucks));
            }
        }
        return Optional.empty();
    }
    public  boolean removeTruck(Long truckid)
    {
        if (trucksRepository.existsById(truckid))
        {
            trucksRepository.deleteById(truckid);
            return true;
        }
        return false;
    }
}
