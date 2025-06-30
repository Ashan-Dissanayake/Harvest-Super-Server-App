package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.OpetypeDao;
import lk.earth.earthuniversity.model.entity.Emptype;
import lk.earth.earthuniversity.model.entity.Opetype;
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
@RequestMapping(value = "/opetypes")
public class OpetypeController {

    @Autowired
    private OpetypeDao opetypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Opetype>>> get() {

        List<Opetype> opetypees = this.opetypedao.findAll();

        opetypees = opetypees.stream().map(
                opetype -> { Opetype d = new Opetype();
                    d.setId(opetype.getId());
                    d.setName(opetype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(opetypees, opetypees.size());

    }

}


