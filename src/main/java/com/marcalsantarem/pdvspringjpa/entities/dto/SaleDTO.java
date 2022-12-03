package com.marcalsantarem.pdvspringjpa.entities.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SaleDTO {
	
	private Long userid;
	private List<ProductDTO> items;
	
}
