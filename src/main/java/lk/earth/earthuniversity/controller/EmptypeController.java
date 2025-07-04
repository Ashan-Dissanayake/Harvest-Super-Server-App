package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Emptype;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.EmptypeService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/empolyeetypes")
public class EmptypeController {

    @Autowired
    private EmptypeService emptypeService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Emptype>>> get() {
        List<Emptype> emptypees = emptypeService.getEmptype();
        return APIResponseBuilder.getResponse(emptypees, emptypees.size());
    }

}


