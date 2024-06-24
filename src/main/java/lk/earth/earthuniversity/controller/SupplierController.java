package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired private SupplierDao supplierdao;

    @GetMapping(produces = "application/json")
    public List<Supplier> get(@RequestParam HashMap<String, String> params) {

        Stream<Supplier> sstream = this.supplierdao.findAll().stream();
        sstream = sstream.peek((s) -> s.getSupplies().forEach((sp) -> sp.setSubcategory(new Subcategory(sp.getSubcategory().getId(), sp.getSubcategory().getName()))));

        if (params.isEmpty()) return sstream.collect(Collectors.toList());

        String regno = params.get("regno");
        String suppliertypeid = params.get("suppliertypeid");
        String supplierstatusid = params.get("supplierstatusid");
        String doregistered = params.get("doregistered");

        if (regno != null) sstream = sstream.filter(s -> s.getRegno().contains(regno));
        if (doregistered != null) sstream = sstream.filter(u -> u.getDoregistered().toString().contains(doregistered));
        if (suppliertypeid != null)
            sstream = sstream.filter(s -> s.getSuppliertype().getId() == Integer.parseInt(suppliertypeid));
        if (supplierstatusid != null)
            sstream = sstream.filter(u -> u.getSupplierstatus().getId() == Integer.parseInt(supplierstatusid));

        return sstream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Supplier supplier) {

        HashMap<String, String> responce = new HashMap<>();
        String errors = "";

        for (Supply s : supplier.getSupplies()) s.setSupplier(supplier);

        if (supplierdao.findByRegno(supplier.getRegno()) != null) errors = errors + "<br> Existing Reg No";
        if (supplierdao.findByName(supplier.getName()) != null) errors = errors + "<br> Existing Name";

        if (errors.isEmpty()) supplierdao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id", String.valueOf(supplier.getId()));
        responce.put("url", "/suppliers/" + supplier.getId());
        responce.put("errors", errors);

        return responce;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> update(@RequestBody Supplier supplier) {
        HashMap<String, String> response = new HashMap<>();

        String errors = "";

        Supplier extsup = supplierdao.findByRegno(supplier.getRegno());

        if (extsup != null) {

            try {
                extsup.getSupplies().clear();
                supplier.getSupplies().forEach(nsupplies -> {
                    nsupplies.setSupplier(extsup);
                    extsup.getSupplies().add(nsupplies);
                });

                BeanUtils.copyProperties(supplier, extsup, "id", "supplies");

                supplierdao.save(extsup);

            } catch (Exception e) {
                errors = errors + e.getMessage();
            }
        } else {
            errors = errors + "<br> Non-Existing Supplier";
        }

        response.put("id", String.valueOf(supplier.getId()));
        response.put("url", "/suppliers/" + supplier.getId());
        response.put("errors", errors);

        return response;
    }



}
