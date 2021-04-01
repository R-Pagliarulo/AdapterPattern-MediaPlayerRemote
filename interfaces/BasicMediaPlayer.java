package interfaces;

/**
 * Interface representing a simple generic Media Player with the bare minimum of functionality.
 * Declares methods that are common among any current (or future) Media Player devices.
 * In this application the CDPlayer, DVDPlayer, and Radio interfaces inherit from this interface.
 * While this wasn't necessary, it lead to a simpler design and reduction of duplicated code.
 *
 * @author Robert Pagliarulo
 */
public interface BasicMediaPlayer {
    public String on();
    public String off();
    public String mute();
    public String unMute();
    public String volumeUp();
    public String volumeDown();

    public boolean isOn();
    public boolean isMuted();
}
