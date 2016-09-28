package com.liuyihui.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author liuyh 2016年9月23日
 */
public class HelloMoonFragment extends Fragment {
	/** 播放按钮引用 */
	private Button playButton;
	/** 暂停按钮 */
	private Button pauseButton;
	/** 停止按钮引用 */
	private Button stopButton;

	/** 播放器类 */
	private AudioPlayer audioPlayer = new AudioPlayer();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 使用布局xml文件生成view实例
		View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);

		// 获取播放按钮实例
		playButton = (Button) view.findViewById(R.id.playButton);
		// 获取停止按钮实例
		stopButton = (Button) view.findViewById(R.id.stopButton);

		// 播放按钮事件：
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 如果播放中：暂停，并改为播放
				if (audioPlayer.isPlaying()) {
					audioPlayer.pause();
					playButton.setText(R.string.go_on_play_text);
				}
				// 未播放:播放，并改为暂停
				else {
					audioPlayer.play(getActivity());
					playButton.setText(R.string.pause_text);
				}
			}
		});
		// 停止按钮事件
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				audioPlayer.stop();
				playButton.setText(R.string.play_button_text);
			}
		});

		// 返回view实例，给Activity了
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		audioPlayer.stop();

	}
}
