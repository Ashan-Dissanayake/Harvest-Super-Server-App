package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.DesignationDao;
import lk.earth.earthuniversity.model.entity.Designation;
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
@RequestMapping(value = "/designations")
public class DesignationController {

    @Autowired
    private DesignationDao designationdao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Designation>>> get() {

        List<Designation> designations = this.designationdao.findAll();

        designations = designations.stream().map(
                designation -> { Designation d = new Designation();
                    d.setId(designation.getId());
                    d.setName(designation.getName());
                    return d; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(designations, designations.size());


    }

}


