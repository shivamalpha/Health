package com.develop.recruitmentstatics.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    @NotNull
    @Column(name = "pin_code")
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(name = "covid_status")
    private CovidStatus status = CovidStatus.NEGATIVE;


    public enum CovidStatus {
        NEGATIVE,
        POSITIVE,
        RECOVERED

    }


}