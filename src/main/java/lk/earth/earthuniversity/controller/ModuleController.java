package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.ModuleDao;
import lk.earth.earthuniversity.model.entity.Gender;
import lk.earth.earthuniversity.model.entity.Module;
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
@RequestMapping(value = "/modules")
public class ModuleController {

    @Autowired
    private ModuleDao moduledao;

    @GetMapping(path ="/list",produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Module>>> get() {

        List<Module> modules = this.moduledao.findAll();

        modules = modules.stream().map(
                module -> { Module m = new Module();
                            m.setId(module.getId());
                            m.setName(module.getName());
                            return m; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(modules, modules.size());

    }

}


