package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PurchaseorderDao;
import lk.earth.earthuniversity.dao.PurchaseorderDao;
import lk.earth.earthuniversity.entity.Purchaseorder;
import lk.earth.earthuniversity.entity.Purchaseorder;
import lk.earth.earthuniversity.entity.Purchaseorderitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchaseorder")
public class PurchaseorderController {

    @Autowired private PurchaseorderDao purchaseorderdao;

    @GetMapping(produces = "application/json")
    public List<Purchaseorder> get(@RequestParam HashMap<String,String> params) {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();
        if(params.isEmpty()) return purchaseorders;

        String ponumber = params.get("number");
        String doplaced = params.get("doplaced");
        String statusid = params.get("statusid");

        Stream<Purchaseorder> spurchaseorders = purchaseorders.stream();

        if(!ponumber.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getNumber().equals(ponumber));
        if(!doplaced.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getDoplaced().toString().equals(doplaced));
        if(!statusid.isEmpty()) spurchaseorders = spurchaseorders.filter((p)-> p.getPurchaseorderstatus().getId()== Integer.parseInt(statusid));

        return spurchaseorders.collect(Collectors.toList());

    }

    @GetMapping(path ="/list", produces = "application/json")
    public List<Purchaseorder> get() {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();

        purchaseorders = purchaseorders.stream().map(
                purchaseorder -> { Purchaseorder i = new Purchaseorder();
                    i.setId(purchaseorder.getId());
                    i.setNumber(purchaseorder.getNumber());
                    return i; }
        ).collect(Collectors.toList());

        return purchaseorders;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Purchaseorder purchaseorder) {

        HashMap<String, String> response = new HashMap<>();
        String errors = "";

        for (Purchaseorderitem pi : purchaseorder.getPurchaseorderitems()) pi.setPurchaseorder(purchaseorder);

        purchaseorderdao.save(purchaseorder);

        response.put("id", String.valueOf(purchaseorder.getId()));
        response.put("url", "/purchaseorders/" + purchaseorder.getId());
        response.put("errors", errors);

        return response;

    }


}


