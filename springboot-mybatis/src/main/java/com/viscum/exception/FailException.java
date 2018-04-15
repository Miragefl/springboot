package com.viscum.exception;

public class FailException extends Exception {

	private String errCode;
	private String errMsg;
	private Exception ex;

	public FailException(String errCode, String errMsg, Exception ex) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.ex = ex;
	}

	public FailException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}
}
