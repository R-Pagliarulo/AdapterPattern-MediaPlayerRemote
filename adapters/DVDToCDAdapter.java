package adapters;

import interfaces.CDPlayer;
import interfaces.DVDPlayer;

/**
 * An adapter class allowing for a DVDPlayer object to handle requests designated for a CDPlayer object.
 * Allows for the universal CD player remote to control a DVD player.
 *
 * @author Robert Pagliarulo
 */
public class DVDToCDAdapter implements CDPlayer {
    private DVDPlayer dvdPlayer;

    public DVDToCDAdapter(DVDPlayer dvdPlayer) {
        this.dvdPlayer = dvdPlayer;
    }

    @Override
    public String on() {
        return dvdPlayer.on();
    }

    @Override
    public String off() {
        return dvdPlayer.off();
    }

    @Override
    public String mute() {
        return dvdPlayer.mute();
    }

    @Override
    public String unMute() {
        return dvdPlayer.unMute();
    }

    @Override
    public String volumeUp() {
        return dvdPlayer.volumeUp();
    }

    @Override
    public String volumeDown() {
        return dvdPlayer.volumeDown();
    }



    @Override
    public String play() {
        return dvdPlayer.playVideo();
    }

    @Override
    public String pause() {
        return dvdPlayer.pauseVideo();
    }

    @Override
    public String forward() {
        return dvdPlayer.forwardVideo();
    }

    @Override
    public String rewind() {
        return dvdPlayer.rewindVideo();
    }



    @Override
    public boolean isOn() {
        return dvdPlayer.isOn();
    }

    @Override
    public boolean isMuted() {
        return dvdPlayer.isMuted();
    }

    @Override
    public boolean isPlaying() {
        return dvdPlayer.isPlaying();
    }
}
