package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.EmployeeDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired private EmployeeDao employeeDao;

    public List<Employee> getEmployees(HashMap<String, String> params) {

        List<Employee> employees = employeeDao.findAll();

        if(params.isEmpty()) return  employees;

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

       return estream.collect(Collectors.toList());

    }

    public List<Employee> getEmployeesLsit() {
        return employeeDao.findAllNameId().stream()
                .map(emp -> new Employee(emp.getId(), emp.getCallingname()))
                .collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        validateNewEmployee(employee);
        return employeeDao.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        validateEmployeeUpdate(employee);
        return employeeDao.save(employee);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeDao.findByMyId(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not exists with this id: " + id);
        }
        employeeDao.delete(employee);
    }

    private void validateNewEmployee(Employee employee) {
        if (employeeDao.findByNumber(employee.getNumber()) != null) {
            throw new ResourceExistsException("Employee already exists with this Number: " + employee.getNumber());
        }
        if (employeeDao.findByNic(employee.getNic()) != null) {
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());
        }
    }

    private void validateEmployeeUpdate(Employee employee) {
        Employee empByNumber = employeeDao.findByNumber(employee.getNumber());
        Employee empByNic = employeeDao.findByNic(employee.getNic());

        if (empByNumber != null && !Objects.equals(employee.getId(), empByNumber.getId())) {
            throw new ResourceExistsException("Employee already exists with this Number: " + employee.getNumber());
        }
        if (empByNic != null && !Objects.equals(employee.getId(), empByNic.getId())) {
            throw new ResourceExistsException("Employee already exists with this NIC: " + employee.getNic());
        }
    }

}
