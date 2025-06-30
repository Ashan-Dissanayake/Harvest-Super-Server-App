package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmpstatusDao;
import lk.earth.earthuniversity.model.entity.Empstatus;
import lk.earth.earthuniversity.model.entity.Emptype;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/employeestatuses")
public class EmpstatusController {

    @Autowired private EmpstatusDao empstatusdao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Empstatus>>> get() {

        List<Empstatus> empstatuss = this.empstatusdao.findAll();

        empstatuss = empstatuss.stream().map(
                empstatus -> { Empstatus d = new Empstatus();
                    d.setId(empstatus.getId());
                    d.setName(empstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

       return APIResponseBuilder.getResponse(empstatuss, empstatuss.size());

    }

}


