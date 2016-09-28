package com.example.criminalintent.crimedetail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.criminalintent.R;
import com.example.criminalintent.R.id;
import com.example.criminalintent.R.layout;
import com.example.criminalintent.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

/**
 * 对话框fragment，被crimeFragment托管
 * 
 * @author liuyh 2016年9月23日
 */
public class DatePickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = "com.example.criminalintent.date";

	/** 行为发生日期 */
	public Date crimeDate;

	/**
	 * 获取DatePickerFragment实例的方法，获取已绑定args的datePickerFragment
	 * 
	 * @param date
	 * @return
	 */
	public static DatePickerFragment newInstance(Date date) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_DATE, date);

		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.setArguments(bundle);
		return datePickerFragment;
	}

	/**
	 * 创建。由activity调用此方法
	 * 
	 * @param savedInstanceState
	 * @return
	 * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// 使用date_picker.xml布局文件，画日期选择控件的视图。（将来放在对话框的中间）
		View view = getActivity().getLayoutInflater().inflate(R.layout.date_picker, null);

		// 从fragment的args获取日期对象
		crimeDate = (Date) getArguments().getSerializable(EXTRA_DATE);

		// 使用calendar获取date对象中的年月日整数
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(crimeDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// 获取datepicker控件（对话框中间的部分），初始化，绑定日期改变事件
		DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker_dialog);
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// 用户改变日期控件时，改变crimeDate对象
				crimeDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
				getArguments().putSerializable(EXTRA_DATE, crimeDate);
			}
		});
		// 创建对话框，设置view为日期选择控件，设置标题，设置确定按钮
		return new AlertDialog.Builder(getActivity()).setView(view)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				}).create();
	}

	/**
	 * 在点击datePickerFragment的确定按钮时，执行，发送结果
	 * 
	 * @param resultCode
	 */
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		// 创建intent
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATE, crimeDate);
		// 获取targetFragment（类似于父fragment），然后调用其onActivityResult方法
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

	}
}
