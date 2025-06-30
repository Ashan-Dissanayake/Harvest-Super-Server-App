package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {

    @Query("select s from Supplier s where s.regno=:regno")
    Supplier findByRegno(@Param("regno")String regno);

    @Query("select  s from  Supplier  s where  s.name=:name")
    Supplier findByName(@Param("name")String name);

    @Query("select s from Supplier s where s.id=:id")
    Supplier findByMyId(@Param("id")Integer id);

    @Query("select new Supplier(s.id,s.name) from Supplier s")
    List<Supplier>findAllByIdAndName();

}

