package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Employee;
import lk.earth.earthuniversity.entity.Supplier;
import lk.earth.earthuniversity.entity.Supplierstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {

    @Query("select s from Supplier s where s.regno=:regno")
    Supplier findByRegno(@Param("regno")String regno);

    @Query("select  s from  Supplier  s where  s.name=:name")
    Supplier findByName(@Param("name")String name);

    @Query("select s from Supplier s where s.id=:id")
    Supplier findByMyId(@Param("id")Integer id);
}

