package lib;

import javafx.scene.media.AudioClip;

public class AudioUtility {

	private static AudioClip sound_collect,sound_boom,sound_first,sound_second;
	
	static {
		loadResource();
	}

	public static void loadResource() {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		sound_collect = new AudioClip(loader.getResource("sound/collect.wav").toString());
		sound_boom = new AudioClip(loader.getResource("sound/boom.wav").toString());
		sound_first = new AudioClip(loader.getResource("sound/op.wav").toString());
		sound_second = new AudioClip(loader.getResource("sound/naturesounds.wav").toString());
	}

	public static void playSound(String identifier) {
		if(identifier == "collect"){
			sound_collect.play();
		}else if(identifier == "boom"){
			sound_boom.play();
		}else if(identifier == "first"){
			sound_first.play();
		}else if(identifier == "second"){
			sound_second.play();
		}
	}
}
