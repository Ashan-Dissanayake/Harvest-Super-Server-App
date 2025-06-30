package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CategoryDao;
import lk.earth.earthuniversity.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryDao categorydao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Category> get() {

        List<Category> categories = this.categorydao.findAll();

        categories = categories.stream().map(
                category -> { Category c = new Category();
                    c.setId(category.getId());
                    c.setName(category.getName());
                    c.setCategorybrands(category.getCategorybrands());
                    return c; }
        ).collect(Collectors.toList());

        return categories;

    }

}


