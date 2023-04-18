import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FCFS {
    private int headPosition;
    private Queue<Integer> requests;
    private int currentPosition;
    private int headMovements;

    public FCFS() {
        headPosition = 0;
        requests = new LinkedList<>();
    }

    public void addRequest(int blockNumber) {
        requests.add(blockNumber);
    }

    public void serviceRequests() {
        while (!requests.isEmpty()) {
            int requestedBlock = requests.poll();
            headMovements += Math.abs(requestedBlock - currentPosition);
            currentPosition = requestedBlock;
        }
    }

    public int result() {
        return headMovements;
    }
}