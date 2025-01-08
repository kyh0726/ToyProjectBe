package com.exaple.demo.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class MemberRequestDto {
    private UUID id;
    private String name;
    private String email;
}
