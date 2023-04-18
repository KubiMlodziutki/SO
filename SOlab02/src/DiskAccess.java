import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class DiskAccess {
    private final int maxBlockAmount = 100;
    private final int maxRequestAmount = 50;
    private final int priorityMax = 10;
    private Random random;
    private Queue<Integer> requests;
    private FCFS fcfs;
    private SSTF sstf;
    private SCAN scan;
    private CSCAN cscan;
    private EDF edf;
    private FDSCAN fdscan;

    public DiskAccess() {
        random = new Random();
        requests = new LinkedList<>();
        fcfs = new FCFS();
        sstf = new SSTF();
        scan = new SCAN(maxBlockAmount);
        cscan = new CSCAN(maxBlockAmount);
        edf = new EDF(priorityMax);
        fdscan = new FDSCAN(maxBlockAmount, priorityMax);
    }

    private void generateRequests() {
        for (int i = 0; i < maxRequestAmount; i++) {
            requests.add(random.nextInt(maxBlockAmount) + 1);
        }
    }

    public void startSimulation() {
        generateRequests();

        // FCFS
        for (int request : requests) {
            fcfs.addRequest(request);
        }
        fcfs.serviceRequests();
        System.out.println("FCFS Result: " + fcfs.result());

        // SSTF
        for (int request : requests) {
            sstf.addRequest(request);
        }
        sstf.serviceRequests();
        System.out.println("SSTF Result: " + sstf.result());

        // SCAN
        for (int request : requests) {
            scan.addRequest(request);
        }
        scan.serviceRequests();
        System.out.println("SCAN Result: " + scan.result());

        // C-SCAN
        for (int request : requests) {
            cscan.addRequest(request);
        }
        cscan.serviceRequests();
        System.out.println("C-SCAN Result: " + cscan.result());

        // EDF
        for (int i = 0; i < priorityMax; i++) {
            int deadline = random.nextInt(maxBlockAmount) + 1;
            int request = random.nextInt(maxBlockAmount) + 1;
            edf.addRequest(request, deadline);
        }
        edf.serviceRequests();
        System.out.println("EDF Result: " + edf.result());

        // FD-SCAN
        for (int i = 0; i < priorityMax; i++) {
            int deadline = random.nextInt(maxBlockAmount) + 1;
            int request = random.nextInt(maxBlockAmount) + 1;
            fdscan.addRequest(request, deadline);
        }
        fdscan.serviceRequests();
        System.out.println("FD-SCAN Result: " + fdscan.result());
    }

    public static void main(String[] args) {
        DiskAccess disk = new DiskAccess();
        System.out.println("Total head movements:");
        disk.startSimulation();
    }
}
