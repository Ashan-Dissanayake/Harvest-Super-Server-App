package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Brand;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.BrandService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Brand>>> get() {
        List<Brand> brandes = brandService.getBrands();
        return APIResponseBuilder.getResponse(brandes, brandes.size());
    }

}


