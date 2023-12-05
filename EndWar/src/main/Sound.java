package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    float previousVolume = 0;
    float currentVolume = 0;
    boolean stopped = false;
    int currentFramePosition = 0;
    boolean mute;
    FloatControl fc;
    URL soundURL[] = new URL[50];
    public Sound(){
        clip = null;
        soundURL[0] = getClass().getResource("/sound/attack_confirmed_sound.wav");
        soundURL[1] = getClass().getResource("/sound/background_music.wav");
        soundURL[2] = getClass().getResource("/sound/beefy_engine_sound.wav");
        soundURL[3] = getClass().getResource("/sound/cannon_sound.wav");
        soundURL[4] = getClass().getResource("/sound/cursor_sound.wav");
        soundURL[5] = getClass().getResource("/sound/engine_sound.wav");
        soundURL[6] = getClass().getResource("/sound/fire_at_target_sound.wav");
        soundURL[7] = getClass().getResource("/sound/footstep_sound.wav");
        soundURL[8] = getClass().getResource("/sound/helicopter_sound.wav");
        soundURL[9] = getClass().getResource("/sound/next_phase_sound.wav");
        soundURL[10] = getClass().getResource("/sound/selected_sound.wav");
        soundURL[11] = getClass().getResource("/sound/selected_to_attack_sound.wav");
        soundURL[12] = getClass().getResource("/sound/selected_to_move_sound.wav");
        soundURL[13] = getClass().getResource("/sound/selection2_sound.wav");
        soundURL[14] = getClass().getResource("/sound/selection_sound.wav");
        soundURL[15] = getClass().getResource("/sound/small_engine_sound.wav");
        soundURL[16] = getClass().getResource("/sound/sub_sound.wav");
        soundURL[17] = getClass().getResource("/sound/Undertale - Megalovania.wav");
        soundURL[18] = getClass().getResource("/sound/jet_sound.wav");
        soundURL[19] = getClass().getResource("/sound/control_transition1.wav");
        soundURL[20] = getClass().getResource("/sound/control_transition2.wav");
        soundURL[21] = getClass().getResource("/sound/pause_menu_music.wav");
        soundURL[22] = getClass().getResource("/sound/victory_music.wav");
        soundURL[23] = getClass().getResource("/sound/defeat_music.wav");
        soundURL[24] = getClass().getResource("/sound/missile_sound.wav");
        soundURL[25] = getClass().getResource("/sound/high_caliber_cannon_sound.wav");
        soundURL[26] = getClass().getResource("/sound/machine_gun_sound.wav");
        soundURL[27] = getClass().getResource("/sound/explosion_sound.wav");
        soundURL[28] = getClass().getResource("/sound/medium_caliber_cannon_sound.wav");
        soundURL[29] = getClass().getResource("/sound/jet_sound2.wav");
        soundURL[30] = getClass().getResource("/sound/auto_handgun_sound.wav");
        soundURL[31] = getClass().getResource("/sound/battleship_salvo_sound.wav");
        soundURL[32] = getClass().getResource("/sound/machine_gun_with_missile_sound.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open((ais));
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e) {
        }

    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop((Clip.LOOP_CONTINUOUSLY));
    }
    public void stop(){
        clip.stop();
    }
    public void volumeUp(){
        currentVolume += 1.0f;
        if (currentVolume > 6.0f){
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeDown(){
        currentVolume -= 1.0f;
        if (currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeMute(){
        if (!mute){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else if (mute){
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }
    public void freeze(){
        currentFramePosition = clip.getFramePosition();
        clip.stop();
        stopped = true;
    }
    public void resume(){
        if (stopped){
            clip.setFramePosition(currentFramePosition);
            clip.start();
            stopped = false;
        }
    }
}
