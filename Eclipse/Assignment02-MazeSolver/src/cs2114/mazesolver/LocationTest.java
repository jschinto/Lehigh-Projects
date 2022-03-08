package cs2114.mazesolver;

import student.TestCase;

/**
 * @author jake
 * @version 2017.04.09
 */
public class LocationTest extends TestCase {
    private Location location1;
    private Location location2;
    private Location location3;

    /**
     * runs before each test
     */
    public void setUp() {
        location1 = new Location(0, 0);
        location2 = new Location(1, 1);
        location3 = new Location(0, 2);
    }

    /**
     * tests x() and y() methods
     */
    public void testXY() {
        assertEquals(0, location1.x());
        assertEquals(0, location1.y());
        assertEquals(1, location2.x());
        assertEquals(1, location2.y());
        assertEquals(0, location3.x());
        assertEquals(2, location3.y());
    }

    /**
     * tests the toString() method
     */
    public void testToString() {
        assertEquals("(0, 0)", location1.toString());
        assertEquals("(1, 1)", location2.toString());
        assertEquals("(0, 2)", location3.toString());
    }

    /**
     * tests the north south east and west methods
     */
    public void testNorthSouthEastWest() {
        assertEquals("(0, -1)", ((Location) location1.north()).toString());
        assertEquals("(0, 1)", ((Location) location1.south()).toString());
        assertEquals("(1, 0)", ((Location) location1.east()).toString());
        assertEquals("(-1, 0)", ((Location) location1.west()).toString());
    }

    /**
     * tests the equals method
     */
    public void testEquals() {
        assertTrue(location1.equals(location2.west().north()));
        assertFalse(location1.equals(location2));
        assertFalse(location1.equals("Hello"));
    }
}