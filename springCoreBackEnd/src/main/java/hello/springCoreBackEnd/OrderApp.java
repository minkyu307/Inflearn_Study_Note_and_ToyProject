package hello.springCoreBackEnd;

import hello.springCoreBackEnd.member.Grade;
import hello.springCoreBackEnd.member.Member;
import hello.springCoreBackEnd.member.MemberService;
import hello.springCoreBackEnd.member.MemberServiceImpl;
import hello.springCoreBackEnd.order.Order;
import hello.springCoreBackEnd.order.OrderService;
import hello.springCoreBackEnd.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
