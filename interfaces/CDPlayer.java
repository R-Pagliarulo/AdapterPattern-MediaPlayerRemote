package interfaces;

/**
 * Interface representing any CD Player.
 * It defines the methods a CD Player must implement.
 * BoseCDPlayer implements this interface.
 * The Driver makes requests on objects implementing this interface.
 * RadioToCDAdapter and DVDToCDAdapter implement this interface in order to allow other interfaces to fulfill requests.
 *
 * @author Robert Pagliarulo
 */
public interface CDPlayer extends BasicMediaPlayer{
    public String play();
    public String pause();
    public String forward();
    public String rewind();

    public boolean isPlaying();
}
