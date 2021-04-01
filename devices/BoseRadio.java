package devices;

import interfaces.Radio;

/**
 * Represents a Radio produced by Bose.
 * Implements the Radio interface.
 * Provides implementation for all methods from both the Radio and BasicMediaPlayer interfaces.
 * Includes AM/FM tuners.
 *
 * @author Robert Pagliarulo
 */
public class BoseRadio implements Radio {

    private boolean on, muted;
    private int volumeLevel;
    private String frequency;
    private int currentAMStation;
    private double currentFMStation;
    public static final String AM = "AM";
    public static final String FM = "FM";

    public BoseRadio() {
        on = false;
        muted = false;
        volumeLevel = 50;
        frequency = FM;
        currentAMStation = 1030;
        currentFMStation = 100.7;
    }

    @Override
    public String on() {
        if (!on) {
            on = true;
            if (frequency.equals(AM))
                return ("Radio has been turned on\nTuned in to AM " + currentAMStation);
            else
                return ("Radio has been turned on\nTuned in to FM " + currentFMStation);
        }
        return "Already on...";
    }

    @Override
    public String off() {
        if (on) {
            on = false;
            return ("Radio has been turned off");
        }
        return "Must be turned on first...";
    }

    @Override
    public String mute() {
        if ((on) && (!muted)) {
            muted = true;
            return ("Radio has been muted");
        }
        else if (on)
            return "";
        return "Must be turned on first...";
    }

    @Override
    public String unMute() {
        if ((on) && (muted)) {
            muted = false;
            return ("Radio has been un-muted");
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
                return (unMute() + "\nRadio volume increased to " + volumeLevel);
            }
            else
                return (unMute() + "\nRadio volume at 100 (Maximum)");
        }
        return "Must be turned on first...";
    }

    @Override
    public String volumeDown() {
        if (on) {
            if (volumeLevel > 1) {
                volumeLevel--;
                return (unMute() + "\nRadio volume decreased to " + volumeLevel);
            }
            else
                return (unMute() + "\nRadio volume at 1 (Minimum)");
        }
        return "Must be turned on first...";
    }



    @Override
    public String tuneUp() {
        if(on) {
            if (frequency.equals(AM)) {
                if (currentAMStation < 1600) {
                    currentAMStation += 10;
                    return ("Station set to AM " + currentAMStation);
                }
            }
            else {
                if (currentFMStation < 107.9) {
                    currentFMStation += 0.2;
                    currentFMStation = Math.round(currentFMStation * 10) / 10.0;
                    return ("Station set to FM " + currentFMStation);
                }
            }
        }
        return "Must be turned on first...";
    }

    @Override
    public String tuneDown() {
        if(on) {
            if (frequency.equals(AM)) {
                if (currentAMStation > 560) {
                    currentAMStation -= 10;
                    return ("AM station set to " + currentAMStation);
                }
            }
            else {
                if (currentFMStation > 88.1) {
                    currentFMStation -= 0.2;
                    currentFMStation = Math.round(currentFMStation * 10) / 10.0;
                    return ("FM station set to " + currentFMStation);
                }
            }
        }
        return "Must be turned on first...";
    }

    @Override
    public String changeFrequency() {
        if (on) {
            if (frequency.equals(AM)) {
                frequency = FM;
                return ("Frequency now set to FM\nTuned in to FM " + currentFMStation);
            } else {
                frequency = AM;
                return ("Frequency now set to AM\nTuned in to AM " + currentAMStation);
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
    public String getFrequency() {
        return frequency;
    }
}
