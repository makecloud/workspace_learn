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
 * �Ի���fragment����crimeFragment�й�
 * 
 * @author liuyh 2016��9��23��
 */
public class DatePickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = "com.example.criminalintent.date";

	/** ��Ϊ�������� */
	public Date crimeDate;

	/**
	 * ��ȡDatePickerFragmentʵ���ķ�������ȡ�Ѱ�args��datePickerFragment
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
	 * ��������activity���ô˷���
	 * 
	 * @param savedInstanceState
	 * @return
	 * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// ʹ��date_picker.xml�����ļ���������ѡ��ؼ�����ͼ�����������ڶԻ�����м䣩
		View view = getActivity().getLayoutInflater().inflate(R.layout.date_picker, null);

		// ��fragment��args��ȡ���ڶ���
		crimeDate = (Date) getArguments().getSerializable(EXTRA_DATE);

		// ʹ��calendar��ȡdate�����е�����������
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(crimeDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// ��ȡdatepicker�ؼ����Ի����м�Ĳ��֣�����ʼ���������ڸı��¼�
		DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker_dialog);
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// �û��ı����ڿؼ�ʱ���ı�crimeDate����
				crimeDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
				getArguments().putSerializable(EXTRA_DATE, crimeDate);
			}
		});
		// �����Ի�������viewΪ����ѡ��ؼ������ñ��⣬����ȷ����ť
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
	 * �ڵ��datePickerFragment��ȷ����ťʱ��ִ�У����ͽ��
	 * 
	 * @param resultCode
	 */
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		// ����intent
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATE, crimeDate);
		// ��ȡtargetFragment�������ڸ�fragment����Ȼ�������onActivityResult����
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

	}
}
