package com.liuyihui.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author liuyh 2016��9��23��
 */
public class HelloMoonFragment extends Fragment {
	/** ���Ű�ť���� */
	private Button playButton;
	/** ��ͣ��ť */
	private Button pauseButton;
	/** ֹͣ��ť���� */
	private Button stopButton;

	/** �������� */
	private AudioPlayer audioPlayer = new AudioPlayer();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// ʹ�ò���xml�ļ�����viewʵ��
		View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);

		// ��ȡ���Ű�ťʵ��
		playButton = (Button) view.findViewById(R.id.playButton);
		// ��ȡֹͣ��ťʵ��
		stopButton = (Button) view.findViewById(R.id.stopButton);

		// ���Ű�ť�¼���
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��������У���ͣ������Ϊ����
				if (audioPlayer.isPlaying()) {
					audioPlayer.pause();
					playButton.setText(R.string.go_on_play_text);
				}
				// δ����:���ţ�����Ϊ��ͣ
				else {
					audioPlayer.play(getActivity());
					playButton.setText(R.string.pause_text);
				}
			}
		});
		// ֹͣ��ť�¼�
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				audioPlayer.stop();
				playButton.setText(R.string.play_button_text);
			}
		});

		// ����viewʵ������Activity��
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		audioPlayer.stop();

	}
}
