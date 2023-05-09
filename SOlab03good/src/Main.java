import java.util.*;
public class Main {

    public static void main(String[] args) {

        int pagesCount = 1000; // liczba stron pamięci wirtualnej
        int framesCount1 = 3; // liczba ramek pamięci fizycznej
        int framesCount2 = 5; // liczba ramek pamięci fizycznej
        int framesCount3 = 10; // liczba ramek pamięci fizycznej
        int length = 1000; // długość ciągu odwołań do stron

        // Tworzymy obiekty klasy Simulation z podanymi parametrami
        Simulation simulation1 = new Simulation(pagesCount, framesCount1, length);

        Simulation simulation2 = new Simulation(pagesCount, framesCount2, length);
        simulation2.setReferences(simulation1.getReferences());

        Simulation simulation3 = new Simulation(pagesCount, framesCount3, length);
        simulation3.setReferences(simulation1.getReferences());

        // Uruchamiamy symulację algorytmów zastępowania stron
        System.out.println();
        System.out.println("Symulacja 1 dla ilości ramek równej " + framesCount1 + " i długości ciągu odwołań równego " + length);
        simulation1.run();
        System.out.println();
        System.out.println("Symulacja 2 dla ilości ramek równej " + framesCount2 + " i długości ciągu odwołań równego " + length);
        simulation2.run();
        System.out.println();
        System.out.println("Symulacja 3 dla ilości ramek równej " + framesCount3 + " i długości ciągu odwołań równego " + length);
        simulation3.run();
    }
}
