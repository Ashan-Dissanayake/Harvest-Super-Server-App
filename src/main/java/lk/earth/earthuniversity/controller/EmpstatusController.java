package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Empstatus;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.EmpstatusService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/employeestatuses")
public class EmpstatusController {

    @Autowired
    private EmpstatusService empstatusService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Empstatus>>> get() {
        List<Empstatus> empstatuses = empstatusService.getEmpStatus();
        return APIResponseBuilder.getResponse(empstatuses, empstatuses.size());
    }

}


