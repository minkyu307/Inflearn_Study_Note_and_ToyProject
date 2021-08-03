package hello.springCoreBackEnd.order;

import hello.springCoreBackEnd.discount.DiscountPolicy;
import hello.springCoreBackEnd.discount.FixDiscountPolicy;
import hello.springCoreBackEnd.member.Member;
import hello.springCoreBackEnd.member.MemberRepository;
import hello.springCoreBackEnd.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
