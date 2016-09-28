package com.liuyihui.geoquiz;

import android.app.ActionBar;
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

	/** ����truefalse���� */
	private TrueFalse[] questionBank = new TrueFalse[] { new TrueFalse(R.string.question_1, true),
			new TrueFalse(R.string.question_2, false), new TrueFalse(R.string.question_3, true),
			new TrueFalse(R.string.question_4, false) };
	/** ��ǰ�����±� */
	private int currentIndex = 0;
	/** TAG */
	private static final String TAG = "QuizActivity";
	/** extra��key */
	public static final String EXTRA_ANSWER_IS_TRUE = "com.liuyihui.geoquiz.answer_is_true";
	private boolean isCheat = false;

	/**
	 * activity�����߼�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// FrameLayout frameLayout = new FrameLayout(this);
		// setContentView(frameLayout);

		// ImageView imageView = new ImageView(this);
		// frameLayout.addView(imageView);
		setContentView(R.layout.activity_quiz);

		// ������������app���������
		ActionBar aBar = getActionBar();
		// �ӱ��⣬��ʾ��Ӧ���������
		aBar.setSubtitle("�ӱ���");

		/** ��ȡ��� */
		// ��ȡ�ı����
		final TextView tv = (TextView) findViewById(R.id.question_text);
		// ��ȡ�ǰ�ť���
		Button mTrueButton = (Button) findViewById(R.id.true_button);
		// ��ȡ��ť���
		Button mFalseButton = (Button) findViewById(R.id.false_button);
		// ��ȡ��һ����ť���
		Button mNextButton = (Button) findViewById(R.id.next_button);
		// ��ȡ���װ�ť
		Button mCheatButton = (Button) findViewById(R.id.cheat_button);
		// ��ȡ����� ״̬
		if (savedInstanceState != null) {
			currentIndex = savedInstanceState.getInt("currentIndex");
		}

		// --------------------------------------//

		// �����ı��ؼ�Ҫ��ʵ���ı�
		tv.setText(questionBank[currentIndex].getQuestionStringId());

		// Ϊ�ǰ�ť�󶨵����¼������߼�
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "sb";
				if (questionBank[currentIndex].isTrueQuestion()) {
					result = "�ش���ȷ";
				}
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		});

		// Ϊ��ť�󶨵����¼������߼�
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "sb";
				if (!questionBank[currentIndex].isTrueQuestion()) {
					result = "�ش���ȷ";
				}
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		});
		// Ϊ���װ�ť�󶨵���¼������߼�
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// ��Ҫ���ݸ���activity��intent
				Intent i = new Intent(getApplicationContext(), CheatActivity.class);
				i.putExtra(EXTRA_ANSWER_IS_TRUE, questionBank[currentIndex].isTrueQuestion());

				// startActivity(i);
				startActivityForResult(i, 0);// ������activity
			}
		});

		// Ϊ��һ�� ��ť�󶨵���¼������߼�
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

	/**
	 * ����activity���ص����������
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			isCheat = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
		}
		if (isCheat) {
			Toast.makeText(getApplicationContext(), "�����ף�", Toast.LENGTH_SHORT).show();
		}
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
