package com.example.FoodTrucksSystem.Service;


import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.TruckOwnersRepository;
import com.example.FoodTrucksSystem.model.TruckOwners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TruckOwnersService {

    private final TruckOwnersRepository truckOwnersRepository;
    @Autowired


    public List<TruckOwners> getAllTruckOwners() {
        return truckOwnersRepository.findAll();
    }
    public Optional<TruckOwners> getTruckOwnerById(Long Id) {
        return truckOwnersRepository.findById(Id);
    }
    public int addNewTruckOwner(TruckOwners trckOwn) {
        if(existsByEmail(trckOwn.getEmail()))
        {
            return 2;
        }
        try
        {
            truckOwnersRepository.save(trckOwn);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;

    }

    public  Optional<TruckOwners> editTruckOwnerById(Long Id, TruckOwners trckOwn) {
        if (truckOwnersRepository.existsById(Id))
        {
            TruckOwners trckOwns = truckOwnersRepository.getById(Id);
            if(trckOwns!=null)
            {
                trckOwns.setName(trckOwn.getName());
                trckOwns.setPassword(trckOwn.getPassword());
                trckOwns.setEmail(trckOwn.getEmail());
                trckOwns.setPhone(trckOwn.getPhone());
                trckOwns.setAddress(trckOwn.getAddress());
                return Optional.of(truckOwnersRepository.save(trckOwns));
            }
        }
        return Optional.empty();
    }
    public  boolean removeTruckOwner(Long id) {
        if (truckOwnersRepository.existsById(id)) {
            truckOwnersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean existsByEmail(String email) {
        return truckOwnersRepository.findByEmail(email) != null;
    }
}
