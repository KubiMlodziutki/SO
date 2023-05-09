import java.util.*;
// Klasa abstrakcyjna reprezentująca algorytm zastępowania stron
abstract class Algorithm {
    // Nazwa algorytmu
    protected String name;
    // Liczba ramek pamięci fizycznej
    protected int framesCount;
    // Lista ramek pamięci fizycznej
    protected List<Frame> frames;
    // Liczba błędów strony
    protected int faults;

    public Algorithm(String name, int framesCount) {
        this.name = name;
        this.framesCount = framesCount;
        this.frames = new ArrayList<>();
        for (int i = 0; i < framesCount; i++) {
            frames.add(new Frame(i));
        }
        this.faults = 0;
    }
    public String getName() {
        return name;
    }

    public int getFaults() {
        return faults;
    }

    // Metoda abstrakcyjna realizująca algorytm zastępowania stron
    public abstract void replacePage(Page page, int time);

    // Metoda pomocnicza sprawdzająca, czy strona jest już w pamięci fizycznej
    protected boolean isInMemory(Page page) {
        for (Frame frame : frames) {
            if (frame.getPage() != null && frame.getPage().getNumber() == page.getNumber()) {
                return true;
            }
        }
        return false;
    }

    // Metoda pomocnicza znajdująca wolną ramkę w pamięci fizycznej
    protected Frame findFreeFrame() {
        for (Frame frame : frames) {
            if (frame.getPage() == null) {
                return frame;
            }
        }
        return null;
    }
}
