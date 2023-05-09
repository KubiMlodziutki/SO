import java.util.*;
public class RAND extends Algorithm {

    public RAND(int framesCount) {
        super("RAND", framesCount);
    }

    public void replacePage(Page page, int time) {
        if (!isInMemory(page)) { // jeśli strony nie ma w pamięci fizycznej
            faults++;
            Frame freeFrame = findFreeFrame(); // szukamy wolnej ramki
            if (freeFrame != null) {
                freeFrame.setPage(page); // umieszczamy w niej stronę
            } else {
                int victim = (int) (Math.random() * framesCount); // losujemy indeks ramki ze stroną ofiarą
                frames.get(victim).setPage(page); // zastępujemy stronę ofiarę nową stroną
            }
        }
    }
}

