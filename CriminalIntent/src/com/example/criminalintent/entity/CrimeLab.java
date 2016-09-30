package com.example.criminalintent.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import android.content.Context;
import android.widget.Toast;
import com.example.criminalintent.util.CriminalIntentJSONSerializer;

/**
 * ��Ϊʵ�� ������
 * 
 * @author liuyh 2016��9��28��
 */
public class CrimeLab {

	/** crime�б� */
	private ArrayList<Crime> crimes;
	/** crimeLab */
	private static CrimeLab crimeLab;
	/** context */
	private Context context;
	/** �־û������� */
	private CriminalIntentJSONSerializer serializar;
	/** crime������ļ��� */
	private static final String crimeFileName = "CrimeLab.json";

	/**
	 * ���췽���ĳ�˽�У���ֹ�� new CrimeLab() ��ʽ�ĵ�������ʵ����
	 * 
	 * @param appContext
	 */
	private CrimeLab(Context appContext) {
		context = appContext;
		serializar = new CriminalIntentJSONSerializer(context, crimeFileName);
		// ����crimes json�ļ���crimes����
		try {
			crimes = serializar.loadCrimesFromJsonFile();
		}
		catch (Exception e) {
			crimes = new ArrayList<Crime>();
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * ���crimeLabʵ��
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
	 * ���crime
	 * 
	 * @param crime
	 */
	public void addCrime(Crime c) {
		crimes.add(c);
	}

	/**
	 * �־û�crimes���鵽.json�ļ�
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
	 * ɾ��Crime
	 * 
	 * @param crime����
	 */
	public void deleteCrime(Crime c) {
		crimes.remove(c);
	}

	// ---getter/setter--//

	/**
	 * ��ȡcrime�б�
	 * 
	 * @return
	 */
	public List<Crime> getCrimes() {
		return crimes;
	}

	/**
	 * ��ȡָ��id��crime
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
