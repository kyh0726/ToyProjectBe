package com.exaple.user;

import com.exaple.demo.user.domain.Member;
import com.exaple.demo.user.dto.request.MemberRequestDto;
import com.exaple.demo.user.dto.response.MemberResponseDto;
import com.exaple.demo.user.repository.MemberRepository;
import com.exaple.demo.user.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;



public class UserServiceTest {
    @Test
    void updateUser_shouldUpdateAndReturnUpdatedUser() {
        // Given: Mock 데이터 준비
        UUID userId = UUID.randomUUID();
        Member existingUser = new Member();
        existingUser.setId(userId); // 난수로 ID 설정
        existingUser.setName("Old Name");
        existingUser.setEmail("old.email@example.com");

        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setName("Updated Name");
        requestDto.setEmail("updated.email@example.com");

        Member updatedUser = new Member();
        updatedUser.setId(userId);
        updatedUser.setName("Updated Name");
        updatedUser.setEmail("updated.email@example.com");

        MemberRepository userRepository = Mockito.mock(MemberRepository.class);
        Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenReturn(updatedUser);

        MemberService memberService = new MemberService(userRepository);


        // When: updateUser 호출
        MemberResponseDto result = memberService.updateMember(userId, requestDto);

        // Then: 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getName()).isEqualTo("Updated Name");
        assertThat(result.getEmail()).isEqualTo("updated.email@example.com");
    }
}
