package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {

}

