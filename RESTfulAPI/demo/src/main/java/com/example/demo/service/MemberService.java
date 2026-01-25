package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.model.Member;
import com.example.demo.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 사용자 등록하기
    public MemberResponse create(MemberRequest memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .enabled(true).build();
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    // 사용자 전체 조회
    public List<MemberResponse> findAll() {
        // 람다 이용하여 코드 양 줄이고 가독성 높이기
        return memberRepository
                .findAll()
                .stream()
                .map(this::mapToMemberResponse)
                .toList();
    }

//        List<Member> members = memberRepository.findAll();

//        List<MemberResponse> memberResponses = new ArrayList<>();
//        for (Member member : members) {
//            MemberResponse memberResponse = mapToMemberResponse(member);
//            memberResponses.add(memberResponse);
//        }
//        return memberResponses;
//    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToMemberResponse(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        // DTO 객체 - memberResponse객체를 반환
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }

}
