package com.jeantravassos.subscriptionsservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    private String id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private Integer gender;

    @Email(message = "Enter a valid email")
    @Column(nullable = false, unique = true)
    @NonNull
    private String email;

    @Past
    @Column(nullable = false)
    @NonNull
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @NonNull
    private Boolean consent;

    @Column(nullable = false)
    @NonNull
    private Long newsletterId;

}