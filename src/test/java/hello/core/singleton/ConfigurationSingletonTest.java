package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationSingletonTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberServiceImplMemberRepository = memberServiceImpl.getMemberRepository();
        MemberRepository orderServiceImplMemberRepository = orderServiceImpl.getMemberRepository();
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberServiceImpl -> memberRepository = " + memberServiceImplMemberRepository);
        System.out.println("orderServiceImpl -> memberRepository = " + orderServiceImplMemberRepository);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberServiceImplMemberRepository).isSameAs(memberRepository);
        Assertions.assertThat(orderServiceImplMemberRepository).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
