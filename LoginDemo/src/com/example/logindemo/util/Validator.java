package com.example.logindemo.util;

import android.content.Context;
import android.widget.Toast;

public class Validator {
	public static void volidNull2Toast(Object target, Context c, String message) {
		if (target == null || target.equals("")) {
			Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
		}
	}
}
