package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.DesignationDao;
import lk.earth.earthuniversity.model.entity.Designation;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.DesignationService;
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
@RequestMapping(value = "/designations")
public class DesignationController {

    @Autowired
    private DesignationDao designationdao;

    @Autowired private DesignationService designationService;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Designation>>> get() {
        List<Designation> designationes = designationService.getDesignation();
        return APIResponseBuilder.getResponse(designationes, designationes.size());
    }


}


