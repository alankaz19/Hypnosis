package scene;

import resourcemanage.SoundResource;

import java.applet.AudioClip;

public class AudioManager {
    public static final int LEVEL_ONE_BACKGROUND = 0;
    public static final int LEVEL_TWO_BACKGROUND = 1;
    public static final int LEVEL_THREE_BACKGROUND = 2;
    public static final int LEVEL_ONE_CLICK = 3;
    public static final int LEVEL_ONE_LOCK_CLICK = 4;
    public static final int LEVEL_ONE_LOCK_DOOR = 5;
    public static final int NOISE = 6;
    public static final int LEVEL_TWO_COLLECT = 7;
    public static final int LEVEL_TWO_JUMP = 8;
    public static final int END_SCENE_BACKGROUND = 9;
    AudioClip[] playList;

    private static AudioManager audio;

    public static AudioManager getInstance(){
        if(audio == null){
            audio = new AudioManager();
        }
        return audio;
    }

    public AudioManager(){
        playList = new AudioClip[15];
        playList[LEVEL_ONE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level1.wav");
        playList[LEVEL_TWO_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level2.wav");
        playList[LEVEL_THREE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level2.wav");
        playList[LEVEL_ONE_CLICK] = SoundResource.getInstance().getClip("/Art/Sound Effect/key.wav");
        playList[LEVEL_ONE_LOCK_CLICK] = SoundResource.getInstance().getClip("/Art/Sound Effect/Switch1.wav");
        playList[LEVEL_ONE_LOCK_DOOR] = SoundResource.getInstance().getClip("/Art/Sound Effect/Open5.wav");
        playList[NOISE] = SoundResource.getInstance().getClip("/Art/Sound Effect/Noise.wav");
        playList[LEVEL_TWO_COLLECT] = SoundResource.getInstance().getClip("/Art/Sound Effect/collect.wav");
        playList[END_SCENE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/endGame.wav");

    }

    public AudioClip[] getPlayList() {
        return playList;
    }
}
