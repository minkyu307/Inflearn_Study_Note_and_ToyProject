package hello.springCoreBackEnd.order;

import hello.springCoreBackEnd.discount.DiscountPolicy;
import hello.springCoreBackEnd.discount.FixDiscountPolicy;
import hello.springCoreBackEnd.discount.RateDiscountPolicy;
import hello.springCoreBackEnd.member.Member;
import hello.springCoreBackEnd.member.MemberRepository;
import hello.springCoreBackEnd.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    /*하단의 두 의존관계는 인터페이스 뿐만 아니라 구체화된 클래스에도 의존하고있기 때문에
    * DI,OCP를 위반한다. */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /*하단의 두 의존은 구체화에 의존하지않아 DI,OCP를 위반하지 않지만 구현체가 없기때문에 AppConfig
    * 를 사용하여 주입해줘야한다.  */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
