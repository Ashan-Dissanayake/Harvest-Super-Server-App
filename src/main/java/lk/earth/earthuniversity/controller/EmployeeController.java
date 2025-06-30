package lk.earth.earthuniversity.controller;
import lk.earth.earthuniversity.dao.EmployeeDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeedao;

    @GetMapping(produces = "application/json")
//    @PreAuthorize("hasAuthority('employee-select')")
    public ResponseEntity<APISuccessResponse<List<Employee>>> get(@RequestParam HashMap<String, String> params) {

        List<Employee> employees = this.employeedao.findAll();

        if(params.isEmpty()) return APIResponseBuilder.getResponse(employees, employees.size());

        String number = params.get("number");
        String genderid= params.get("genderid");
        String fullname= params.get("fullname");
        String designationid= params.get("designationid");
        String nic= params.get("nic");

        Stream<Employee> estream = employees.stream();

        if(designationid!=null) estream = estream.filter(e -> e.getDesignation().getId()==Integer.parseInt(designationid));
        if(genderid!=null) estream = estream.filter(e -> e.getGender().getId()==Integer.parseInt(genderid));
        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));
        if(nic!=null) estream = estream.filter(e -> e.getNic().contains(nic));
        if(fullname!=null) estream = estream.filter(e -> e.getFullname().contains(fullname));

        employees = estream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(employees, employees.size());

    }

    @GetMapping(path ="/list",produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Employee>>> get() {

        List<Employee> employees = this.employeedao.findAllNameId();

        employees = employees.stream().map(
                employee -> {
                    return new Employee(employee.getId(), employee.getCallingname());
                }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(employees, employees.size());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('Employee-Insert')")
    public ResponseEntity<APISuccessResponse<Employee>> add(@RequestBody Employee employee){

        if(employeedao.findByNumber(employee.getNumber())!=null)
            throw new ResourceExistsException("Employee already exists with this Number: " + employee.getNumber());
        if(employeedao.findByNic(employee.getNic())!=null)
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());

        Employee savedEmployee =  employeedao.save(employee);

        return APIResponseBuilder.postResponse(savedEmployee,savedEmployee.getId());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('Employee-Update')")
    public ResponseEntity<APISuccessResponse<Employee>> update(@RequestBody Employee employee){

        Employee emp1 = employeedao.findByNumber(employee.getNumber());
        Employee emp2 = employeedao.findByNic(employee.getNic());

        if(emp1!=null && !Objects.equals(employee.getId(), emp1.getId()))
            throw new ResourceExistsException("Employee already exists with this Number: " + employee.getNumber());
        if(emp2!=null && !Objects.equals(employee.getId(), emp2.getId()))
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());

        Employee updatedEmployee =  employeedao.save(employee);

        return APIResponseBuilder.putResponse(updatedEmployee,updatedEmployee.getId());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APISuccessResponse<Employee>> delete(@PathVariable Integer id){

        Employee emp1 = employeedao.findByMyId(id);

        if(emp1==null) throw new ResourceNotFoundException("Employee not exists with this id: " + id);

        employeedao.delete(emp1);

        return APIResponseBuilder.deleteResponse(id);
    }


}




