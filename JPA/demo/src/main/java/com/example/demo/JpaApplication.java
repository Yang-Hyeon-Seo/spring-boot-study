package com.example.demo;

import com.example.demo.model.Member;
import com.example.demo.model.MemberStats;
import com.example.demo.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        memberRepository.save(Member.builder()
                .name("윤서준")
                .email("Seojun@hanbit.co.kr")
                .age(10)
                .build());
        memberRepository.save(Member.builder()
                .name("윤광철")
                .email("KwangCheol@hanbit.co.kr")
                .age(43)
                .build());

        //Example 객체 활용하기
        // AND 조건 - matchingAll()
        Example<Member> example = Example.of(
                Member.builder()
                        .name("윤서준")
                        .age(10)
                        .build(),
                ExampleMatcher.matchingAll());
        List<Member> members = memberRepository.findAll(example);

        // OR 조건 - matchingAny()
        Example<Member> example1 = Example.of(
                Member.builder()
                        .name("윤서준").age(10).build(),
                ExampleMatcher.matchingAny()
        );

        // JPQL 쿼리
        List<Object[]> memberStatsList = memberRepository.getMemberStatsObject();
        for (Object[] ob : memberStatsList){
            String name = (String)ob[0];
            String email = (String)ob[1];
            Long count = (Long) ob[2];
            log.warn("{} {} {}", name, email, count);
        }

        // MemberStats 객체
        List<MemberStats> memberStatsList1 = memberRepository.getMemberStats();
        for (MemberStats memberStats: memberStatsList1) {
            log.warn("{}", memberStats);
        }
    }


}
