package com.miniHr.comm;

/**
 * 展位状态
 * 1、未购买，2、购买中  3、已购买
 * @author Jacco
 */
public enum BoothState {
	Unsaled("1"),Saling("2"),Saled("3");
	String state;
	
	BoothState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	
	public static String getBoothState(BoothState state){
		return state.getState();
	}
	
}
