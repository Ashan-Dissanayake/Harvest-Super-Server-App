package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Unittype;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.UnittypeService;
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
@RequestMapping(value = "/unittypes")
public class UnittypeController {

    @Autowired
    private UnittypeService unittypeService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Unittype>>> get() {
        List<Unittype> unittypes = unittypeService.getUnittypes();
        return APIResponseBuilder.getResponse(unittypes, unittypes.size());
    }
}


