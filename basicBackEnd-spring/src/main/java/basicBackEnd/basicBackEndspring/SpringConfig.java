package basicBackEnd.basicBackEndspring;

import basicBackEnd.basicBackEndspring.repository.JdbcMemberRepository;
import basicBackEnd.basicBackEndspring.repository.MemberRepository;
import basicBackEnd.basicBackEndspring.repository.MemoryMemberRepository;
import basicBackEnd.basicBackEndspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*컨피규레이션 어노테이션은 컴포넌트스캔 방식과 달리 직접 스프링 빈을 등록하는 방식이다. */
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
