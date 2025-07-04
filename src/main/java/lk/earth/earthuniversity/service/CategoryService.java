package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.CategoryDao;
import lk.earth.earthuniversity.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categorydao;

    public List<Category> getCategories() {

        List<Category> categoryes = this.categorydao.findAll();

        return categoryes.stream().map(
                category -> {
                    Category d = new Category();
                    d.setId(category.getId());
                    d.setName(category.getName());
                    d.setCategorybrands(category.getCategorybrands());
                    return d;
                }
        ).collect(Collectors.toList());
    }


}
