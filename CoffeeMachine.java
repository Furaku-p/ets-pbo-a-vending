import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Selamat Datang di Mesin Kopi ITS ===");

        while (true) {
            System.out.println();
            System.out.println("Silakan pilih jenis kopi (ketik angka).");
            CoffeeList.printCoffeeList();
            System.out.println("Masukkan 0 untuk keluar.");
            System.out.print("> ");
            int input;
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
                continue;
            }

            if (input == 0) {
                System.out.println("Terima kasih. Sampai jumpa!");
                break;
            }

            // Admin mode trigger: jika user mengetik 123 masuk admin mode
            if (input == 123) {
                AdminMode.enter(scanner);
                continue;
            }

            if (!CoffeeList.isValidCoffeeIndex(input)) {
                System.out.println("Pilihan tidak ada. Silakan coba lagi.");
                continue;
            }

            // jalankan transaksi untuk pilihan kopi (index sesuai tampilan: 1..n)
            Transaction.start(scanner, input);
        }

        scanner.close();
    }
}
