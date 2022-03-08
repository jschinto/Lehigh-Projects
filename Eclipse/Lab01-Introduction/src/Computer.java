
/**
 * @author jake
 * @version 2017.01.27
 */
public class Computer {
    private String processor;
    private int numCores;
    private double processorSpeed;

    /**
     * @param inProcessor
     *            stores into processor
     * @param inNumCores
     *            stores into numCores
     * @param inProcessorSpeed
     *            stores into processorSpeed
     */
    public Computer(String inProcessor, int inNumCores,
            double inProcessorSpeed) {
        this.setProcessor(inProcessor);
        this.setNumCores(inNumCores);
        this.setProcessorSpeed(inProcessorSpeed);
    }

    /**
     * @return processor
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * @param processor
     *            stores into processor
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * @return numCores
     */
    public int getNumCores() {
        return numCores;
    }

    /**
     * @param numCores
     *            stores into numCores
     */
    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    /**
     * @return processorSpeed
     */
    public double getProcessorSpeed() {
        return processorSpeed;
    }

    /**
     * @param processorSpeed
     *            stores into processorSpeed
     */
    public void setProcessorSpeed(double processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    /**
     * @return numCores * processorSpeed
     */
    public double computePower() {
        return numCores * processorSpeed;
    }

    /**
     * @return processor, numCores cores at processorSpeed GHz
     */
    public String toString() {
        if (numCores == 1) {
            return processor + ", " + numCores + " core at " + processorSpeed
                    + "GHz";
        }
        else {
            return processor + ", " + numCores + " cores at " + processorSpeed
                    + "GHz";
        }
    }
}
