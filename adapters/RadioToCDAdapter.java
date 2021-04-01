package adapters;

import interfaces.CDPlayer;
import interfaces.Radio;

/**
 * An adapter class allowing for a Radio object to handle requests designated for a CDPlayer object.
 * Allows for the universal CD player remote to control a Radio.
 *
 * @author Robert Pagliarulo
 */
public class RadioToCDAdapter implements CDPlayer {
    private Radio radio;

    public RadioToCDAdapter(Radio radio) {
        this.radio = radio;
    }

    @Override
    public String on() {
       return radio.on();
    }

    @Override
    public String off() {
        return radio.off();
    }

    @Override
    public String mute() {
        return radio.mute();
    }

    @Override
    public String unMute() {
        return radio.unMute();
    }

    @Override
    public String volumeUp() {
        return radio.volumeUp();
    }

    @Override
    public String volumeDown() {
       return radio.volumeDown();
    }


// Radios can't "play" or "pause", so these functions of CDPlayer interface will equate to changing the frequency
    @Override
    public String play() {
       return radio.changeFrequency();
    }

    @Override
    public String pause() {
        return radio.changeFrequency();
    }

    @Override
    public String forward() {
        return radio.tuneUp();
    }

    @Override
    public String rewind() {
        return radio.tuneDown();
    }



    @Override
    public boolean isOn() {
        return radio.isOn();
    }

    @Override
    public boolean isMuted() {
        return radio.isMuted();
    }
//
    @Override
    public boolean isPlaying() {
        return radio.isOn();
    }
}
