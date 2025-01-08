package com.exaple.demo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {
    private UUID id;
    private String name;
    private String email;
}
