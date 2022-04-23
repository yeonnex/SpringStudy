package me.yeonnex.jpashop;

import me.yeonnex.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false) // 롤백하지 않음!
    void crud(){
        // save
        Member member = Member.builder().name("chaechae").build();
        Long id = memberRepository.save(member);
        Assertions.assertThat(member.getId()).isEqualTo(memberRepository.save(member));

        Member findMember = memberRepository.find(id);
        // find
        Assertions.assertThat(findMember).isEqualTo(member); // true
        System.out.println("member == findMember " + (member == findMember)); //true

    }

}