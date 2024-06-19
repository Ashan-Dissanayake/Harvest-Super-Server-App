package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.entity.Item;
import lk.earth.earthuniversity.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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

        sstream = sstream.peek((s)->s.getSupplies().forEach((sp)-> sp.setItem(new Item(sp.getItem().getId(),sp.getItem().getName()))));

        if(params.isEmpty())  return sstream.collect(Collectors.toList());

        String suppliercode = params.get("suppliercode");
        String supplierstatusid= params.get("supplierstatusid");
        String suppliertypeid= params.get("suppliertypeid");

        if(suppliercode!=null)sstream = sstream.filter(s->s.getName().contains(suppliercode));
        if(supplierstatusid!=null)sstream = sstream.filter(s->s.getSupplierstatus().getId()==Integer.parseInt(supplierstatusid));
        if(suppliertypeid!=null)sstream = sstream.filter(s->s.getSuppliertype().getId()==Integer.parseInt(suppliertypeid));

        return sstream.collect(Collectors.toList());

    }
    
}




