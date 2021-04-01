package devices;

import interfaces.DVDPlayer;

/**
 * Represents a DVD Player produced by Bose.
 * Implements the DVDPlayer interface.
 * Provides implementation for all methods from both the DVDPlayer and BasicMediaPlayer interfaces.
 *
 * @author Robert Pagliarulo
 */
public class BoseDVDPlayer implements DVDPlayer {
    private boolean on, muted, playing;
    private int volumeLevel;
    private String dvdTitle;

    public BoseDVDPlayer(String discName) {
        on = false;
        muted = false;
        playing = false;
        volumeLevel = 50;
        dvdTitle = discName;
    }

    @Override
    public String on() {
        if (!on) {
            on = true;
            return ("DVD Player has been turned on" +
                             "\nDisc recognized as '" + dvdTitle + "'");
        }
        return "Already on...";
    }

    @Override
    public String off() {
        if (on) {
            on = false;
            playing = false;
            return ("DVD Player has been turned off");
        }
        return "Must be turned on first...";
    }

    @Override
    public String mute() {
        if ((on) && (!muted)) {
            muted = true;
            return ("DVD Player has been muted");
        }
        else if (on)
            return "";
        return "Must be turned on first...";
    }

    @Override
    public String unMute() {
        if ((on) && (muted)) {
            muted = false;
            return ("DVD Player has been un-muted");
        }
        else if (on)
            return "";
        return "Must be turned on first...";
    }

    @Override
    public String volumeUp() {
        if (on) {
            if (volumeLevel < 100) {
                volumeLevel++;
                return (unMute() + "\nDVD Player volume increased to " + volumeLevel);
            }
            else
                return(unMute() + "\nDVD Player volume at 100 (Maximum)");
        }
        return "Must be turned on first...";
    }

    @Override
    public String volumeDown() {
        if (on) {
            if (volumeLevel > 1) {
                volumeLevel--;
                return (unMute() + "\nDVD Player volume decreased to " + volumeLevel);
            }
            else
                return (unMute() + "\nDVD Player volume at 1 (Minimum)");
        }
        return "Must be turned on first...";
    }



    @Override
    public String playVideo() {
        if ((on) && (!playing)) {
            playing = true;
            return ("Now playing " + dvdTitle + "...");
        }
        return "Must be turned on first...";
    }

    @Override
    public String pauseVideo() {
        if ((on) && (playing)) {
            playing = false;
            return ("Pausing " + dvdTitle + "...");
        }
        return "Must be turned on first...";
    }

    @Override
    public String forwardVideo() {
        if (on) {
            return ("fast forwarding " + dvdTitle + "...");
        }
        return "Must be turned on first...";
    }

    @Override
    public String rewindVideo() {
        if (on) {
            return ("rewinding " + dvdTitle + "...");
        }
        return "Must be turned on first...";
    }

    @Override
    public String recordVideo() {
        if (on) {
            return ("recording now...");
        }
        return "Must be turned on first...";
    }



    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public boolean isMuted() {
        return muted;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }
}
