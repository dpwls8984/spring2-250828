package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;
    private final MemberService memberService;
    private final WiseSayingService wiseSayingService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            self.work1();
            self.work2();
            work3();
        };
    }

    void work1() {
        if(memberService.count() > 0) return;

        Member member1 = memberService.join("systemUser", "시스템");
        Member member2 = memberService.join("adminUser", "관리자");
        Member member3 = memberService.join("user1", "유저1");
        Member member4 = memberService.join("user2", "유저2");
        Member member5 = memberService.join("user3", "유저3");

    }

    @Transactional
    //트랜잭션이 작동되려면 프록시를 거쳐야한다
    void work2() {
        Member member4 = memberService.findByUsername("user2").get();
        member4.setNickname("new user2");
    }

    void work3(){
        if(wiseSayingService.count() > 0) return;

        wiseSayingService.write("명언1", "작가1");
        wiseSayingService.write("명언2", "작가2");
        wiseSayingService.write("명언3", "작가3");
        wiseSayingService.write("명언4", "작가4");
        wiseSayingService.write("""
                        - 너 할 수 있다고 믿든지, 할 수 없다고 믿든지,
                        - 어쨌든 믿는 대로 될 것이다.
                        """
                , "작가5");
    }

}
