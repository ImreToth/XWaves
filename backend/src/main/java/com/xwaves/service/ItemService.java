
package com.xwaves.service;


import com.xwaves.domain.Item;
import com.xwaves.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
     private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
     public List<Item> getAll() {
        return itemRepository.findAll();
    }
    
    public Item getByName(String name) {
        return itemRepository.findByName(name);
    }
    
    public Item getById(long id) {
        return itemRepository.findById(id);
    }
    
}
