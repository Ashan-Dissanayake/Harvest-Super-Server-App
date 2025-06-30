package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.RoleDao;
import lk.earth.earthuniversity.model.entity.Role;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleDao roledao;

    @GetMapping(path ="/list",produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Role>>> get() {

        List<Role> roles = this.roledao.findAll();

        roles = roles.stream().map(
                role -> { Role r = new Role();
                            r.setId(role.getId());
                            r.setName(role.getName());
                            return r; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(roles, roles.size());

    }

}


