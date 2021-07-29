package basicBackEnd.basicBackEndspring.repository;

import basicBackEnd.basicBackEndspring.domain.Member;
import basicBackEnd.basicBackEndspring.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
