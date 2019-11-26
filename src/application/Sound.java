package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound {
	
	private static MediaPlayer bgmPlayer;
	private static Music currentMusic;
	
	public static void changeBackgroundMusic(Music music) {
		if (currentMusic != music) {
			if (bgmPlayer != null) {
				bgmPlayer.stop();
			}
			bgmPlayer = new MediaPlayer(new Media(music.getPath()));
			bgmPlayer.setVolume(music.getVolume());
			bgmPlayer.play();
			bgmPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					bgmPlayer.seek(Duration.ZERO);
				}
			});
			currentMusic = music;
		}
	}

}
