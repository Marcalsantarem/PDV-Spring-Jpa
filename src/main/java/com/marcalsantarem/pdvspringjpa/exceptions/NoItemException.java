package com.marcalsantarem.pdvspringjpa.exceptions;

public class NoItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoItemException(String msg) {
		super(msg);
	}

}
