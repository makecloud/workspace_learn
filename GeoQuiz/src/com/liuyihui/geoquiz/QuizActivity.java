package com.liuyihui.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liuyihui.geoquiz.service.TrueFalse;

public class QuizActivity extends Activity {

	/** 问题truefalse数组 */
	private TrueFalse[] questionBank = new TrueFalse[] { new TrueFalse(R.string.question_1, true),
			new TrueFalse(R.string.question_2, false), new TrueFalse(R.string.question_3, true),
			new TrueFalse(R.string.question_4, false) };
	/** 当前数组下标 */
	private int currentIndex = 0;
	/** TAG */
	private static final String TAG = "QuizActivity";

	/**
	 * activity创建逻辑
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// FrameLayout frameLayout = new FrameLayout(this);
		// setContentView(frameLayout);

		// ImageView imageView = new ImageView(this);
		// frameLayout.addView(imageView);
		setContentView(R.layout.activity_quiz);

		/** 获取组件 */
		// 获取文本组件
		final TextView tv = (TextView) findViewById(R.id.question_text);
		// 获取是按钮组件
		Button mTrueButton = (Button) findViewById(R.id.true_button);
		// 获取否按钮组件
		Button mFalseButton = (Button) findViewById(R.id.false_button);
		// 获取下一个按钮组件
		Button mNextButton = (Button) findViewById(R.id.next_button);
		// 获取作弊按钮
		Button mCheatButton = (Button) findViewById(R.id.cheat_button);
		// 获取保存的 状态
		if (savedInstanceState != null) {
			currentIndex = savedInstanceState.getInt("currentIndex");
		}

		// --------------------------------------//

		// 设置文本控件要现实的文本
		tv.setText(questionBank[currentIndex].getQuestionStringId());

		// 为是按钮绑定单击事件处理逻辑
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "sb";
				if (questionBank[currentIndex].isTrueQuestion()) {
					result = "回答正确";
				}
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		});

		// 为否按钮绑定单击事件处理逻辑
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "sb";
				if (!questionBank[currentIndex].isTrueQuestion()) {
					result = "回答正确";
				}
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		});
		// 为作弊按钮绑定点击事件处理逻辑
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), CheatActivity.class);
				startActivity(i);
			}
		});

		// 为下一个 按钮绑定点击事件处理逻辑
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (++currentIndex == questionBank.length) {
					currentIndex = 0;
				}
				tv.setText(questionBank[currentIndex].getQuestionStringId());
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onSaveInstanceState");
		outState.putInt("currentIndex", currentIndex);
	}
}
