package me.yeonnex.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import me.yeonnex.jpashop.domain.Category;
import me.yeonnex.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
// 앞으로, 데이터 변경을 줘야 할 때는 세터로 접근하지 말고, 비즈니스 로직으로 접근하여 데이터를 변경하자!
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한 테이블에 다 때려박겠다!
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==

    /**
     * 재고 추가
     */
    public void increaseStock(Long quantity){
        this.stockQuantity += quantity;
    }

    /**
     * 재고 감소
     */
    public void decreaseStock(Long quantity) throws NotEnoughStockException {
        Long resultQuantity = 0L;
        resultQuantity = this.stockQuantity - quantity;
        if (resultQuantity < 0){
            throw new NotEnoughStockException("out of stock...");
        }
    }

}
