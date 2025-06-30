package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao extends JpaRepository<Gender,Integer> {

}

