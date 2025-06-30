package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PurchaseorderDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Purchaseorder;
import lk.earth.earthuniversity.model.entity.Purchaseorderitem;
import lk.earth.earthuniversity.model.entity.Supplier;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchaseorders")
public class PurchaseorderController {

    @Autowired private PurchaseorderDao purchaseorderdao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Purchaseorder>>> get(@RequestParam HashMap<String,String> params) {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();

        if(params.isEmpty()) return APIResponseBuilder.getResponse(purchaseorders, purchaseorders.size());

        String ponumber = params.get("number");
        String doplaced = params.get("doplaced");
        String statusid = params.get("statusid");

        Stream<Purchaseorder> spurchaseorders = purchaseorders.stream();

        if(!ponumber.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getNumber().equals(ponumber));
        if(!doplaced.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getDoplaced().toString().equals(doplaced));
        if(!statusid.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getPurchaseorderstatus().getId()== Integer.parseInt(statusid));

        purchaseorders = spurchaseorders.collect(Collectors.toList());

        return  APIResponseBuilder.getResponse(purchaseorders, purchaseorders.size());

    }

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Purchaseorder>>> get() {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();

        purchaseorders = purchaseorders.stream().map(
                purchaseorder -> { Purchaseorder i = new Purchaseorder();
                    i.setId(purchaseorder.getId());
                    i.setNumber(purchaseorder.getNumber());
                    return i; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(purchaseorders, purchaseorders.size());

    }

    @PostMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<Purchaseorder>> add(@RequestBody Purchaseorder purchaseorder) {

        for (Purchaseorderitem pi : purchaseorder.getPurchaseorderitems()) pi.setPurchaseorder(purchaseorder);

        if (this.purchaseorderdao.findByNumber(purchaseorder.getNumber()) != null)
            throw new ResourceExistsException("Purchase order already exists with this No: " + purchaseorder.getNumber());

       Purchaseorder savedPurchaseorder = purchaseorderdao.save(purchaseorder);

        return APIResponseBuilder.postResponse(savedPurchaseorder,savedPurchaseorder.getId());

    }

    @PutMapping
    @Transactional
    public ResponseEntity<APISuccessResponse<Purchaseorder>> update(@RequestBody Purchaseorder purchaseorder) {

        Purchaseorder extPurchaseorder = purchaseorderdao.findByMyId(purchaseorder.getId());

        if (extPurchaseorder == null)
            throw new ResourceNotFoundException("Purchase order not exists with this number: " + purchaseorder.getNumber());

        if (purchaseorder.getPurchaseorderitems() != null) {
            extPurchaseorder.getPurchaseorderitems().clear();
            purchaseorder.getPurchaseorderitems().forEach(p -> p.setPurchaseorder(extPurchaseorder));
            extPurchaseorder.getPurchaseorderitems().addAll(purchaseorder.getPurchaseorderitems());
        }

        Purchaseorder updatedPurchaseorder = this.purchaseorderdao.save(extPurchaseorder);

        return APIResponseBuilder.putResponse(updatedPurchaseorder,updatedPurchaseorder.getId());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APISuccessResponse<Purchaseorder>> delete(@PathVariable Integer id) {

        Purchaseorder purchaseorder = purchaseorderdao.findByMyId(id);

        if (purchaseorder == null)
            throw new ResourceNotFoundException("Purchase order not exists with this id: " + id);

        purchaseorderdao.delete(purchaseorder);

        return APIResponseBuilder.deleteResponse(id);

    }

}


