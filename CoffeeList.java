import java.util.ArrayList;
import java.util.List;

public class CoffeeList {
    // Nama, harga dasar (int dalam rupiah), dan stok
    private static final List<String> NAMES = new ArrayList<>();
    private static final List<Integer> PRICES = new ArrayList<>();
    private static final List<Integer> STOCKS = new ArrayList<>();

    static {
        // Inisialisasi contoh menu: nama, harga, stok awal
        NAMES.add("Americano");
        PRICES.add(15000);
        STOCKS.add(5);

        NAMES.add("Latte");
        PRICES.add(17000);
        STOCKS.add(5);

        NAMES.add("Cappuccino");
        PRICES.add(18000);
        STOCKS.add(5);

        NAMES.add("Espresso");
        PRICES.add(12000);
        STOCKS.add(5);
    }

    public static void printCoffeeList() {
        for (int i = 0; i < NAMES.size(); i++) {
            System.out.printf("%d. %s\tRp %d\t(Stock: %d)%n", i + 1, NAMES.get(i), PRICES.get(i), STOCKS.get(i));
        }
    }

    public static boolean isValidCoffeeIndex(int index) {
        return index >= 1 && index <= NAMES.size();
    }

    public static String getName(int index) {
        return NAMES.get(index - 1);
    }

    public static int getPrice(int index) {
        return PRICES.get(index - 1);
    }

    public static int getStock(int index) {
        return STOCKS.get(index - 1);
    }

    public static boolean isAvailable(int index) {
        return getStock(index) > 0;
    }

    public static void decrementStock(int index) {
        int current = getStock(index);
        if (current > 0) {
            STOCKS.set(index - 1, current - 1);
        }
    }

    public static void refillStock(int index, int amount) {
        if (amount <= 0) return;
        int current = getStock(index);
        STOCKS.set(index - 1, current + amount);
    }

    public static String stockReport() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NAMES.size(); i++) {
            sb.append(String.format("%d. %s - stock: %d%n", i + 1, NAMES.get(i), STOCKS.get(i)));
        }
        return sb.toString();
    }

    public static int menuSize() {
        return NAMES.size();
    }
}
