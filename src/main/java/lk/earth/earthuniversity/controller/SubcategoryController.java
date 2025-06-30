package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SubcategoryDao;
import lk.earth.earthuniversity.model.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryDao subcategorydao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Subcategory> get(@RequestParam HashMap<String, String> params) {

        List<Subcategory> subcategories = this.subcategorydao.findAll();

        if (params.isEmpty())  return  subcategories;

        String categoryid = params.get("categoryid");

        Stream<Subcategory> sstream = subcategories.stream();

        if(categoryid!=null) sstream = sstream.filter((s -> s.getCategory().getId()==Integer.parseInt(categoryid)));

        return sstream.collect(Collectors.toList());

    }
}


