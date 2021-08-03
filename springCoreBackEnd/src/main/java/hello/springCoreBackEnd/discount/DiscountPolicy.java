package hello.springCoreBackEnd.discount;

import hello.springCoreBackEnd.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
