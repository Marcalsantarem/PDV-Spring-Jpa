package com.marcalsantarem.pdvspringjpa.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductInfoDTO {

	private Long id;
	private String description;
	private Integer quantity;
		
}
