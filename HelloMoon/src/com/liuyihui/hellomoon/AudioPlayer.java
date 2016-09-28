package com.liuyihui.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
	private MediaPlayer mediaPlayer;

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	/**
	 * 播放。兼顾从暂停中播放，和从未开始状态播放
	 * 
	 * @param c
	 */
	public void play(Context c) {
		// 未开始状态播放：创建mediaPlayer实例，设置播放完成事件，开始播放
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(c, R.raw.hao);
			// 设置循环播放
			mediaPlayer.setLooping(true);
			// mediaPlayer.setOnCompletionListener(new
			// MediaPlayer.OnCompletionListener() {
			// @Override
			// public void onCompletion(MediaPlayer mp) {
			// stop();
			// }
			// });
			mediaPlayer.start();
		}
		// 从暂停状态播放：判断
		else {
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}
	}

	/**
	 * 是否播放中。如果没开始播放，也返回否
	 * 
	 * @return
	 */
	public boolean isPlaying() {
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying())
				return true;
			else
				return false;

		}
		return false;
	}

	/**
	 * 暂停播放
	 */
	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

}
