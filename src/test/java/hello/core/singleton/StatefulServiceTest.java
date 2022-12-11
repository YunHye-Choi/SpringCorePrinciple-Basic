package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA A사용자가 10000 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB B사용자가 20000 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA A사용자, B사용자가 주문한 금액 각각 조회
        Assertions.assertEquals(userAPrice, 10000);
        Assertions.assertEquals(userBPrice, 20000);


    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}