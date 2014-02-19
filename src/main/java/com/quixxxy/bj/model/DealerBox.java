package com.quixxxy.bj.model;


public class DealerBox extends Box {

	public boolean isEnough() {
		return getScore() >= 17;
	}
}
