package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Gender;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.GenderService;
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
@RequestMapping(value = "/genders")
public class GenderController {


    @Autowired private GenderService genderService;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Gender>>> get() {
        List<Gender> genderes = genderService.getGender();
        return APIResponseBuilder.getResponse(genderes, genderes.size());
    }

}


