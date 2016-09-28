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
	 * ���š���˴���ͣ�в��ţ��ʹ�δ��ʼ״̬����
	 * 
	 * @param c
	 */
	public void play(Context c) {
		// δ��ʼ״̬���ţ�����mediaPlayerʵ�������ò�������¼�����ʼ����
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(c, R.raw.hao);
			// ����ѭ������
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
		// ����ͣ״̬���ţ��ж�
		else {
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}
	}

	/**
	 * �Ƿ񲥷��С����û��ʼ���ţ�Ҳ���ط�
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
	 * ��ͣ����
	 */
	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

}
