package com.jeantravassos.publicservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRequestDto {

    private String firstName;
    private Integer gender;

    @NonNull
    private String email;

    @NonNull
    private LocalDate dateOfBirth;

    @NonNull
    private Boolean consent;

    @NonNull
    private Long newsletterId;

}