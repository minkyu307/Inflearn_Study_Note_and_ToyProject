package basicBackEnd.basicBackEndspring.repository;

import basicBackEnd.basicBackEndspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*리포지토리 어노테이션이 있으면 어플리케이션 실행시 자동으로 스프링 빈으로
 스프링 컨테이너에 등록해준다. */
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//id가 null값이어도 반환가능
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}