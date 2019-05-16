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
    public static final int ATTACK = 10;
    public static final int PAPER = 11;
    public static final int RISER = 12;
    public static final int JUMP = 13;
    public static final int FOUND = 14;
    public static final int COUNT = 15;
    public static final int VIC = 16;
    public static final int RICARDO = 17;

    AudioClip[] playList;

    private static AudioManager audio;

    public static AudioManager getInstance() {
        if (audio == null) {
            audio = new AudioManager();
        }
        return audio;
    }

    public AudioManager() {
        playList = new AudioClip[18];
        playList[LEVEL_ONE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level1.wav");
        playList[LEVEL_TWO_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level2.wav");
        playList[LEVEL_THREE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/Level3.wav");
        playList[LEVEL_ONE_CLICK] = SoundResource.getInstance().getClip("/Art/Sound Effect/key.wav");
        playList[LEVEL_ONE_LOCK_CLICK] = SoundResource.getInstance().getClip("/Art/Sound Effect/Switch1.wav");
        playList[LEVEL_ONE_LOCK_DOOR] = SoundResource.getInstance().getClip("/Art/Sound Effect/Open5.wav");
        playList[NOISE] = SoundResource.getInstance().getClip("/Art/Sound Effect/Noise.wav");
        playList[LEVEL_TWO_COLLECT] = SoundResource.getInstance().getClip("/Art/Sound Effect/collect.wav");
        playList[END_SCENE_BACKGROUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/endGame.wav");
        playList[ATTACK] = SoundResource.getInstance().getClip("/Art/Sound Effect/attack.wav");
        playList[PAPER] = SoundResource.getInstance().getClip("/Art/Sound Effect/paper.wav");
        playList[RISER] = SoundResource.getInstance().getClip("/Art/Sound Effect/riser.wav");
        playList[JUMP] = SoundResource.getInstance().getClip("/Art/Sound Effect/jump.wav");
        playList[FOUND] = SoundResource.getInstance().getClip("/Art/Sound Effect/found.wav");
        playList[COUNT] = SoundResource.getInstance().getClip("/Art/Sound Effect/count.wav");
        playList[VIC] = SoundResource.getInstance().getClip("/Art/Sound Effect/vic.wav");
        playList[RICARDO] = SoundResource.getInstance().getClip("/Art/Sound Effect/ricado.wav");

    }

    public AudioClip[] getPlayList() {
        return playList;
    }
}
