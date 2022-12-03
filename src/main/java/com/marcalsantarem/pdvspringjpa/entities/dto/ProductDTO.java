package com.marcalsantarem.pdvspringjpa.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
	
	private Long productid;
	private int quantity;
	
}
