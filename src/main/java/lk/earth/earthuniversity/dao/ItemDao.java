package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDao extends JpaRepository<Item,Integer> {

    @Query("select i from Item i where i.code=:code")
    Item findByItemCode(@Param("code")String code);

    @Query("select i from Item i where i.name=:name")
    Item findByItemName(@Param("name")String name);

    @Query("select i from Item i where i.id=:id")
    Item findByMyId(@Param("id")Integer id);

    @Query("SELECT NEW Item (i.id, i.name) FROM Item i")
    List<Item> findAllNameId();

}

