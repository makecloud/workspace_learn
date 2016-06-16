package com.role;

import com.interf.Waiter;

public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("greet to,"+name);
	}

	@Override
	public void serverTo(String name) {
		System.out.println("serveToyou,"+name);
	}

}
