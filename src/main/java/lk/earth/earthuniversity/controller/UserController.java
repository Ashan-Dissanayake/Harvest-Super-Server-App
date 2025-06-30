package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.UserDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.entity.Supplier;
import lk.earth.earthuniversity.model.entity.User;
import lk.earth.earthuniversity.model.entity.Userrole;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired private UserDao userdao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<User>>> get(@RequestParam HashMap<String, String> params) {

        List<User> users = this.userdao.findAll();
        users.forEach(user -> user.setEmployee(new Employee(
                user.getEmployee().getId(),
                user.getEmployee().getCallingname()
        )));

        if (params.isEmpty()) APIResponseBuilder.getResponse(users, users.size());

        String employee = params.get("employee");
        String username = params.get("username");
        String roleid = params.get("roleid");

        Stream<User> ustream = users.stream();

        if (employee != null)ustream = ustream.filter(u -> u.getEmployee().getCallingname().contains(employee));
        if (username != null) ustream = ustream.filter(u -> u.getUsername().contains(username));
        if (roleid != null) ustream = ustream.filter(u -> u.getUserroles().stream().anyMatch(
                ur -> ur.getRole().getId() == Integer.parseInt(roleid)
        ));

        users = ustream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(users, users.size());
    }

    @GetMapping("/empbyuser/{username}")
    public ResponseEntity<APISuccessResponse<Employee>> get(@PathVariable String username) {

        User user = userdao.findByUsername(username);

        if (user == null)
            throw new ResourceNotFoundException("User does not exists with this username: " + username);

        Employee employee = user.getEmployee();

        return APIResponseBuilder.getResponse(employee, 1);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<User>> add(@RequestBody User user){

       if(userdao.findByUsername(user.getUsername())!=null)
           throw new ResourceExistsException("User already exists with this Username: " + user.getUsername());

       for(Userrole u : user.getUserroles()) u.setUser(user);

       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String salt = passwordEncoder.encode(user.getUsername());
        String hashedPassword = passwordEncoder.encode(salt + user.getPassword());
        user.setSalt(salt);
        user.setPassword(hashedPassword);

        User savedUser = userdao.save(user);

        return APIResponseBuilder.postResponse(savedUser,savedUser.getId());

    }

    @PutMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<User>> update(@RequestBody User user) {

        User extUser = userdao.findByUsername(user.getUsername());

        if (extUser == null)
            throw new ResourceNotFoundException("User not exists with this name: " + user.getUsername());

        if (user.getUserroles() != null) {
            extUser.getUserroles().clear();
            user.getUserroles().forEach(u -> u.setUser(extUser));
            extUser.getUserroles().addAll(user.getUserroles());
        }

        User updatedUser = userdao.save(extUser);

        return APIResponseBuilder.putResponse(updatedUser,updatedUser.getId());

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<APISuccessResponse<User>> delete(@PathVariable String username){

        User user = userdao.findByUsername(username);

        if(user==null)
            throw new ResourceNotFoundException("User not exists with this username: " + username);

       userdao.delete(user);

        return APIResponseBuilder.deleteResponse(username);

    }

}
