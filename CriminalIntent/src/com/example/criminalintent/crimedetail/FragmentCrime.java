package com.example.criminalintent.crimedetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.criminalintent.R;
import com.example.criminalintent.crimecamra.ActivityCrimeCamera;
import com.example.criminalintent.crimecamra.FragmentCrimeCamera;
import com.example.criminalintent.entity.Crime;
import com.example.criminalintent.entity.CrimeLab;
import com.example.criminalintent.util.CrimeIO;
import com.example.criminalintent.util.PictureUtils;
import com.example.criminalintent.util.TimeUtil;

/**
 * ��������Ϊ��ϸ ��Ƭ��fragment�Ƿ�����activity֮�ϵ�һ������Ƭ��
 * 
 * @author liuyh 2016��9��20��
 */
public class FragmentCrime extends Fragment {

	public static String EXTRA_CRIME_ID = "extra_crime_id";
	private static final String DATE_DIALOG = "date_dialog";
	public static final int REQUEST_CODE = 0;
	private static final int REQUEST_PHOTO = 1;
	/** ������Ϊ��ʵ�� */
	private Crime crime;
	/** ����� */
	private EditText titleField;
	/** ���ڰ�ť */
	private Button dateButton;
	/** ��ѡ�� */
	private CheckBox solvedCheckBox;
	/** ��Ƭ����ͼ */
	private ImageView photoImageView;
	/** �����ť */
	private ImageButton cameraButton;

	/**
	 * �ô˷�������crimefragmentʵ����ʵ���ڴ���ʵ��ʱ����bundle��fragment
	 * 
	 * @param crimeId
	 * @return
	 */
	public static FragmentCrime newInstance(UUID crimeId) {
		// ����bundle
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_CRIME_ID, crimeId);
		// ����fragment�����arg������fragment
		FragmentCrime crimeFragment = new FragmentCrime();
		crimeFragment.setArguments(bundle);
		return crimeFragment;
	}

	/**
	 * ����
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����һ�ַ�ʽ����intent��ȡcrimeid
		// UUID crimeId = (UUID)
		// getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);

		// ���ڶ��ַ�ʽ����ȡfragment����
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		// ����id��ȡcrime
		crime = CrimeLab.getIntance(getActivity()).getCrime(crimeId);
		// ����fragment�в˵�
		setHasOptionsMenu(true);
		// �����в��������ذ�ť
		if (NavUtils.getParentActivityName(getActivity()) != null) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	/**
	 * ������ͼ
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// return super.onCreateView(inflater, container, savedInstanceState);
		// ��fragment_crime.xml���ֻ��Ʊ�fragment����ͼ
		View view = inflater.inflate(R.layout.fragment_crime, container, false);

		// ----- ��ȡ�ؼ� -----//
		// ��ȡ�����
		titleField = (EditText) view.findViewById(R.id.crime_title);
		// ��ȡ���ڰ�ť
		dateButton = (Button) view.findViewById(R.id.crime_date);
		// dateButton.setEnabled(false);
		// ��ȡ��ѡ��
		solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
		// ��ȡ��Ƭview�ؼ�
		photoImageView = (ImageView) view.findViewById(R.id.crime_imageView);
		// ��ȡ�����ť
		cameraButton = (ImageButton) view.findViewById(R.id.imageButton);

		// -----���ÿؼ����ԣ���ʱ��ʾ-----//
		titleField.setText(crime.getTitle());
		dateButton.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(crime.getDate()));
		solvedCheckBox.setChecked(crime.isSolved());

		// ----- ���¼����߼� -----//
		// ������������ı��ı��¼������߼�
		titleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				crime.setTitle(s.toString());
				crime.setDate(new Date());
				dateButton.setText(new SimpleDateFormat("yyyyMMdd-hh:mm:ss").format(crime.getDate()));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		// ���ø�ѡ��ı�ʱ�¼������߼�
		solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				crime.setSolved(isChecked);
			}
		});
		// �������ڰ�ť����¼��߼�
		dateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡactivity�� fragmentManager
				FragmentManager fm = getActivity().getSupportFragmentManager();
				// ����dataPickerFragment���Ի���fragment��
				FragmentDatePicker dateDialog = FragmentDatePicker.newInstance(crime.getDate());
				// ��dataPickerFragment����Ŀ��fragment
				dateDialog.setTargetFragment(FragmentCrime.this, REQUEST_CODE);
				// ��ʾdataPickerFragment
				dateDialog.show(fm, DATE_DIALOG);
			}
		});
		// ���������ť�¼�
		cameraButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ActivityCrimeCamera.class);
				startActivity(i);
				startActivityForResult(i, REQUEST_PHOTO);
			}
		});
		return view;
	}

	/**
	 * �����ź��ͼƬ�ŵ�imageView��
	 */
	public void showPhoto() {
		BitmapDrawable b = null;
		if (crime.getPhotoFileName() != null) {
			String path = CrimeIO.getAppSDPath() + crime.getPhotoFileName();
			b = PictureUtils.getScaledDrawable(getActivity(), path);
		}
		photoImageView.setImageDrawable(b);
	}

	/**
	 * fragment����-start
	 */
	@Override
	public void onStart() {
		super.onStart();
		if (crime.getPhotoFileName() != null)
			showPhoto();
	}

	/**
	 * fragment����-stop
	 */
	@Override
	public void onStop() {
		super.onStop();
		PictureUtils.cleanImageView(photoImageView);
	}

	/**
	 * ������Activity���ش�Activityʱ������
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_CODE) {
			Date date = (Date) data.getSerializableExtra(FragmentDatePicker.EXTRA_DATE);
			crime.setDate(date);
			dateButton.setText(TimeUtil.date2String(date, "yyyy-MM-dd HH:mm:ss"));
		}
		else if (requestCode == REQUEST_PHOTO) {
			String photoFileName = data.getStringExtra(FragmentCrimeCamera.EXTRA_PHOTO_FILENAME);
			crime.setPhotoFileName(photoFileName);
			showPhoto();
		}

	}

	/**
	 * �������˵������ʱ����
	 * 
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:// ���id������ǲ������ϵķ��ذ�ť��������home����
				// ���ò������ϵķ��ؼ��߼�
				if (NavUtils.getParentActivityName(getActivity()) != null) {
					NavUtils.navigateUpFromSameTask(getActivity());
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * fragment��������->��ͣ�׶�
	 */
	@Override
	public void onPause() {
		super.onPause();
		// ���Ϊ��crimeFragment��ͣʱ����crime����־û���json�ļ�
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}
}
