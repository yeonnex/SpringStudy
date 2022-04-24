package me.yeonnex.jpashop.repository;

import me.yeonnex.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em; // 스프링이 엔티티 매니저 주입해줌

    // 회원 등록
    public void save(Member member){
        // persist 로 member 를 영속성 컨텍스트에 넣은 다음, 트랜잭션이 커밋되는 시점에 DB 에 반영된다!
        em.persist(member);
    }

    // id로 회원 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // 이메일로 회원 조회 - 이메일은 중복되지 않음
    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email= :email", Member.class)
                        .setParameter("email", email)
                        .getSingleResult();
    }

    // 모든 회원 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 이름별 회원 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }
}
