package interfaces;

/**
 * Interface representing any DVD Player.
 * It defines the methods a DVD Player must implement.
 * BoseDVDPlayer implements this interface.
 *
 * @author Robert Pagliarulo
 */
public interface DVDPlayer extends BasicMediaPlayer {
    public String playVideo();
    public String pauseVideo();
    public String forwardVideo();
    public String rewindVideo();
    public String recordVideo();

    public boolean isPlaying();
}
