package com.ar.mrm.exception;

public class QuantityFieldEmptyException extends RuntimeException{

	private static final long serialVersionUID = -6794840331722105354L;

	public QuantityFieldEmptyException(){
		super("empty quantity field exception");
	}
}
