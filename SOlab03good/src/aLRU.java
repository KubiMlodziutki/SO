public class aLRU extends Algorithm {

    // Konstruktor klasy aLRU
    public aLRU(int framesCount) {
        super("aLRU", framesCount);
    }

    // Metoda realizująca algorytm aproksymowany LRU (aLRU)
    public void replacePage(Page page, int time) {
        if (!isInMemory(page)) { // jeśli strony nie ma w pamięci fizycznej
            faults++;
            Frame freeFrame = findFreeFrame();
            if (freeFrame != null) { // jeśli znaleźliśmy wolną ramkę, umieszczamy w niej stronę
                freeFrame.setPage(page);
            } else {
                int victimIndex = -1; // zmienna przechowująca indeks ramki ze stroną "ofiarą"
                boolean found = false;
                for (int i = 0; i < framesCount && !found; i++) {
                    Page p = frames.get(i).getPage();
                    if (p.getLastAccess() % 2 == 0) { // jeśli czas ostatniego odwołania do tej strony jest parzysty
                        victimIndex = i;
                        found = true;
                    }
                }
                if (!found) {
                    victimIndex = 0;
                }
                frames.get(victimIndex).setPage(page); // zastępujemy stronę ofiarę nową stroną
            }
        }
        page.setLastAccess(page.getLastAccess() * 2 + 1); // ustawiamy czas ostatniego odwołania do strony na nieparzystą liczbę większą od poprzedniej
    }
}
