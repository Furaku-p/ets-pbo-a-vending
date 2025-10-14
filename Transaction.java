import java.util.Scanner;

public class Transaction {
    // Sizes: 1=Small (+0), 2=Medium (+3000), 3=Large (+6000)
    public static void start(Scanner scanner, int coffeeChoice) {
        System.out.println();
        System.out.println("Anda memilih: " + CoffeeList.getName(coffeeChoice));
        if (!CoffeeList.isAvailable(coffeeChoice)) {
            System.out.println("Maaf, stock kopi ini habis. Silakan pilih yang lain.");
            return;
        }

        // pilih ukuran
        System.out.println("Pilih ukuran gelas:");
        printSize();
        System.out.print("> ");
        int sizeChoice;
        try {
            sizeChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Transaksi dibatalkan.");
            return;
        }
        int sizeExtra = sizePrice(sizeChoice);
        if (sizeExtra < 0) {
            System.out.println("Pilihan ukuran tidak valid. Transaksi dibatalkan.");
            return;
        }

        // pilih addons
        System.out.println("Apakah Anda ingin menambahkan add-ons?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.print("> ");
        int wantAddons;
        try {
            wantAddons = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Transaksi dibatalkan.");
            return;
        }

        int addonsPrice = 0;
        String addonsDesc = "Tidak ada";
        if (wantAddons == 1) {
            printAddons();
            System.out.print("> ");
            int addonsChoice;
            try {
                addonsChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Menganggap tanpa add-ons.");
                addonsChoice = -1;
            }
            switch (addonsChoice) {
                case 1:
                    addonsPrice = 3000;
                    addonsDesc = "Extra Sugar";
                    break;
                case 2:
                    addonsPrice = 6000;
                    addonsDesc = "Double Extra Sugar";
                    break;
                case 3:
                    addonsPrice = 5000;
                    addonsDesc = "Extra Milk";
                    break;
                default:
                    addonsPrice = 0;
                    addonsDesc = "Tidak ada";
                    break;
            }
        } else if (wantAddons != 2) {
            System.out.println("Pilihan add-ons tidak valid. Lanjut tanpa add-ons.");
        }

        int basePrice = CoffeeList.getPrice(coffeeChoice);
        int total = basePrice + sizeExtra + addonsPrice;
        System.out.println();
        System.out.println("Pesanan:");
        System.out.println("- Kopi: " + CoffeeList.getName(coffeeChoice));
        System.out.println("- Ukuran: " + sizeName(sizeChoice) + " (+" + sizeExtra + ")");
        System.out.println("- Add-ons: " + addonsDesc + " (+" + addonsPrice + ")");
        System.out.println("- Total: Rp " + total);

        // lakukan pembayaran
        Payment.transaksi(scanner, total, coffeeChoice, sizeChoice, addonsDesc);
    }

    private static int sizePrice(int sizeChoice) {
        switch (sizeChoice) {
            case 1: return 0;
            case 2: return 3000;
            case 3: return 6000;
            default: return -1;
        }
    }

    private static String sizeName(int sizeChoice) {
        switch (sizeChoice) {
            case 1: return "Small";
            case 2: return "Medium";
            case 3: return "Large";
            default: return "Unknown";
        }
    }

    public static void printSize() {
        System.out.println("1. Small  \t+Rp 0");
        System.out.println("2. Medium \t+Rp 3.000");
        System.out.println("3. Large  \t+Rp 6.000");
    }

    public static void printAddons() {
        System.out.println("1. Extra Sugar \t+Rp 3.000");
        System.out.println("2. Double Extra Sugar \t+Rp 6.000");
        System.out.println("3. Extra Milk \t+Rp 5.000");
    }
}
