import java.util.*;
public class Simulation {
    // Liczba stron pamięci wirtualnej
    private int pagesCount;
    // Liczba ramek pamięci fizycznej
    private int framesCount;
    // Długość ciągu odwołań do stron
    private int length;
    // Lista stron pamięci wirtualnej
    private List<Page> pages;
    // Lista odwołań do stron (numery stron)
    private List<Integer> references;
    // Lista algorytmów zastępowania stron do symulacji
    private List<Algorithm> algorithms;

    public Simulation(int pagesCount, int framesCount, int length) {
        this.pagesCount = pagesCount;
        this.framesCount = framesCount;
        this.length = length;
        this.pages = new ArrayList<>();
        for (int i = 0; i < pagesCount; i++) {
            pages.add(new Page(i));
        }
        this.references = generateReferences(); // generujemy losowy ciąg odwołań do stron
        this.algorithms = new ArrayList<>();
        algorithms.add(new FIFO(framesCount));
        algorithms.add(new OPT(framesCount));
        algorithms.add(new LRU(framesCount));
        algorithms.add(new aLRU(framesCount));
        algorithms.add(new RAND(framesCount));
    }

    // Metoda generująca losowy ciąg odwołań do stron z uwzględnieniem zasady lokalności odwołań
    private List<Integer> generateReferences() {
        List<Integer> references = new ArrayList<>();
        Random random = new Random();
        int current = random.nextInt(pagesCount); // losujemy pierwszą stronę do odwołania
        references.add(current); // dodajemy ją do listy odwołań
        for (int i = 1; i < length; i++) {
            int next = random.nextInt(pagesCount); // losujemy następną stronę do odwołania
            if (random.nextDouble() < 0.7) { // z prawdopodobieństwem 0.7
                next = (current + random.nextInt(3) - 1 + pagesCount) % pagesCount; // wybieramy stronę sąsiadującą z obecną (poprzednią lub następną)
            }
            references.add(next); // dodajemy ją do listy odwołań
            current = next; // aktualizujemy obecną stronę
        }
        return references;
    }

    public List<Integer> getReferences() {
        return references;
    }

    public void setReferences(List<Integer> references) {
        this.references = references;
    }

    // Metoda wyświetlająca ciąg odwołań do stron
    private void printReferences() {
        System.out.print("Ciąg odwołań do stron: ");
        for (int reference : references) {
            System.out.print(reference + " ");
        }
        System.out.println();
    }

    // Metoda wyznaczająca czasy najbliższych odwołań do stron w pamięci fizycznej dla algorytmu OPT
    private void setNextAccessTimes(int time) {
        for (Page page : pages) {
            page.setNextAccess(Integer.MAX_VALUE); // ustawiamy czas najbliższego odwołania na maksymalną wartość dla każdej strony
        }
        for (int i = time + 1; i < length; i++) { // dla każdego następnego odwołania w ciągu
            Page page = pages.get(references.get(i)); // pobieramy stronę z listy stron
            if (page.getNextAccess() == Integer.MAX_VALUE) { // jeśli czas najbliższego odwołania do tej strony nie został jeszcze ustalony
                page.setNextAccess(i); // ustawiamy go na indeks tego odwołania w ciągu
            }
        }
    }

    // Metoda przeprowadzająca symulację algorytmów zastępowania stron
    public void run() {
        printReferences();
        for (int i = 0; i < length; i++) {
            Page page = pages.get(references.get(i)); // pobieramy stronę z listy stron
            setNextAccessTimes(i); // ustawiamy czasy najbliższych odwołań
            for (Algorithm algorithm : algorithms) {
                algorithm.replacePage(page, i); // wykonujemy algorytm zastępowania stron dla bieżącej strony i czasu
            }
        }
        for (Algorithm algorithm : algorithms) {
            System.out.println(algorithm.getName() + ": liczba błędów strony = " + algorithm.getFaults()); // wypisujemy jego nazwę i liczbę błędów strony
        }
    }
}
