package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SubcategoryDao;
import lk.earth.earthuniversity.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryDao subcategorydao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Subcategory> get() {

        List<Subcategory> subcategories = this.subcategorydao.findAll();

        subcategories = subcategories.stream().map(
                subcategory -> { Subcategory s = new Subcategory();
                    s.setId(subcategory.getId());
                    s.setName(subcategory.getName());
                    s.setCategory(subcategory.getCategory());
                    return s; }
        ).collect(Collectors.toList());

        return subcategories;

    }

}


