package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PurchaseorderstatusDao;
import lk.earth.earthuniversity.model.entity.Purchaseorderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/purchaseorderstatuses")
public class PurchaseorderstatusController {

    @Autowired
    private PurchaseorderstatusDao purchaseorderstatusdao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Purchaseorderstatus> get() {

        List<Purchaseorderstatus> purchaseorderstatuses = this.purchaseorderstatusdao.findAll();

        purchaseorderstatuses = purchaseorderstatuses.stream().map(
                purchaseorderstatus -> { Purchaseorderstatus i = new Purchaseorderstatus();
                    i.setId(purchaseorderstatus.getId());
                    i.setName(purchaseorderstatus.getName());
                    return i; }
        ).collect(Collectors.toList());

        return purchaseorderstatuses;

    }

}


