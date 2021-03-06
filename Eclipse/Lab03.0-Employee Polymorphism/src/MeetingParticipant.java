// -------------------------------------------------------------------------
/**
 * @author jake
 * @version 2017.02.10
 */
public interface MeetingParticipant {
    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Gets the name of the meeting participant.
     * 
     * @return the name of the meeting participant
     */
    public String getName();

    // ----------------------------------------------------------
    /**
     * Returns a message to announce that this participant is joining the given
     * participant in a meeting.
     * 
     * @param otherParticipant
     *            the other meeting participant
     * @return a message describing the nature of the meeting
     */
    public String meetWith(MeetingParticipant otherParticipant);
}
