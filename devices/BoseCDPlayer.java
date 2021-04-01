package devices;

import interfaces.CDPlayer;

import java.util.ArrayList;

/**
 * Represents a CD Player produced by Bose.
 * Implements the CDPlayer interface.
 * Provides implementation for all methods from both the CDPlayer and BasicMediaPlayer interfaces.
 *
 * @author Robert Pagliarulo
 */
public class BoseCDPlayer implements CDPlayer {
    private boolean on, muted, playing;
    private int volumeLevel;
    private ArrayList<String> playList;
    private String cdTitle, currentSong;

    public BoseCDPlayer(String discName, ArrayList<String> trackList) {
        on = false;
        muted = false;
        playing = false;
        volumeLevel = 50;
        cdTitle = discName;
        playList = trackList;
        currentSong = playList.get(0);
    }

    @Override
    public String on() {
        if (!on) {
            on = true;
            return ("CD Player has been turned on" +
                             "\nDisc recognized as '" + cdTitle + "'");
        }
        return "Already on...";
    }

    @Override
    public String off() {
        if (on) {
            on = false;
            return ("CD Player has been turned off");
        }
        return "Must be turned on first...";
    }

    @Override
    public String mute() {
        if ((on) && (!muted)) {
            muted = true;
            return ("CD Player has been muted");
        }
        else if (on)
            return "";
        return "Must be turned on first...";
    }

    @Override
    public String unMute() {
        if ((on) && (muted)) {
            muted = false;
            return ("CD Player has been un-muted");
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
                return (unMute() + "\nCD Player volume increased to " + volumeLevel);
            }
            else
                return (unMute() + "\nCD Player volume at 100 (Maximum)");
        }
        return "Must be turned on first...";
    }

    @Override
    public String volumeDown() {
        if (on) {
            if (volumeLevel > 1) {
                volumeLevel--;
                return (unMute() + "\nCD Player volume decreased to " + volumeLevel);
            }
            else
                return (unMute() + "\nCD Player volume at 1 (Minimum)");
        }
        return "Must be turned on first...";
    }



    @Override
    public String play() {
        if (on) {
            if (!playing) {
                playing = true;
                return ("CD Player now playing " + currentSong + "...");
            }
            else
                return ("CD Player already playing " + currentSong + "...");
        }
        return "Must be turned on first...";
    }

    @Override
    public String pause() {
        if ((on) && (playing)) {
            playing = false;
            return ("CD Player now paused");
        }
        else
            return "Must be turned on first...";
    }

    @Override
    public String forward() {
        if (on) {
            playing = false;
            if (playList.size()-1 > playList.indexOf(currentSong)) {
                currentSong = playList.get(1+playList.indexOf(currentSong));
                return (play());
            }
            else {
                return ("last track on disc");
            }
        }
        return "Must be turned on first...";
    }

    @Override
    public String rewind() {
        if (on) {
            playing = false;
            if (playList.indexOf(currentSong) > 0) {
                currentSong = playList.get(playList.indexOf(currentSong) - 1);
                return (play());
            } else {
                return ("first track on disc");
            }
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
