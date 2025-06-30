package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.PaymenttypeDao;
import lk.earth.earthuniversity.model.entity.Paymenttype;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<APISuccessResponse<List<Paymenttype>>> get() {

        List<Paymenttype> paymenttypes = this.paymenttypedao.findAll();

        paymenttypes = paymenttypes.stream().map(
                paymenttype -> { Paymenttype i = new Paymenttype();
                    i.setId(paymenttype.getId());
                    i.setName(paymenttype.getName());
                    return i; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(paymenttypes, paymenttypes.size());

    }

}


