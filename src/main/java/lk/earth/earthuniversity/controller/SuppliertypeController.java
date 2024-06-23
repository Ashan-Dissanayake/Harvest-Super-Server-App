package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SuppliertypeDao;
import lk.earth.earthuniversity.entity.Suppliertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/supplierstypes")
public class SuppliertypeController {

    @Autowired
    private SuppliertypeDao suppliertypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Suppliertype> get() {

        List<Suppliertype> suppliertypes = this.suppliertypedao.findAll();

        suppliertypes = suppliertypes.stream().map(
                suppliertype -> { Suppliertype d = new Suppliertype();
                    d.setId(suppliertype.getId());
                    d.setName(suppliertype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return suppliertypes;

    }

}


