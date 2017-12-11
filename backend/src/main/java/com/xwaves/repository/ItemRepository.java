package com.xwaves.repository;

import com.xwaves.domain.Item;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findById(long id);
    
    Item findByName(String name);

    List<Item> findAll();
}
