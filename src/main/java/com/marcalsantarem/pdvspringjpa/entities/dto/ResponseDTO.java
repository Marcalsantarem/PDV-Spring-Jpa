package com.marcalsantarem.pdvspringjpa.entities.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ResponseDTO {
	
	private List<String> messages;
		
	public ResponseDTO(List<String> messages) {
        this.messages = messages;
    }

    public ResponseDTO(String message) {
        this.messages = Arrays.asList(message);
    }

}
