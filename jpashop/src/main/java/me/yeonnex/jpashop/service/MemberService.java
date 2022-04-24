package me.yeonnex.jpashop.service;

import me.yeonnex.jpashop.domain.Member;
import me.yeonnex.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

//JPA 의 로직들은 가급적 트랜잭션 안에서 실행되는 것이 좋다
@Transactional(readOnly = true) // 읽기의 경우, 가급적이면 readOnly=true 로 주어 더티체킹 등의 성능 최적화가 가능하게 하자!
public class MemberService {
    private final MemberRepository memberRepository;

    // 생성자 주입을 쓰자! - 테스트 용이해짐 (목객체로 갈아끼기 쉬움)
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    @Transactional // 기본값이 readOnly=false. 읽기가 아닌 쓰기 작업의 경우, readOnly=true 는 절대 안됨. 데이터 변경이 안됨.
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember.getId() != null){ // 만약 같은 이메일이 Member 에 있다면
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    /**
     * 회원 목록 조회
     */
    public List<Member> findAllUser(){
        return memberRepository.findAll();
    }

    /**
     * 유니크키인 email 로 회원 조회
     */
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
