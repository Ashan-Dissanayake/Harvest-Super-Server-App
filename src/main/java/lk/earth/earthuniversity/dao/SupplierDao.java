package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Supplier;
import lk.earth.earthuniversity.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {


}

