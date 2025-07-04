package lk.earth.earthuniversity.service;

import lk.earth.earthuniversity.dao.SubcategoryDao;
import lk.earth.earthuniversity.model.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryDao subcategorydao;

    public List<Subcategory> getSubcategories() {

        List<Subcategory> subcategories = this.subcategorydao.findAll();

        return subcategories.stream().map(
                subcategory -> {
                    Subcategory d = new Subcategory();
                    d.setId(subcategory.getId());
                    d.setName(subcategory.getName());
                    return d;
                }
        ).collect(Collectors.toList());
    }

}
