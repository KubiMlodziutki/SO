import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EDF {
    private Queue<Request> pendingRequests;
    private Queue<Request> servedRequests;
    private PriorityQueue<Request> priorityQueue;
    private int currentPosition;
    private int maxPriority;

    public EDF(int maxPriority) {
        this.maxPriority = maxPriority;
        this.currentPosition = 0;
        this.pendingRequests = new LinkedList<>();
        this.servedRequests = new LinkedList<>();
        Comparator<Request> comparator = Comparator.comparingInt(Request::getDeadline);
        this.priorityQueue = new PriorityQueue<>(comparator);
    }

    public void addRequest(int position, int deadline) {
        Request request = new Request(position, deadline);
        pendingRequests.add(request);
        priorityQueue.add(request);
    }

    public void serviceRequests() {
        while (!priorityQueue.isEmpty()) {
            Request request = priorityQueue.poll();
            int requestedPosition = request.getPosition();
            int distance = Math.abs(currentPosition - requestedPosition);
            currentPosition = requestedPosition;
            servedRequests.add(request);
        }
    }

    public int result() {
        int totalDistance = 0;
        int lastPosition = 0;
        for (Request request : servedRequests) {
            int requestedPosition = request.getPosition();
            totalDistance += Math.abs(requestedPosition - lastPosition);
            lastPosition = requestedPosition;
        }
        return totalDistance;
    }

    private class Request {
        private int position;
        private int deadline;

        public Request(int position, int deadline) {
            this.position = position;
            this.deadline = deadline;
        }

        public int getPosition() {
            return position;
        }

        public int getDeadline() {
            return deadline;
        }
    }
}