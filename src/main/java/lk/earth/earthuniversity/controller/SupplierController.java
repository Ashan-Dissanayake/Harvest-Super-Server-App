package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Item;
import lk.earth.earthuniversity.model.entity.Subcategory;
import lk.earth.earthuniversity.model.entity.Supplier;
import lk.earth.earthuniversity.model.entity.Supply;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<APISuccessResponse<List<Supplier>>> get(@RequestParam HashMap<String, String> params) {

        List<Supplier> suppliers = this.supplierdao.findAll();

        if (params.isEmpty()) return APIResponseBuilder.getResponse(suppliers, suppliers.size());

        String regno = params.get("regno");
        String suppliertypeid = params.get("suppliertypeid");
        String supplierstatusid = params.get("supplierstatusid");
        String doregistered = params.get("doregistered");

        Stream<Supplier>  sstream =suppliers.stream();

        if (params.containsKey("regno")) sstream = sstream.filter(s -> s.getRegno().contains(regno));
        if (doregistered != null) sstream = sstream.filter(u -> u.getDoregistered().toString().contains(doregistered));
        if (suppliertypeid != null) sstream = sstream.filter(s -> s.getSuppliertype().getId() == Integer.parseInt(suppliertypeid));
        if (supplierstatusid != null) sstream = sstream.filter(u -> u.getSupplierstatus().getId() == Integer.parseInt(supplierstatusid));

        suppliers = sstream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(suppliers, suppliers.size());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Supplier>>> get() {

        List<Supplier> suppliers = this.supplierdao.findAllByIdAndName();

        suppliers = suppliers.stream().map(
                supplier -> new Supplier(supplier.getId(), supplier.getName())
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(suppliers, suppliers.size());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<Supplier>> add(@RequestBody Supplier supplier) {

        for (Supply s : supplier.getSupplies()) s.setSupplier(supplier);

        if (supplierdao.findByRegno(supplier.getRegno()) != null)
            throw new ResourceExistsException("Item already exists with this Reg No: " + supplier.getRegno());
        if (supplierdao.findByName(supplier.getName()) != null)
            throw new ResourceExistsException("Item already exists with this Existing Name: " + supplier.getName());

        Supplier savedSupplier = supplierdao.save(supplier);

        return APIResponseBuilder.postResponse(savedSupplier,savedSupplier.getId());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<Supplier>> update(@RequestBody Supplier supplier) {

        Supplier extsup = supplierdao.findByRegno(supplier.getRegno());

        if (extsup == null)
            throw new ResourceNotFoundException("Supplier not exists with this name: " + supplier.getName());

        if (supplier.getSupplies() != null) {
            extsup.getSupplies().clear();
            supplier.getSupplies().forEach(s -> s.setSupplier(extsup));
            extsup.getSupplies().addAll(supplier.getSupplies());
        }

        Supplier updatedSupplier = supplierdao.save(extsup);

        return APIResponseBuilder.putResponse(updatedSupplier,updatedSupplier.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APISuccessResponse<Supplier>> delete(@PathVariable Integer id) {

        Supplier sup = supplierdao.findByMyId(id);

        if (sup == null)
            throw new ResourceNotFoundException("Supplier not exists with this id: " + id);

        supplierdao.delete(sup);

        return APIResponseBuilder.deleteResponse(id);
    }


}
