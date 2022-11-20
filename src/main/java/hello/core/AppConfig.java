package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // 기존: MemberRepository 구현체를 MemberServiceImpl에서 직접 지정
    // AppConfig 적용: MemberServiceImpl 생성자 파라미터를 통해 MemberRepository 구현체를 지정하게 하고,
    // AppConfig에서 해당 생성자를 통해 어떤 구현체를 사용할 지 지정(주입)한다!
    // 생성자를 통해 객체 주입. 생성자 주입.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
