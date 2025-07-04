package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.EmployeeService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired private EmployeeService employeeService;

    @GetMapping(produces = "application/json")
//  @PreAuthorize("hasAuthority('employee-select')")
    public ResponseEntity<APISuccessResponse<List<Employee>>> get(@RequestParam HashMap<String, String> params) {
        List<Employee> employees = this.employeeService.getEmployees(params);
        return APIResponseBuilder.getResponse(employees, employees.size());
    }

    @GetMapping(path ="/list",produces = "application/json")
//  @PreAuthorize("hasAuthority('employee-select-list')")
    public ResponseEntity<APISuccessResponse<List<Employee>>> get() {
        List<Employee> employees = this.employeeService.getEmployeesLsit();
        return APIResponseBuilder.getResponse(employees, employees.size());
    }

    @PostMapping
//  @PreAuthorize("hasAuthority('Employee-Insert')")
    public ResponseEntity<APISuccessResponse<Employee>> add(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.createEmployee(employee);
        return APIResponseBuilder.postResponse(savedEmployee, savedEmployee.getId());
    }

    @PutMapping
//  @PreAuthorize("hasAuthority('Employee-Update')")
    public ResponseEntity<APISuccessResponse<Employee>> update(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return APIResponseBuilder.putResponse(updatedEmployee, updatedEmployee.getId());
    }

    @DeleteMapping("/{id}")
    //  @PreAuthorize("hasAuthority('Employee-Update')")
    public ResponseEntity<APISuccessResponse<Employee>> delete(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return APIResponseBuilder.deleteResponse(id);
    }


}




