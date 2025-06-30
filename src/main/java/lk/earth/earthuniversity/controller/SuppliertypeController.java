package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SuppliertypeDao;
import lk.earth.earthuniversity.model.entity.Suppliertype;
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
@RequestMapping(value = "/suppliertypes")
public class SuppliertypeController {

    @Autowired
    private SuppliertypeDao suppliertypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Suppliertype>>> get() {

        List<Suppliertype> suppliertypes = this.suppliertypedao.findAll();

        suppliertypes = suppliertypes.stream().map(
                suppliertype -> { Suppliertype d = new Suppliertype();
                    d.setId(suppliertype.getId());
                    d.setName(suppliertype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(suppliertypes, suppliertypes.size());

    }

}


