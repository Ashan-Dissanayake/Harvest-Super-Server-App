package lk.earth.earthuniversity.dao;


import lk.earth.earthuniversity.entity.Purchaseorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PurchaseorderDao extends JpaRepository<Purchaseorder,Integer> {

    Purchaseorder findByNumber(String number);

    @Query("select i from Purchaseorder i where i.id=:id")
    Purchaseorder findByMyId(@Param("id")Integer id);


}

