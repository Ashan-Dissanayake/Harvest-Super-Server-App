package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.model.entity.Subcategory;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.service.SubcategoryService;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Subcategory>>> get() {
        List<Subcategory> subcategories = subcategoryService.getSubcategories();
        return APIResponseBuilder.getResponse(subcategories, subcategories.size());
    }

}


