package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PaymenttypeDao;
import lk.earth.earthuniversity.model.entity.Paymenttype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/paymenttypes")
public class PaymenttypeController {

    @Autowired
    private PaymenttypeDao paymenttypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Paymenttype> get() {

        List<Paymenttype> paymenttypes = this.paymenttypedao.findAll();

        paymenttypes = paymenttypes.stream().map(
                paymenttype -> { Paymenttype i = new Paymenttype();
                    i.setId(paymenttype.getId());
                    i.setName(paymenttype.getName());
                    return i; }
        ).collect(Collectors.toList());

        return paymenttypes;

    }

}


