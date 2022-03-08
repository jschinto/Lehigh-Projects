/**
 * @author jake
 * @version 2017.02.17
 */
public class EventFinder {
    /**
     * @param target
     *            target event
     * @param search
     *            array to search
     * @param start
     *            start index
     * @param end
     *            end index
     * @return index of target
     */
    public int find(HistoricEvent target, HistoricEvent[] search, int start,
            int end) {
        end = end - 1;
        int mid = (end + start) / 2;
        if (target.compareTo(search[mid]) > 0) {
            start = mid + 1;
            if (mid + 1 >= search.length) {
                return search.length;
            }
            else if (target.compareTo(search[mid + 1]) < 0) {
                return mid + 1;
            }
        }
        else if (target.compareTo(search[mid]) < 0) {
            end = mid - 1;
            if (mid - 1 <= 0) {
                if (target.compareTo(search[0]) <= 0) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else if (target.compareTo(search[mid - 1]) > 0) {
                return mid;
            }
        }
        else {
            return mid;
        }
        return find(target, search, start, end + 1);
    }

    /**
     * @param target
     *            target event
     * @param search
     *            array to search
     * @return find result of whole array
     */
    public int find(HistoricEvent target, HistoricEvent[] search) {
        return find(target, search, 0, search.length);
    }
}