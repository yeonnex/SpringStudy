package me.yeonnex.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") // 테이블명은 관례상 orders 로!
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id") // DBA 가 선호하는 방식
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK 이름이 member_id 가 된다
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //==[연관관계 편의 메서드 시작]==
    // 양방향 연관관계의 경우, 연관관계 편의 메서드로 값을 세팅해주자.
    // 물론 FK 가 있는 곳에서만 해줘도 테이블 상에는 문제없이 들어가지만,
    // 자바객체에게 값 할당하는 것도 필요하다.
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    //==[연관관계 편의 메서드 끝]

}
