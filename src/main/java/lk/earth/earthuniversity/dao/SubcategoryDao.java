package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryDao extends JpaRepository<Subcategory,Integer> {

    @Query("select s from Subcategory s join Category c on s.category.id=c.id join Categorybrand cb on cb.category.id=c.id join Brand  b on cb.brand.id= b.id where b.name=:name")
    List<Subcategory>findSubcategoriesByBrand(@Param("name")String name);

}

