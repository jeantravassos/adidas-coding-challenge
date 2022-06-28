package com.jeantravassos.subscriptionsservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRequestDto {

    private String id;
    private String firstName;
    private Integer gender;

    @NonNull
    @Email
    private String email;

    @NonNull
    private LocalDate dateOfBirth;

    @NonNull
    private Boolean consent;

    @NonNull
    private Long newsletterId;

}