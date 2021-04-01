package interfaces;

/**
 * Interface representing any Radio.
 * It defines the methods a Radio must implement.
 * A Radio can be for AM, FM, or both frequencies.
 * BoseRadio implements this interface, and has a tuner for both AM and FM.
 *
 * @author Robert Pagliarulo
 */
public interface Radio extends BasicMediaPlayer {
    public String tuneUp();
    public String tuneDown();
    public String changeFrequency(); // implemented method does nothing if only one AM or FM supported

    public String getFrequency();
}
