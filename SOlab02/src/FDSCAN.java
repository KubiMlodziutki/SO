import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FDSCAN {
    private final int maxBlockAmount;
    private final int priorityMax;
    private int currentHead;
    private int currentDirection;
    private List<Request> requests;
    private List<Request> pendingRequests;
    private List<Request> completeRequests;
    private List<Integer> headMoves;
    private int result;

    public FDSCAN(int maxBlock, int maxPriority) {
        maxBlockAmount = maxBlock;
        priorityMax = maxPriority;
        currentHead = 0;
        currentDirection = 1;
        requests = new ArrayList<>();
        pendingRequests = new ArrayList<>();
        completeRequests = new ArrayList<>();
        headMoves = new ArrayList<>();
        result = 0;
    }

    public void addRequest(int block, int priority) {
        Request request = new Request(block, priority);
        if (request.getBlock() > maxBlockAmount) {
            throw new IllegalArgumentException("Invalid block number: " + request.getBlock());
        }
        requests.add(request);
    }

    private boolean isPendingEmpty() {
        return pendingRequests.isEmpty();
    }

    private void addToPending(Request request) {
        pendingRequests.add(request);
    }

    private void removeFromPending(Request request) {
        pendingRequests.remove(request);
    }

    private boolean isCompleteEmpty() {
        return completeRequests.isEmpty();
    }

    private void addToComplete(Request request) {
        completeRequests.add(request);
    }

    private void sortPending() {
        pendingRequests.sort(Comparator.comparing(Request::getPriority).reversed().thenComparing(Request::getBlock));
    }

    private void addToResult(Request request) {
        addToComplete(request);
        headMoves.add(request.getBlock() - currentHead);
    }

    private void moveHead() {
        currentHead += currentDirection;
        if (currentHead == 0 || currentHead == maxBlockAmount - 1) {
            currentDirection = -currentDirection;
        }
        headMoves.add(currentDirection);
    }


    public void serviceRequests() {
        int numIterations = 0;
        while (!requests.isEmpty() || !isPendingEmpty()) {
            for (Iterator<Request> it = requests.iterator(); it.hasNext(); ) {
                Request request = it.next();
                if (request.getBlock() == currentHead) {
                    addToPending(request);
                    it.remove();
                }
            }
            sortPending();
            if (isPendingEmpty()) {
                moveHead();
                numIterations++;
                if (numIterations > maxBlockAmount) {
                    break;
                }
                continue;
            }
            Request request = pendingRequests.get(0);
            addToResult(request);
            removeFromPending(request);
            moveHead();
            numIterations++;
            if (numIterations > maxBlockAmount) {
                break;
            }
        }
        for (Integer headMove : headMoves) {
            result += headMove;
        }
    }

    public int result() {
        return result;
    }
}