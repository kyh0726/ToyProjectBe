package com.exaple.demo.user.service;

import com.exaple.demo.exception.EmailAlreadyExistsException;
import com.exaple.demo.user.domain.Member;
import com.exaple.demo.user.dto.request.MemberRequestDto;
import com.exaple.demo.user.dto.response.MemberResponseDto;
import com.exaple.demo.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final 이 붙은 필드에 대해 생성자를 자동으로 생성하는 역할
@Service
public class MemberService {
    private final MemberRepository userRepository;

    public List<MemberResponseDto> getAllMembers() {
        List<Member> members = userRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getName(), member.getEmail()))
                .collect(Collectors.toList());
    }

    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getEmail() == null) {
            throw new IllegalArgumentException("Name and email must not be null");
        }
        // 이메일 중복 처리 로직
        boolean emailExists = userRepository.existsByEmail(requestDto.getEmail());
        if (emailExists) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        // User 엔티티 생성
        Member member = new Member();
        member.setId(UUID.randomUUID()); // 난수로 ID 설정
        member.setName(requestDto.getName());
        member.setEmail(requestDto.getEmail());

        // 데이터 저장
        Member savedUser = userRepository.save(member);

        // 결과를 DTO로 변환하여 반환
        return new MemberResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());

    }

    public MemberResponseDto getMemberById(UUID id) {
        Member member = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail());
    }

    public MemberResponseDto updateMember(UUID id, MemberRequestDto requestDto) {
        Member member = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        member.setName(requestDto.getName());
        member.setEmail(requestDto.getEmail());

        Member updatedUser = userRepository.save(member);


        return new MemberResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    public void deleteMember(UUID id){
        Member member = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));

        // 회원 삭제
        userRepository.delete(member);
    }
}
