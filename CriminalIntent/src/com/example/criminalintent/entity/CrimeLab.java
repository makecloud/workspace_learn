package com.example.criminalintent.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.content.Context;
import android.widget.Toast;
import com.example.criminalintent.util.CriminalIntentJSONSerializer;

/**
 * 行为实例 管理类
 * 
 * @author liuyh 2016年9月28日
 */
public class CrimeLab {

	/** crime列表 */
	private ArrayList<Crime> crimes;
	/** crimeLab */
	private static CrimeLab crimeLab;
	/** context */
	private Context context;
	/** 持久化工具类 */
	private CriminalIntentJSONSerializer serializar;
	/** crime保存的文件名 */
	private static final String crimeFileName = "CrimeLab.json";

	/**
	 * 构造方法改成私有，禁止了 new CrimeLab() 形式的调用生成实例。
	 * 
	 * @param appContext
	 */
	private CrimeLab(Context appContext) {
		context = appContext;
		serializar = new CriminalIntentJSONSerializer(context, crimeFileName);
		// 加载crimes json文件到crimes数组
		try {
			crimes = serializar.loadCrimesFromJsonFile();
		}
		catch (Exception e) {
			crimes = new ArrayList<Crime>();
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 获得crimeLab实例
	 * 
	 * @param c
	 * @return
	 */
	public static CrimeLab getIntance(Context c) {
		if (crimeLab == null) {
			crimeLab = new CrimeLab(c.getApplicationContext());
		}
		return crimeLab;
	}

	/**
	 * 添加crime
	 * 
	 * @param crime
	 */
	public void addCrime(Crime c) {
		crimes.add(c);
	}

	/**
	 * 持久化crimes数组到.json文件
	 * 
	 * @return
	 */
	public boolean serializeCrimes() {
		try {
			serializar.saveCrimes2(crimes);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除Crime
	 * 
	 * @param crime对象
	 */
	public void deleteCrime(Crime c) {
		crimes.remove(c);
	}

	// ---getter/setter--//

	/**
	 * 获取crime列表
	 * 
	 * @return
	 */
	public List<Crime> getCrimes() {
		return crimes;
	}

	/**
	 * 获取指定id的crime
	 * 
	 * @param id
	 * @return
	 */
	public Crime getCrime(UUID id) {
		for (Crime crime : crimes) {
			if (crime.getId().equals(id))
				return crime;
		}
		return null;
	}
}
