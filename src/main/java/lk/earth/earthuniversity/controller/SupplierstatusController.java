package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SupplierstatusDao;
import lk.earth.earthuniversity.entity.Supplierstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/supplierstatuses")
public class SupplierstatusController {

    @Autowired
    private SupplierstatusDao supplierstatusdao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Supplierstatus> get() {

        List<Supplierstatus> supplierstatuses = this.supplierstatusdao.findAll();

        supplierstatuses = supplierstatuses.stream().map(
                supplierstatus -> { Supplierstatus i = new Supplierstatus();
                    i.setId(supplierstatus.getId());
                    i.setName(supplierstatus.getName());
                    return i; }
        ).collect(Collectors.toList());

        return supplierstatuses;

    }

}


