package ru.job4j.auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null")
    private int id;
    @EqualsAndHashCode.Include
    @NotBlank(message = "Login must be not empty")
    private String login;
    @EqualsAndHashCode.Include
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}",
    message = "Invalid password. Password length must be more than 8 characters. "
            + "Password must contain at least 1 uppercase letter, one lowercase letter and either a special "
            + "character or a number")
    private String password;
}

