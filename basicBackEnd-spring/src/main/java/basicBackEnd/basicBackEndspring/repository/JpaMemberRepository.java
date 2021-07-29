package basicBackEnd.basicBackEndspring.repository;

import basicBackEnd.basicBackEndspring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /*build.gradle의 data-jpa문을 통해 엔티티매니저가 만들어지고 여기서는
    * 그것을 연결만 하여 사용한다. 엔티티 매니저는 jdbc에서 conn,stmt,pstmt,rs등등의
    * 역할을 대신한다. */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        /*멤버라는 엔티티와 연결되어있어서 presist문으로 바로 저장됨*/
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
