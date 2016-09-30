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
 * 不道德行为明细 碎片（fragment是放置于activity之上的一个个碎片）
 * 
 * @author liuyh 2016年9月20日
 */
public class FragmentCrime extends Fragment {

	public static String EXTRA_CRIME_ID = "extra_crime_id";
	private static final String DATE_DIALOG = "date_dialog";
	public static final int REQUEST_CODE = 0;
	private static final int REQUEST_PHOTO = 1;
	/** 代表行为的实体 */
	private Crime crime;
	/** 输入框 */
	private EditText titleField;
	/** 日期按钮 */
	private Button dateButton;
	/** 复选框 */
	private CheckBox solvedCheckBox;
	/** 照片缩略图 */
	private ImageView photoImageView;
	/** 相机按钮 */
	private ImageButton cameraButton;

	/**
	 * 用此方法创建crimefragment实例，实现在创建实例时，绑定bundle到fragment
	 * 
	 * @param crimeId
	 * @return
	 */
	public static FragmentCrime newInstance(UUID crimeId) {
		// 创建bundle
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_CRIME_ID, crimeId);
		// 创建fragment，添加arg，返回fragment
		FragmentCrime crimeFragment = new FragmentCrime();
		crimeFragment.setArguments(bundle);
		return crimeFragment;
	}

	/**
	 * 创建
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// （第一种方式）从intent获取crimeid
		// UUID crimeId = (UUID)
		// getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);

		// （第二种方式）获取fragment参数
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		// 根据id获取crime
		crime = CrimeLab.getIntance(getActivity()).getCrime(crimeId);
		// 设置fragment有菜单
		setHasOptionsMenu(true);
		// 设置有操作栏返回按钮
		if (NavUtils.getParentActivityName(getActivity()) != null) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	/**
	 * 创建视图
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// return super.onCreateView(inflater, container, savedInstanceState);
		// 用fragment_crime.xml布局绘制本fragment的视图
		View view = inflater.inflate(R.layout.fragment_crime, container, false);

		// ----- 获取控件 -----//
		// 获取输入框
		titleField = (EditText) view.findViewById(R.id.crime_title);
		// 获取日期按钮
		dateButton = (Button) view.findViewById(R.id.crime_date);
		// dateButton.setEnabled(false);
		// 获取复选框
		solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
		// 获取照片view控件
		photoImageView = (ImageView) view.findViewById(R.id.crime_imageView);
		// 获取相机按钮
		cameraButton = (ImageButton) view.findViewById(R.id.imageButton);

		// -----设置控件属性，即时显示-----//
		titleField.setText(crime.getTitle());
		dateButton.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(crime.getDate()));
		solvedCheckBox.setChecked(crime.isSolved());

		// ----- 给事件绑定逻辑 -----//
		// 给输入框设置文本改变事件监听逻辑
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
		// 设置复选框改变时事件监听逻辑
		solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				crime.setSolved(isChecked);
			}
		});
		// 设置日期按钮点击事件逻辑
		dateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取activity的 fragmentManager
				FragmentManager fm = getActivity().getSupportFragmentManager();
				// 创建dataPickerFragment（对话框fragment）
				FragmentDatePicker dateDialog = FragmentDatePicker.newInstance(crime.getDate());
				// 给dataPickerFragment设置目标fragment
				dateDialog.setTargetFragment(FragmentCrime.this, REQUEST_CODE);
				// 显示dataPickerFragment
				dateDialog.show(fm, DATE_DIALOG);
			}
		});
		// 设置相机按钮事件
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
	 * 把缩放后的图片放到imageView上
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
	 * fragment周期-start
	 */
	@Override
	public void onStart() {
		super.onStart();
		if (crime.getPhotoFileName() != null)
			showPhoto();
	}

	/**
	 * fragment周期-stop
	 */
	@Override
	public void onStop() {
		super.onStop();
		PictureUtils.cleanImageView(photoImageView);
	}

	/**
	 * 从其他Activity返回此Activity时，调用
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
	 * 操作栏菜单被点击时调用
	 * 
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:// 这个id代表的是操作栏上的返回按钮，而不是home键。
				// 设置操作栏上的返回键逻辑
				if (NavUtils.getParentActivityName(getActivity()) != null) {
					NavUtils.navigateUpFromSameTask(getActivity());
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * fragment生命周期->暂停阶段
	 */
	@Override
	public void onPause() {
		super.onPause();
		// 设计为在crimeFragment暂停时，将crime数组持久化到json文件
		CrimeLab.getIntance(getActivity().getApplicationContext()).serializeCrimes();
	}
}
