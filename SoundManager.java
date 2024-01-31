package org.theButton;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private boolean isPassive;
    private final Clip ACTIVE;
    private final Clip PASSIVE;
    private final Clip COLLECT;
    private final Clip DEATH;
    private Sound music;
    public SoundManager() {
        this.ACTIVE = getClip("C:\\Users\\danie\\Desktop\\School\\Grade 12\\CompSci\\code\\THE_BUTTON\\Dance Energetic.wav");
        this.PASSIVE = getClip("C:\\Users\\danie\\Desktop\\School\\Grade 12\\CompSci\\code\\THE_BUTTON\\Dance Chill Out.wav");
        this.COLLECT = getClip("C:\\Users\\danie\\Desktop\\School\\Grade 12\\CompSci\\code\\THE_BUTTON\\Crystal Collect.wav");
        this.DEATH = getClip("C:\\Users\\danie\\Desktop\\School\\Grade 12\\CompSci\\code\\THE_BUTTON\\Lose.wav");
    }
    public void setMusic(Sound music) {
        // Stop whichever sound is running (it will not cause an error if it is not running)
        this.PASSIVE.stop();
        this.ACTIVE.stop();
        switch (music) {
            case PASSIVE:
                this.PASSIVE.setMicrosecondPosition(0);
                this.PASSIVE.loop(2147483647); // This is the largest possible int, and can be considered infinite for our purposes
                break;
            case ACTIVE:
                this.ACTIVE.setMicrosecondPosition(0);
                this.ACTIVE.loop(2147483647); // Same as passive
        }
    }

    private Clip getClip(String filePath) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip.open(inputStream);
        } catch (Exception e) {
            System.out.println("[DEBUG: SoundManager.getClip] An error occured -> " + e.getMessage() + " | " + filePath);
//            clip = getClip(filePath);
            e.printStackTrace();
        }
        return clip;
    }
    public void playSound(Sound sound) {
        switch(sound) {
            case COLLECT:
                this.COLLECT.setMicrosecondPosition(0);
                this.COLLECT.start();
                break;
            case DEATH:
//                this.ACTIVE.stop(); // Passive will never be running on death, so it does not need to be stopped
//                this.DEATH.loop(1);
                this.ACTIVE.stop(); // Passive will never be running on death, so it does not need to be stopped
                this.DEATH.loop(1);
        }
    }
    public enum Sound {
        PASSIVE, ACTIVE, COLLECT, DEATH;
    }
}