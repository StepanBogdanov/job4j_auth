package ru.job4j.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @NotNull(message = "Id must be not null")
    private int id;
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}",
            message = "Invalid password. Password length must be more than 8 characters. "
                    + "Password must contain at least 1 uppercase letter, one lowercase letter and either a special "
                    + "character or a number")
    private String password;
}
