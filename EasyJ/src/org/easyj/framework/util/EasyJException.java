package org.easyj.framework.util;

public class EasyJException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EasyJException(String message){
		super(message);
	}
	public EasyJException(){
		super();
	}
}
