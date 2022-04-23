package me.yeonnex.jpashop;

import me.yeonnex.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId(); // member 전체를 리턴하기보다는, id 만 리턴하기를 권장함
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
