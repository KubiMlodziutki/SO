import java.util.ArrayList;
import java.util.List;

public class CSCAN {
    private final int MAX_BLOCK;
    private int currentBlock;
    private int direction;
    private List<Integer> requests;
    private List<Integer> headMovements;
    private int result;

    public CSCAN(int maxBlock) {
        MAX_BLOCK = maxBlock;
        currentBlock = 0;
        direction = 1;
        requests = new ArrayList<>();
        headMovements = new ArrayList<>();
        result = 0;
    }

    public void addRequest(int request) {
        requests.add(request);
    }

    public void serviceRequests() {
        requests.sort(null);
        int index = requests.indexOf(currentBlock);
        if (index == -1) {
            if (direction == 1) {
                headMovements.add(MAX_BLOCK - currentBlock);
                currentBlock = MAX_BLOCK;
                headMovements.add(MAX_BLOCK);
                currentBlock = 0;
                headMovements.add(requests.get(0));
                currentBlock = requests.get(0);
            } else {
                headMovements.add(currentBlock);
                currentBlock = 0;
                headMovements.add(0);
                currentBlock = MAX_BLOCK;
                headMovements.add(MAX_BLOCK - requests.get(requests.size() - 1));
                currentBlock = requests.get(requests.size() - 1);
            }
        } else {
            for (int i = index; i < requests.size(); i++) {
                headMovements.add(Math.abs(requests.get(i) - currentBlock));
                currentBlock = requests.get(i);
            }
            if (direction == 1) {
                headMovements.add(MAX_BLOCK - currentBlock);
                currentBlock = MAX_BLOCK;
            } else {
                headMovements.add(currentBlock);
                currentBlock = 0;
            }
            for (int i = 0; i < index; i++) {
                headMovements.add(Math.abs(requests.get(i) - currentBlock));
                currentBlock = requests.get(i);
            }
        }
        for (int i = 0; i < headMovements.size(); i++){
            result += headMovements.get(i);
        }
    }

    public int result() {
        return result;
    }
}