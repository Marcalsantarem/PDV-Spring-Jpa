package com.marcalsantarem.pdvspringjpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_tb")
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "A descrição do produto é obrigatória!")
    private String description;

    @Column(length = 20, precision = 20, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

}
