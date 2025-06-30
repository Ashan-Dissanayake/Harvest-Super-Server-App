package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.BrandDao;
import lk.earth.earthuniversity.model.entity.Brand;
import lk.earth.earthuniversity.model.entity.Empstatus;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired private BrandDao branddao;

    @GetMapping(path ="/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Brand>>> get() {

        List<Brand> brands = this.branddao.findAll();

        brands = brands.stream().map(
                brand -> { Brand b = new Brand();
                    b.setId(brand.getId());
                    b.setName(brand.getName());
                    return b; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(brands, brands.size());
    }

}


