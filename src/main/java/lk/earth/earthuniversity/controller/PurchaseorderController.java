package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PurchaseorderDao;
import lk.earth.earthuniversity.dao.PurchaseorderDao;
import lk.earth.earthuniversity.entity.Purchaseorder;
import lk.earth.earthuniversity.entity.Purchaseorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchaseorder")
public class PurchaseorderController {

    @Autowired
    private PurchaseorderDao purchaseorderdao;

    @GetMapping(produces = "application/json")
    public List<Purchaseorder> get() {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();
        return purchaseorders;

    }


    @GetMapping(path ="/list", produces = "application/json")
    public List<Purchaseorder> getList() {

        List<Purchaseorder> purchaseorders = this.purchaseorderdao.findAll();

        purchaseorders = purchaseorders.stream().map(
                purchaseorder -> { Purchaseorder i = new Purchaseorder();
                    i.setId(purchaseorder.getId());
                    i.setNumber(purchaseorder.getNumber());
                    return i; }
        ).collect(Collectors.toList());

        return purchaseorders;

    }

}


