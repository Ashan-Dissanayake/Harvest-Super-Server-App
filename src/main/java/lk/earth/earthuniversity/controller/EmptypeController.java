package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmptypeDao;
import lk.earth.earthuniversity.model.entity.Emptype;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/empolyeetypes")
public class EmptypeController {

    @Autowired private EmptypeDao emptypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Emptype>>> get() {

        List<Emptype> emptypes = this.emptypedao.findAll();

        emptypes = emptypes.stream().map(
                emptype -> { Emptype d = new Emptype();
                    d.setId(emptype.getId());
                    d.setName(emptype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(emptypes, emptypes.size());

    }

}


