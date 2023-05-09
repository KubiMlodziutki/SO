import java.util.*;
public class Frame {
    // Numer ramki
    private int number;
    // Strona przechowywana w ramce
    private Page page;

    // Konstruktor klasy Frame
    public Frame(int number) {
        this.number = number;
        this.page = null;
    }

    // Metoda zwracająca numer ramki
    public int getNumber() {
        return number;
    }

    // Metoda zwracająca stronę przechowywaną w ramce
    public Page getPage() {
        return page;
    }

    // Metoda ustawiająca stronę przechowywaną w ramce
    public void setPage(Page page) {
        this.page = page;
    }
}
