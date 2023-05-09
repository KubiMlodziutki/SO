import java.util.*;
public class FIFO extends Algorithm {

    public FIFO(int framesCount) {
        super("FIFO", framesCount);
    }

    public void replacePage(Page page, int time) {
        if (!isInMemory(page)) { // jeśli strony nie ma w pamięci fizycznej
            faults++;
            Frame freeFrame = findFreeFrame(); // szukamy wolnej ramki
            if (freeFrame != null) {
                freeFrame.setPage(page); // umieszczamy w niej stronę
            } else {
                frames.remove(0); // usuwamy pierwszą stronę z listy ramek (najdłużej przebywającą w pamięci)
                frames.add(new Frame(framesCount - 1)); // dodajemy nową ramkę na końcu listy
                frames.get(framesCount - 1).setPage(page); // umieszczamy w niej stronę
            }
        }
    }
}
