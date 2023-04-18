import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCAN{
    private final int maxBlock;
    private List<Integer> requests;
    private int headPosition;
    private int direction;
    private int result;

    public SCAN(int maxBlock) {
        this.maxBlock = maxBlock;
        requests = new ArrayList<>();
        headPosition = 0;
        direction = 1;
        result = 0;
    }


    public void addRequest(int request) {
        requests.add(request);
    }


    public void serviceRequests() {
        Collections.sort(requests);

        int index = Collections.binarySearch(requests, headPosition);
        if (index < 0) {
            index = -index - 1;
        }
        boolean notEmpty = true;
        while (notEmpty) {
            if (index < requests.size() && requests.get(index) <= maxBlock && requests.get(index) >= 0) {
                result += Math.abs(headPosition - requests.get(index));
                headPosition = requests.get(index);
                requests.remove(index);
            } else {
                if (direction == 1) {
                    direction = -1;
                    result += headPosition;
                    headPosition = 0;
                    index = Collections.binarySearch(requests, headPosition);
                    if (index < 0) {
                        index = -index - 1;
                    }
                } else {
                    direction = 1;
                    result += maxBlock - headPosition;
                    headPosition = maxBlock;
                    index = Collections.binarySearch(requests, headPosition);
                    if (index < 0) {
                        index = -index - 1;
                    }
                }
            }

            if (requests.isEmpty()) {
                notEmpty = false;
            }
        }
    }

    public int result() {
        return result;
    }
}