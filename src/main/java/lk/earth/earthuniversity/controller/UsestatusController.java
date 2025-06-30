package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.UsestatusDao;
import lk.earth.earthuniversity.model.entity.Brand;
import lk.earth.earthuniversity.model.entity.Usestatus;
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
@RequestMapping(value = "/userstatuses")
public class UsestatusController {

    @Autowired
    private UsestatusDao usestatusdao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Usestatus>>> get() {

        List<Usestatus> usestatuses = this.usestatusdao.findAll();

        usestatuses = usestatuses.stream().map(
                usestatus -> { Usestatus d = new Usestatus();
                    d.setId(usestatus.getId());
                    d.setName(usestatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(usestatuses, usestatuses.size());

    }

}


