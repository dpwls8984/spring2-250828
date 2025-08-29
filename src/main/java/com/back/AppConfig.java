package com.back;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
public class AppConfig {

    @Autowired //필드 주입
    //생성할 때 안받고 추후에 받음
    @Lazy
    //순환참조를 피하기 위해 @Lazy를 붙여준다.
    //LAZY란? 스프링 컨테이너가 해당 빈을 처음 주입할 때 실제 객체 대신 프록시 객체를 주입하는 방식
    private AppConfig self;

    @Bean
    public ApplicationRunner myApplicationRunner3() {
        return args -> {

            //리얼 객체의 메서드 호출
            this.work1();
            this.work2();

            //프록시 객체의 메서드 호출
           self.work1();
           self.work2();
        };
    }

    @Transactional
    public void work1() {
        System.out.println("work1");
    }

    @Transactional
    //트랜잭션과 프록시의 관계는? 트랜잭션이 붙은 메서드를 호출할 때, 스프링은 실제 객체 대신 프록시 객체를 통해 호출을 가로채고,
    // 트랜잭션을 시작하고, 메서드 실행 후 트랜잭션을 커밋하거나 롤백하는 작업을 수행한다.
    public void work2() {
        System.out.println("work2");
    }
}
