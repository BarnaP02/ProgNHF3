package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    private Clip clip;
    private float previousVolume = 0;
    private float currentVolume = 0;
    private boolean stopped = false;
    private int currentFramePosition = 0;
    private boolean mute;
    private FloatControl fc;
    public URL soundURL[] = new URL[50];

    /***
     * this is where I store the sounds
     */
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

    /***
     * sets the file for this sound to the file with the index i
     * @param i th index
     */
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open((ais));
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e) {
        }

    }

    /***
     * plays this sound from the beginning
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    /***
     * loops this sound
     */
    public void loop(){
        clip.loop((Clip.LOOP_CONTINUOUSLY));
    }

    /***
     * stops this sound
     */
    public void stop(){
        clip.stop();
    }

    /***
     * turns up the volume of this sound
     */
    public void volumeUp(){
        currentVolume += 1.0f;
        if (currentVolume > 6.0f){
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }

    /***
     * turns the volume down for this sound
     */
    public void volumeDown(){
        currentVolume -= 1.0f;
        if (currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }

    /***
     * mutes this sound
     */
    public void volumeMute(){
        if (!mute){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
        }
        else {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false;
        }
    }

    /***
     * stops this sound but saves its frame position
     */
    public void freeze(){
        currentFramePosition = clip.getFramePosition();
        clip.stop();
        stopped = true;
    }

    /***
     * plays this sound but instead of the beginning it starts from the saved frame position
     */
    public void resume(){
        if (stopped){
            clip.setFramePosition(currentFramePosition);
            clip.start();
            stopped = false;
        }
    }
}
