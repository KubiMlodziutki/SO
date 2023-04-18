import java.util.LinkedList;
import java.util.Queue;

public class SSTF {
    private int head;
    private int totalMovement;
    private Queue<Integer> requests;

    public SSTF() {
        head = 0;
        totalMovement = 0;
        requests = new LinkedList<>();
    }

    public void addRequest(int request) {
        requests.add(request);
    }

    public void serviceRequests() {
        while (!requests.isEmpty()) {
            int shortestDistance = Integer.MAX_VALUE;
            int selectedRequest = 0;
            for (int request : requests) {
                int distance = Math.abs(request - head);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    selectedRequest = request;
                }
            }
            totalMovement += shortestDistance;
            head = selectedRequest;
            requests.remove(selectedRequest);
        }
    }

    public int result() {
        return totalMovement;
    }
}
