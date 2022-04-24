## [⚠️엔티티 설계시 주의점]
**1. 엔티티에는 가급적 setter 를 사용하지 말자**

**2. 모든 연관관계는 지연로딩으로 설정하자**

- 즉시 로딩(EAGER)은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.

  - 특히 `JPQL`을 실행할 때 N+1 문제가 자주 발생한다
    - select o from order o; -> `실제 SQL 발생` select * from Order;
    - 기대한 쿼리는 Order 테이블에서 주문들을 가져오는 하나의 쿼리`(1)`였지만
    - Member 가 EAGER 로 설정되어있는 바람에 각 Order 의 Member 를 가져오는 쿼리도 수행됨`(N)`
    - 만약 Order 가 10000개 였다면, Order 를 가져오는 하나의 쿼리만 수행되는게 아니라,

      각 Order 의 Member 를 가져오는 10000개의  쿼리가 또 발생하는 것이다.
- **고로, 실무에서 모든 연관관계는 지연로딩(LAZY)으로 설정해야 한다.**
- 피치 못할 사정으로 연관된 엔티티를 함께 DB 에서 조회해야 한다면, fetch join 또는 엔티티 그래프를 사용한다
- @OneToOne, @ManyToOne 관계는 기본이 즉시로딩이므로, 직접 지연로딩으로 설정해주어야 한다.
- **즉 "XToOne" 시리즈들은 기본이 EAGER 이므로 재설정이 필요!**
- @OneToMany 의 기본값은 지연로딩이지만 명시하여 코딩하는 것이 좋다

## [엔티티 설계시 🍯팁]

> @ManyToOne 이 쓰인 곳을 한번에 찾아 LAZY 로 바꾸고 싶다면!

`ctrl + f + shift` 로 @ManyToOne 이 쓰인 모든 곳을 찾아낼 수 있다.

![img](/Users/yeonnex/Desktop/:Users:yeonnex:Study:SpringStudy:jpashop:.png)



