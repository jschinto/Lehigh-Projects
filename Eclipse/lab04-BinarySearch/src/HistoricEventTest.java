import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link HistoricEvent} class.
 *
 * @author jake
 * @version 2017.02.16
 */
public class HistoricEventTest extends TestCase {
    // ~ Instance/static variables .............................................

    private HistoricEvent event;

    private static final String TITLE = "CS 2114 Lab";
    private static final String MESSAGE = 
            "I went to lab today, and I didn't even get a T-shirt.";

    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public HistoricEventTest() {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Starting conditions for all tests in this class.
     */
    public void setUp() {
        event = new HistoricEvent(2010, TITLE, MESSAGE);
    }

    // ----------------------------------------------------------
    /**
     * Test the constructor and accessors.
     */
    public void testAccessors() {
        assertEquals(2010, event.getYear());
        assertEquals(TITLE, event.getTitle());
        assertEquals(MESSAGE, event.getDescription());
    }

    // ----------------------------------------------------------
    /**
     * Test toString() on the event created in setUp().
     */
    public void testToString() {
        assertEquals("[2010] " + TITLE + ": " + MESSAGE, event.toString());
    }

    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null title.
     */
    public void testToString2() {
        event = new HistoricEvent(2010, MESSAGE);
        assertEquals("[2010] " + MESSAGE, event.toString());
    }

    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null description.
     */
    public void testToString3() {
        event = new HistoricEvent(2010, TITLE, null);
        assertEquals("[2010] " + TITLE, event.toString());
    }

    /**
     * test compareTo()
     */
    public void testCompareTo() {
        @SuppressWarnings("unused")
        HistoricEvents t = new HistoricEvents();
        HistoricEvent o = HistoricEvents.TIMELINE[0];
        o = new HistoricEvent(2015, TITLE, MESSAGE);
        assertEquals(-5, event.compareTo(o));
        o = new HistoricEvent(2010, "CS 2110 Lab", MESSAGE);
        assertEquals(4, event.compareTo(o));
        assertEquals(0, event.compareTo(event));
    }

    /**
     * test find()
     */
    public void testFind() {
        EventFinder findEvent = new EventFinder();
        HistoricEvent[] events = { HistoricEvents.TIMELINE[0],
                HistoricEvents.TIMELINE[1], HistoricEvents.TIMELINE[2],
                HistoricEvents.TIMELINE[3] };
        assertEquals(0,
                findEvent.find(HistoricEvents.TIMELINE[0], events, 0, 4));
        HistoricEvent[] events2 = { HistoricEvents.TIMELINE[0],
                HistoricEvents.TIMELINE[1], HistoricEvents.TIMELINE[2],
                HistoricEvents.TIMELINE[3], HistoricEvents.TIMELINE[5] };
        HistoricEvent[] events3 = { HistoricEvents.TIMELINE[0],
                HistoricEvents.TIMELINE[2], HistoricEvents.TIMELINE[3],
                HistoricEvents.TIMELINE[5] };
        HistoricEvent[] events4 = { HistoricEvents.TIMELINE[0],
                HistoricEvents.TIMELINE[1], HistoricEvents.TIMELINE[3],
                HistoricEvents.TIMELINE[5], HistoricEvents.TIMELINE[6] };
        HistoricEvent[] events5 = { HistoricEvents.TIMELINE[1],
                HistoricEvents.TIMELINE[3] };
        HistoricEvent[] events6 = { HistoricEvents.TIMELINE[1] };
        assertEquals(0,
                findEvent.find(HistoricEvents.TIMELINE[0], events2, 0, 5));
        assertEquals(1,
                findEvent.find(HistoricEvents.TIMELINE[1], events, 0, 4));
        assertEquals(1,
                findEvent.find(HistoricEvents.TIMELINE[1], events2, 0, 5));
        assertEquals(1,
                findEvent.find(HistoricEvents.TIMELINE[1], events3, 0, 4));
        assertEquals(2,
                findEvent.find(HistoricEvents.TIMELINE[2], events, 0, 4));
        assertEquals(2,
                findEvent.find(HistoricEvents.TIMELINE[2], events2, 0, 5));
        assertEquals(3,
                findEvent.find(HistoricEvents.TIMELINE[3], events, 0, 4));
        assertEquals(3,
                findEvent.find(HistoricEvents.TIMELINE[3], events2, 0, 5));
        assertEquals(4,
                findEvent.find(HistoricEvents.TIMELINE[4], events, 0, 4));
        assertEquals(4,
                findEvent.find(HistoricEvents.TIMELINE[4], events2, 0, 5));
        assertEquals(4,
                findEvent.find(HistoricEvents.TIMELINE[5], events, 0, 4));
        assertEquals(4,
                findEvent.find(HistoricEvents.TIMELINE[5], events2, 0, 5));
        assertEquals(1, findEvent.find(HistoricEvents.TIMELINE[1], events3));
        assertEquals(1, findEvent.find(HistoricEvents.TIMELINE[2], events3));
        assertEquals(2, findEvent.find(HistoricEvents.TIMELINE[3], events3));
        assertEquals(2, findEvent.find(HistoricEvents.TIMELINE[2], events4));
        assertEquals(2, findEvent.find(HistoricEvents.TIMELINE[3], events4));
        assertEquals(3, findEvent.find(HistoricEvents.TIMELINE[4], events4));
        assertEquals(3, findEvent.find(HistoricEvents.TIMELINE[5], events4));
        assertEquals(4, findEvent.find(HistoricEvents.TIMELINE[6], events4));
        assertEquals(5, findEvent.find(HistoricEvents.TIMELINE[7], events4));
        assertEquals(0, findEvent.find(HistoricEvents.TIMELINE[0], events5));
        assertEquals(0, findEvent.find(HistoricEvents.TIMELINE[1], events5));
        assertEquals(1, findEvent.find(HistoricEvents.TIMELINE[2], events5));
        assertEquals(1, findEvent.find(HistoricEvents.TIMELINE[3], events5));
        assertEquals(2, findEvent.find(HistoricEvents.TIMELINE[4], events5));
        assertEquals(0, findEvent.find(HistoricEvents.TIMELINE[0], events6));
        assertEquals(0, findEvent.find(HistoricEvents.TIMELINE[1], events6));
        assertEquals(1, findEvent.find(HistoricEvents.TIMELINE[2], events6));
    }
}
