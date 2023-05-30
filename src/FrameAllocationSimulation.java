import java.util.*;

public class FrameAllocationSimulation {
    private static final int NUM_PROCESSES = 10;
    private static final int NUM_FRAMES = 15;
    private static final int NUM_REFERENCES = 70;
    private static final int[] FRAMES_PER_PROCESS = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
    private static final int[] PAGES_PER_PROCESS = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    private static final int[][] REFERENCES = new int[NUM_PROCESSES][NUM_REFERENCES];

    public static void main(String[] args) {
        generateReferences();
        runSimulation();
    }

    private static void generateReferences() {
        Random rand = new Random();
        for (int i = 0; i < NUM_PROCESSES; i++) {
            for (int j = 0; j < NUM_REFERENCES; j++) {
                REFERENCES[i][j] = rand.nextInt(PAGES_PER_PROCESS[i]);
            }
        }
    }

    private static void runSimulation() {
        System.out.println("Assumptions of the simulation:");
        System.out.println("Number of processes: " + NUM_PROCESSES);
        System.out.println("Number of frames: " + NUM_FRAMES);
        System.out.println("Number of references per process: " + NUM_REFERENCES);
        System.out.println("Frames per process: " + Arrays.toString(FRAMES_PER_PROCESS));
        System.out.println("Pages per process: " + Arrays.toString(PAGES_PER_PROCESS));

        int[] pageFaultsProportional = runProportionalAllocation(NUM_PROCESSES, NUM_FRAMES, FRAMES_PER_PROCESS, REFERENCES);
        int[] pageFaultsEqual = runEqualAllocation(NUM_PROCESSES, NUM_FRAMES, REFERENCES);
        int[] pageFaultsControl = new int[NUM_PROCESSES];
        int[] pageFaultsZone = new int[NUM_PROCESSES];

        for (int i = 0; i < NUM_PROCESSES; i++) {
            pageFaultsControl[i] = runLRU(i, (int) (NUM_FRAMES * (1.0 * PAGES_PER_PROCESS[i] / getTotalPages())));
            pageFaultsZone[i] = runLRU(i, (int) (NUM_FRAMES * (1.0 * PAGES_PER_PROCESS[i] / getMaxPages())));
        }

        System.out.println("\nResults of the simulation:");
        System.out.println("Proportional allocation: " + Arrays.toString(pageFaultsProportional));
        System.out.println("Total page faults for Proportional allocation: " + sumArray(pageFaultsProportional));
        System.out.println();
        System.out.println("Equal allocation: " + Arrays.toString(pageFaultsEqual));
        System.out.println("Total page faults for Equal allocation: " + sumArray(pageFaultsEqual));
        System.out.println();
        System.out.println("Control page error rate: " + Arrays.toString(pageFaultsControl));
        System.out.println("Total page faults for Control page error rate: " + sumArray(pageFaultsControl));
        /*System.out.println("Zone model: " + Arrays.toString(pageFaultsZone));
        System.out.println("Total page faults for Zone model: " + sumArray(pageFaultsZone));*/
    }

    public static int[] runProportionalAllocation(int numProcesses, int numFrames, int[] framesPerProcess, int[][] references) {
        int[] pageFaults = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            pageFaults[i] = runLRU(i, framesPerProcess[i], references);
        }
        return pageFaults;
    }

    public static int[] runEqualAllocation(int numProcesses, int numFrames, int[][] references) {
        int[] pageFaults = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            pageFaults[i] = runLRU(i, numFrames / numProcesses, references);
        }
        return pageFaults;
    }

    private static int runLRU(int processId, int numFrames) {
        Set<Integer> frames = new HashSet<>();
        Map<Integer, Integer> pageIndexes = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < NUM_REFERENCES; i++) {
            if (frames.size() < numFrames) {
                if (!frames.contains(REFERENCES[processId][i])) {
                    frames.add(REFERENCES[processId][i]);
                    pageFaults++;
                }
                pageIndexes.put(REFERENCES[processId][i], i);
            } else {
                if (!frames.contains(REFERENCES[processId][i])) {
                    int lru = Integer.MAX_VALUE;
                    int val = Integer.MIN_VALUE;
                    Iterator<Integer> itr = frames.iterator();
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (pageIndexes.get(temp) < lru) {
                            lru = pageIndexes.get(temp);
                            val = temp;
                        }
                    }
                    frames.remove(val);
                    frames.add(REFERENCES[processId][i]);
                    pageFaults++;
                }
                pageIndexes.put(REFERENCES[processId][i], i);
            }
        }
        return pageFaults;
    }
    private static int runLRU(int processId, int numFrames, int[][] references) {
        Set<Integer> frames = new HashSet<>();
        Map<Integer,Integer> pageIndexes= new HashMap<>();
        int pageFaults = 0;
        int numReferences = references[processId].length;

        for (int i = 0; i < numReferences; i++) {
            if (frames.size() < numFrames) {
                if (!frames.contains(references[processId][i])) {
                    frames.add(references[processId][i]);
                    pageFaults++;
                }
                pageIndexes.put(references[processId][i], i);
            } else {
                if (!frames.contains(references[processId][i])) {
                    int lru = Integer.MAX_VALUE;
                    int val = Integer.MIN_VALUE;
                    Iterator<Integer> itr = frames.iterator();
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (pageIndexes.get(temp) < lru) {
                            lru = pageIndexes.get(temp);
                            val = temp;
                        }
                    }
                    frames.remove(val);
                    frames.add(references[processId][i]);
                    pageFaults++;
                }
                pageIndexes.put(references[processId][i], i);
            }
        }
        return pageFaults;
    }
    private static int getTotalPages() {
        int totalPages = 0;
        for (int pages : PAGES_PER_PROCESS) {
            totalPages += pages;
        }
        return totalPages;
    }

    private static int getMaxPages() {
        int maxPages = Integer.MIN_VALUE;
        for (int pages : PAGES_PER_PROCESS) {
            maxPages = Math.max(maxPages, pages);
        }
        return maxPages;
    }

    private static int sumArray(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

}