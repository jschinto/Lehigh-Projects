/**
 * @author jake
 * @version 2017.02.13
 */
public interface BundledSubscription {
    /**
     * @return title
     */
    public abstract String getTitle();

    /**
     * @param inBundledSubscription gets other BundledSubscription
     * @return title and otherTitle subscriptions are bundled
     */
    public abstract String bundledWith(
            BundledSubscription inBundledSubscription);
}