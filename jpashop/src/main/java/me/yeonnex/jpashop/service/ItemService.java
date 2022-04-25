package me.yeonnex.jpashop.service;

import lombok.RequiredArgsConstructor;
import me.yeonnex.jpashop.domain.item.Item;
import me.yeonnex.jpashop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;

    public void saveItem(Item item){
        repository.save(item);
    }

    public Item findOne(Long id){
        return repository.findOne(id);
    }

    public List<Item> findAllItem(){
        return repository.findAll();
    }
}
