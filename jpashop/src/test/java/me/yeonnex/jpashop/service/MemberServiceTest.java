package me.yeonnex.jpashop.service;
import me.yeonnex.jpashop.domain.Member;
import me.yeonnex.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원가입")
    @Rollback(value = false) // 롤백하지 않고, 커밋해버리기 때문에 jpa 에서 쿼리가 날아가는 것을 볼 수 있음
    public void 회원가입() throws Exception {
        // given
        Member member = Member.builder().email("yeonnex@gmail.com").name("seoyeon").build();
//        em.flush(); -> 바로 커밋을 날려버릴 수 있다!
        // when
         Long joinMember = memberService.join(member); // 중복회원검사 로직, db 에 insert 로직 실행됨
        // then
         assertEquals(joinMember, memberRepository.findOne(joinMember).getId()); // option + enter 로 static 임포트!
    }

    @Test
    @DisplayName("중복_회원_예외")
    public void 중복_회원_예외() throws Exception {
        Member member1 = Member.builder().email("yeonnex@gmail.com").name("pomingbbu").build();
        Member member2 = Member.builder().email("yeonnex@gmail.com").name("dundun").build();

        memberService.join(member1);
        assertThrows(Exception.class,() -> memberService.join(member2),"해당 이메일로 가입된 계졍이 있습니다");

    }


}