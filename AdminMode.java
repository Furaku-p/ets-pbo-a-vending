import java.util.Scanner;

public class AdminMode {
    // masuk ke admin: refill & lihat log
    // scanner: gunakan scanner yang sama dari main untuk konsistensi input
    public static void enter(Scanner scanner) {
        System.out.println();
        System.out.println("=== ADMIN MODE ===");
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Lihat stock kopi");
            System.out.println("2. Refill stock kopi");
            System.out.println("3. Lihat log transaksi");
            System.out.println("4. Kembali ke menu utama");
            System.out.print("> ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("=== Stock Saat Ini ===");
                    System.out.print(CoffeeList.stockReport());
                    break;
                case 2:
                    System.out.println("Pilih kopi yang ingin di-refill:");
                    CoffeeList.printCoffeeList();
                    System.out.print("> ");
                    int cIndex;
                    try {
                        cIndex = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid.");
                        break;
                    }
                    if (!CoffeeList.isValidCoffeeIndex(cIndex)) {
                        System.out.println("Pilihan tidak valid.");
                        break;
                    }
                    System.out.println("Masukkan jumlah refill (angka):");
                    System.out.print("> ");
                    int amount;
                    try {
                        amount = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid.");
                        break;
                    }
                    if (amount <= 0) {
                        System.out.println("Jumlah harus lebih dari 0.");
                        break;
                    }
                    CoffeeList.refillStock(cIndex, amount);
                    System.out.println("Refill berhasil. Stock sekarang:");
                    System.out.print(CoffeeList.stockReport());
                    Log.addLog(String.format("ADMIN | Refill: %s +%d", CoffeeList.getName(cIndex), amount));
                    break;
                case 3:
                    Log.showLogs();
                    break;
                case 4:
                    running = false;
                    System.out.println("Keluar dari Admin Mode.");
                    break;
                default:
                    System.out.println("Pilihan tidak dikenal.");
            }
        }
    }
}
