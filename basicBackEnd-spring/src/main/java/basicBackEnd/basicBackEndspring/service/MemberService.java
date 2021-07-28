package basicBackEnd.basicBackEndspring.service;

import basicBackEnd.basicBackEndspring.domain.Member;
import basicBackEnd.basicBackEndspring.repository.MemberRepository;
import basicBackEnd.basicBackEndspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*서비스 어노테이션이 있으면 어플리케이션 실행시 스프링 컨테이너에
 스프링 빈으로 자동으로 등록해준다*/
//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //서비스에서 직접 new하지 않고 생성자를 통해 외부에서 인스턴스를 주입해줌 DI라고 한다.
    /*컨트롤러뿐만 아니라 서비스 어노테이션을 가지고 있는 서비스객체도 생성자에 오토와이어드
    * 어노테이션이 있으면 자동으로 해당 인스턴스를 연결시켜준다. 여기선 멤버리포지토리 */
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m ->
        {throw new IllegalStateException("이미 존재하는 회원입니다."); });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
