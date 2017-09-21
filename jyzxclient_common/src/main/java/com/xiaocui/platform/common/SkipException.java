package com.xiaocui.platform.common;

public class SkipException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String msg;

	public SkipException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public SkipException() {
	}

}
