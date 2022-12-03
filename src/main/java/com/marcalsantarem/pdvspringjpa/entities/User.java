package com.marcalsantarem.pdvspringjpa.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tb")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
		
}
