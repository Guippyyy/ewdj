package com.project.ewdj.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormDto {
    private Long id;

    @NotEmpty(message = "Please enter a valid Title.")
    private String bookName;

    @NotEmpty(message = "Please enter a valid author.")
    private String author;

    @NotEmpty(message = "Please enter a valid ISBN.")
    private String isbnCode;

    @NotEmpty(message = "Please enter a valid place code.")
    private String pc1;

    @NotEmpty(message = "Please enter a valid place code.")
    private String pc2;

    @NotEmpty(message = "Please enter a valid location.")
    private String location;
}
