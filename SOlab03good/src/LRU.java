import java.util.*;
public class LRU extends Algorithm {

    public LRU(int framesCount) {
        super("LRU", framesCount);
    }

    public void replacePage(Page page, int time) {
        if (!isInMemory(page)) { // jeśli strony nie ma w pamięci fizycznej
            faults++;
            Frame freeFrame = findFreeFrame(); // szukamy wolnej ramki
            if (freeFrame != null) {
                freeFrame.setPage(page); // umieszczamy w niej stronę
            } else {
                int minLastAccess = Integer.MIN_VALUE; // zmienna przechowująca najmniejszy czas ostatniego odwołania do strony w pamięci fizycznej
                int victimIndex = -1; // zmienna przechowująca indeks ramki ze stroną "ofiarą"
                for (int i = 0; i < framesCount; i++) {
                    Page p = frames.get(i).getPage(); // pobieramy stronę z ramki
                    if (p.getLastAccess() > minLastAccess) { // jeśli czas ostatniego odwołania do tej strony jest mniejszy niż dotychczasowe minimum
                        minLastAccess = p.getLastAccess(); // aktualizujemy minimum
                        victimIndex = i; // zapamiętujemy indeks ramki ze stroną ofiarą
                    }
                }
                frames.get(victimIndex).setPage(page); // zastępujemy stronę ofiarę nową stroną
            }
        }
        page.setLastAccess(time); // ustawiamy czas ostatniego odwołania do strony na bieżący czas
    }
}
