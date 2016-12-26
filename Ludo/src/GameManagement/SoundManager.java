package GameManagement;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {
	static boolean mute = false;
	private Media media;
	private MediaPlayer mplayer;

	public SoundManager(){
		String path = new File("src/MediaFolder/Happy-electronic-music.mp3").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mplayer = new MediaPlayer(media);
		mplayer.setOnEndOfMedia(new Runnable(){
			public void run(){
				mplayer.seek(Duration.ZERO);
			}
		});
		if(!mute){
			mplayer.play();
		}
	}

	public SoundManager(int args){
		String path = new File("src/MediaFolder/Marimba-music.mp3").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mplayer = new MediaPlayer(media);
		mplayer.setOnEndOfMedia(new Runnable(){
			public void run(){
				mplayer.seek(Duration.ZERO);
			}
		});
		if(!mute){
			mplayer.play();
		}
	}

	public static void killSound (){
		if(!mute){
			String path = new File("src/MediaFolder/RICOCHET.WAV").getAbsolutePath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
		}
	}

	public static void moveToken(){
		if(!mute){
			String path = new File("src/MediaFolder/POP.WAV").getAbsolutePath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
		}
	}

	public static void endGame(){
		if(!mute){
			String path = new File("src/MediaFolder/VICTORY.WAV").getAbsolutePath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
		}
	}

	public static void dieRoll(){
		if(!mute){
			String path = new File("src/MediaFolder/Shake And Roll Dice-SoundBible.com-591494296.mp3").getAbsolutePath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
		}
	}

	public static void tokenOut(){
		if(!mute){
			String path = new File("src/MediaFolder/ANGELS_F.WAV").getAbsolutePath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
		}
	}

	public MediaPlayer getMediaPlayer(){
		return mplayer;
	}

	public Media getMedia(){
		return media;
	}	
	
	public void changeMute(){
		if(!mute){
			mplayer.stop();
			SoundManager.mute = true;
		}	
		else{
			mplayer.setAutoPlay(true);
			SoundManager.mute = false;
		}
	}
}
