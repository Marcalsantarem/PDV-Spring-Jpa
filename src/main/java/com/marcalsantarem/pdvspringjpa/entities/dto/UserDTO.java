package com.marcalsantarem.pdvspringjpa.entities.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;
    private boolean isEnabled;
    
}