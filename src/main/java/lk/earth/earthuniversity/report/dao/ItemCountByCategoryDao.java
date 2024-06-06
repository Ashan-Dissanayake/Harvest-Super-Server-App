package lk.earth.earthuniversity.report.dao;

import lk.earth.earthuniversity.report.entity.ItemCountByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemCountByCategoryDao extends JpaRepository<ItemCountByCategory,Integer> {

    @Query("SELECT NEW ItemCountByCategory (c.name, COUNT(*)) FROM Item i, Subcategory s, Category c where i.subcategory.id = s.id and s.category.id = c.id group by c.id")
    List<ItemCountByCategory> itemCountByCategory();

}

