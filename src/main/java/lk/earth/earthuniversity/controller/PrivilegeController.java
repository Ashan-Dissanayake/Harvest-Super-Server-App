package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.PrivilegeDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.entity.Privilege;
import lk.earth.earthuniversity.model.entity.User;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/privileges")
public class PrivilegeController {

    @Autowired
    private PrivilegeDao privilegedao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Privilege>>> get(@RequestParam HashMap<String, String> params) {

        List<Privilege> privileges = this.privilegedao.findAll();

        if(params.isEmpty())  APIResponseBuilder.getResponse(privileges, privileges.size());

        String roleid= params.get("roleid");
        String moduleid= params.get("moduleid");
        String operationid= params.get("operationid");

        Stream<Privilege> pstream = privileges.stream();

        if(roleid!=null) pstream = pstream.filter(p -> p.getRole().getId()==Integer.parseInt(roleid));
        if(moduleid!=null) pstream = pstream.filter(p -> p.getModule().getId()==Integer.parseInt(moduleid));
        if(operationid!=null) pstream = pstream.filter(p -> p.getOperation().getId()==Integer.parseInt(operationid));

        privileges = pstream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(privileges, privileges.size());

    }

    @PostMapping
    public ResponseEntity<APISuccessResponse<Privilege>> add(@RequestBody Privilege privilege){

        Privilege priv = this.privilegedao.findByRoleIdAndModuleIdAndOperationId(
                privilege.getModule().getId(),
                privilege.getRole().getId(),
                privilege.getRole().getId()
        );

        if (priv!=null)
            throw new ResourceExistsException("Privilege already exists with this id: "+privilege.getId());

        Privilege savedPrivilege = privilegedao.save(privilege);
        return APIResponseBuilder.postResponse(savedPrivilege,savedPrivilege.getId());
    }

    @PutMapping
    public ResponseEntity<APISuccessResponse<Privilege>> update(@RequestBody Privilege privilege){

        Privilege priv = this.privilegedao.findByRoleIdAndModuleIdAndOperationId(
                privilege.getModule().getId(),
                privilege.getRole().getId(),
                privilege.getRole().getId()
        );

        if (!Objects.equals(privilege.getId(), priv.getId()))
            throw new ResourceExistsException("Privilege already exists with this id: "+privilege.getId());

       Privilege updatedPrivilege = this.privilegedao.save(privilege);

       return APIResponseBuilder.putResponse(updatedPrivilege,updatedPrivilege.getId());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APISuccessResponse<Privilege>> delete(@PathVariable Integer id){

        Privilege prv = privilegedao.findByMyId(id);

        if(prv==null)
            throw new ResourceNotFoundException("Privilege not exists with this id: " + id);

        privilegedao.delete(prv);

       return APIResponseBuilder.deleteResponse(id);

    }

}


