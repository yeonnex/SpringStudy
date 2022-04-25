package me.yeonnex.jpashop.repository;

import lombok.RequiredArgsConstructor;
import me.yeonnex.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {
    public final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){ // 신규 아이템이다!
            em.persist(item);
        } else { // 기존 아이템에 수정 발생!
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
