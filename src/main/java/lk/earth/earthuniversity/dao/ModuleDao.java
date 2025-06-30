package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ModuleDao extends JpaRepository<Module,Integer> {
}
