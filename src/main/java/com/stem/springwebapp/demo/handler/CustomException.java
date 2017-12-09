package com.stem.springwebapp.demo.handler;

public class CustomException extends RuntimeException {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;
	   
	   public CustomException(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }
	   public String getExceptionMsg(){
	      return this.exceptionMsg;
	   }
	   public void setExceptionMsg(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }
}
