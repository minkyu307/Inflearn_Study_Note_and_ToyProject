package hello.springCoreBackEnd;

import hello.springCoreBackEnd.discount.DiscountPolicy;
import hello.springCoreBackEnd.discount.FixDiscountPolicy;
import hello.springCoreBackEnd.discount.RateDiscountPolicy;
import hello.springCoreBackEnd.member.MemberRepository;
import hello.springCoreBackEnd.member.MemberService;
import hello.springCoreBackEnd.member.MemberServiceImpl;
import hello.springCoreBackEnd.member.MemoryMemberRepository;
import hello.springCoreBackEnd.order.OrderService;
import hello.springCoreBackEnd.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*AppConfig는 서비스, 저장소, 주문, 할인등의 구체화된 클래스들의 객체를 생성해주고
* 서비스와 주문의 구체화 클래스의 변수 주입할때 각 객체 인스턴스의 참조를 생성자를 통해서 주입한다. */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
