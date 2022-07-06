package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.DepartmentsService;
import com.example.FoodTrucksSystem.model.Departments;
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
@RequestMapping("/departments")
public class DepartmentsController {

    Logger logger=LoggerFactory.getLogger(DepartmentsController.class);

    private final DepartmentsService departmentsService;

//    @Autowired


    @GetMapping
    public ResponseEntity<List<Departments>> getDepartments(){
        logger.info("getAllDepartments =>called");
        List<Departments> dept=departmentsService.getAllDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(dept);
    }

    @GetMapping("/search/{deptid}")
    public ResponseEntity<API> getDepartmentById(@PathVariable Long deptid)
    {
        logger.info("getDepartmentsById =>called");
        Optional<Departments> c = departmentsService.getDepartmentById(deptid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search Department : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("deptid is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addDepartment(@RequestBody @Valid Departments mydept){
        logger.info("addNewDepartment =>called");
        int statfunc=0;
        statfunc= departmentsService.addNewDepartment(mydept);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Department is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New Department ! "+mydept.toString(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{deptid}")
    ResponseEntity<API> editDepartments(@PathVariable Long deptid,@RequestBody Departments dept) {
        logger.info("editDepartmentById =>called");
        Optional<Departments> result = departmentsService.editDepartmentById(deptid, dept);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit Department "+deptid+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("deptid is valid",200));
    }

    @DeleteMapping("/delete/{deptid}")
    ResponseEntity<API> deleteDepartmentById(@PathVariable Long deptid) {
        logger.info("removeDepartment =>called");
        if (departmentsService.removeDepartment(deptid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete Department "+deptid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found Department "+deptid,200));
    }

}
