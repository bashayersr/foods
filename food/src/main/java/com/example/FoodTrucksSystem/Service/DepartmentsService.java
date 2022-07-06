package com.example.FoodTrucksSystem.Service;


import com.example.FoodTrucksSystem.Repository.DepartmentsRepository;
import com.example.FoodTrucksSystem.model.Departments;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DepartmentsService {

    private final DepartmentsRepository departmentsRepository;
//    @Autowired


    public List<Departments> getAllDepartments() {
        return departmentsRepository.findAllDepartments();
    }
    public Optional<Departments> getDepartmentById(Long deptid) {
        return departmentsRepository.findById(deptid);
    }
    public int addNewDepartment(Departments dept) {

        try
        {
            departmentsRepository.save(dept);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;

    }

    public  Optional<Departments> editDepartmentById(Long deptid, Departments dept) {
        if (departmentsRepository.existsById(deptid))
        {
            Departments departments = departmentsRepository.getById(deptid);
            if(departments!=null)
            {
                departments.setDeptname(dept.getDeptname());
                return Optional.of(departmentsRepository.save(departments));
            }
        }
        return Optional.empty();
    }
    public  boolean removeDepartment(Long deptid) {
        if (departmentsRepository.existsById(deptid)) {
            departmentsRepository.deleteById(deptid);
            return true;
        }
        return false;
    }
}
