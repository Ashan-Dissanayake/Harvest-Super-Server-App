package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.GenderDao;
import lk.earth.earthuniversity.model.entity.Gender;
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
@RequestMapping(value = "/genders")
public class GenderController {

    @Autowired
    private GenderDao genderdao;

    @GetMapping(path ="/list",produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Gender>>> get() {

        List<Gender> genders = this.genderdao.findAll();

        genders = genders.stream().map(
                gender -> { Gender g = new Gender();
                            g.setId(gender.getId());
                            g.setName(gender.getName());
                            return g; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(genders, genders.size());

    }

}


