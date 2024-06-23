package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.entity.Subcategory;
import lk.earth.earthuniversity.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDao supplierdao;

    @GetMapping(produces = "application/json")
    public List<Supplier> get(@RequestParam HashMap<String, String> params) {

        Stream<Supplier> sstream = this.supplierdao.findAll().stream();
        sstream = sstream.peek((s)->s.getSupplies().forEach((sp)->sp.setSubcategory(new Subcategory(sp.getSubcategory().getId(),sp.getSubcategory().getName()))));

        if (params.isEmpty()) return sstream.collect(Collectors.toList());

        String regno = params.get("regno");
        String suppliertypeid = params.get("suppliertypeid");
        String supplierstatusid = params.get("supplierstatusid");
        String doregistered = params.get("doregistered");

        if (regno != null) sstream = sstream.filter(s -> s.getRegno().contains(regno));
        if (suppliertypeid != null) sstream = sstream.filter(s -> s.getSuppliertype().getId()==Integer.parseInt(suppliertypeid));
        if (supplierstatusid != null) sstream = sstream.filter(u -> u.getSupplierstatus().getId() == Integer.parseInt(supplierstatusid));
        if (doregistered != null) sstream = sstream.filter(u -> u.getDoregistered().toString().contains(doregistered));

        return sstream.collect(Collectors.toList());
    }
    
}
