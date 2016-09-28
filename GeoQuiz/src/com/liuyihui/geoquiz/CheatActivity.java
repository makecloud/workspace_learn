package com.liuyihui.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	/** 答案显示文本控件 */
	private TextView answerTextView;
	/** 显示答案按钮 */
	private Button showAnswerButton;
	/** 答案 */
	private boolean answer;
	/** extra key */
	public static final String EXTRA_ANSWER_SHOWN = "com.liuyihui.geoquiz.answer_shown";

	/**
	 * 创建activity调用的方法
	 * 
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		answer = getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false);
		showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
		showAnswerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				answerTextView = (TextView) findViewById(R.id.answerTextView);
				if (answer) {
					answerTextView.setText(R.string.true_button);
				}
				else {
					answerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}

		});
	}

	/**
	 * 设置anwser result
	 * 
	 * @param isAnswerShown
	 */
	public void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(0, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cheat, menu);
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
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
}
