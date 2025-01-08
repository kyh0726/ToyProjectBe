package com.exaple.demo.user.controller;


import com.exaple.demo.user.dto.request.MemberRequestDto;
import com.exaple.demo.user.dto.response.MemberResponseDto;
import com.exaple.demo.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto requestDto) {
        // 서비스 계층 호출
        MemberResponseDto responseDto = memberService.createMember(requestDto);

        // HTTP 201 상태 코드와 함께 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @GetMapping("/{id}")
    public MemberResponseDto getMemberById(@PathVariable UUID id){
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    public MemberResponseDto updateMember(@PathVariable UUID id, @RequestBody MemberRequestDto requestDto) {
        return memberService.updateMember(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponseDto> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
