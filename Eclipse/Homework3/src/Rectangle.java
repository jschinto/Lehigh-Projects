/**
 * @author jake
 * @version 2017.02.12
 */
public class Rectangle implements Shape {
    private double width;
    private double length;

    /**
     * sets width to 0 sets length to 0
     */
    public Rectangle() {
        width = 0;
        length = 0;
    }

    /**
     * @param inWidth
     *            sets width
     * @param inLength
     *            sets length
     */
    public Rectangle(double inWidth, double inLength) {
        width = inWidth;
        length = inLength;
    }

    /**
     * @return area
     */
    @Override
    public double Area() {
        return length * width;
    }

    /**
     * @return perimeter
     */
    @Override
    public double Perimeter() {
        return (2 * width) + (2 * length);
    }

    /**
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return length
     */
    public double getLength() {
        return length;
    }
}