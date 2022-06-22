package com.jeantravassos.publicservice.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {

    private String id;

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