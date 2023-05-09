import java.util.*;
public class Page {
    // Numer strony
    private int number;
    // Czas ostatniego odwołania do strony
    private int lastAccess;
    // Czas najbliższego odwołania do strony
    private int nextAccess;

    public Page(int number) {
        this.number = number;
        this.lastAccess = -1;
        this.nextAccess = -1;
    }

    // Metoda zwracająca numer strony
    public int getNumber() {
        return number;
    }

    // Metoda zwracająca czas ostatniego odwołania do strony
    public int getLastAccess() {
        return lastAccess;
    }

    // Metoda zwracająca czas najbliższego odwołania do strony
    public int getNextAccess() {
        return nextAccess;
    }

    // Metoda ustawiająca czas ostatniego odwołania do strony
    public void setLastAccess(int lastAccess) {
        this.lastAccess = lastAccess;
    }

    // Metoda ustawiająca czas najbliższego odwołania do strony
    public void setNextAccess(int nextAccess) {
        this.nextAccess = nextAccess;
    }
}

