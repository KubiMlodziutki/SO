import java.util.*;
public class OPT extends Algorithm {

    public OPT(int framesCount) {
        super("OPT", framesCount);
    }

    public void replacePage(Page page, int time) {
        if (!isInMemory(page)) { // jeśli strony nie ma w pamięci fizycznej
            faults++;
            Frame freeFrame = findFreeFrame(); // szukamy wolnej ramki
            if (freeFrame != null) {
                freeFrame.setPage(page); // umieszczamy w niej stronę
            } else {
                int maxNextAccess = -1; // zmienna przechowująca największy czas najbliższego odwołania do strony w pamięci fizycznej
                int victimIndex = -1; // zmienna przechowująca indeks ramki ze stroną "ofiarą"
                for (int i = 0; i < framesCount; i++) {
                    Page p = frames.get(i).getPage();
                    if (p.getNextAccess() > maxNextAccess) { // jeśli czas najbliższego odwołania do tej strony jest większy niż dotychczasowy maksimum
                        maxNextAccess = p.getNextAccess();
                        victimIndex = i; // zapamiętujemy indeks ramki ze stroną ofiarą
                    }
                }
                frames.get(victimIndex).setPage(page); // zastępujemy stronę ofiarę nową stroną
            }
        }
    }
}

