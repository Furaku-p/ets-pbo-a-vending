import java.time.LocalDateTime;
import java.util.Scanner;

public class Payment {
    // Melakukan simulasi transaksi
    // scanner: objek scanner yang sama dari main
    public static void transaksi(Scanner scanner, int totalHarga, int coffeeIndex, int sizeChoice, String addonsDesc) {
        // periksa stock sebelum minta uang
        if (!CoffeeList.isAvailable(coffeeIndex)) {
            System.out.println("Maaf, stok kopi sudah habis. Transaksi dibatalkan.");
            return;
        }

        System.out.println();
        System.out.println("Silakan masukkan nominal uang Anda (Rp):");
        System.out.print("> ");
        int uang;
        try {
            uang = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Nominal tidak valid. Transaksi dibatalkan.");
            return;
        }

        if (uang < totalHarga) {
            System.out.println("Uang Anda tidak mencukupi. Transaksi gagal.");
            Log.addLog(String.format("%s | %s | %s | Gagal: Kurang uang (Masuk: Rp %d, Butuh: Rp %d)",
                    LocalDateTime.now(), CoffeeList.getName(coffeeIndex), addonsDesc, uang, totalHarga));
            return;
        }

        // jika cukup dan stok masih ada, proses pembuatan kopi
        if (CoffeeList.isAvailable(coffeeIndex)) {
            System.out.println("Pembayaran diterima. Pesanan Anda sedang dibuat...");
            // simulasi proses pembuatan (bisa ditambah Thread.sleep jika mau)
            CoffeeList.decrementStock(coffeeIndex);
            int kembalian = uang - totalHarga;
            System.out.println("Pesanan selesai. Silakan ambil kopi Anda.");
            System.out.println("Kembalian: Rp " + kembalian);

            // simpan log sukses
            String logEntry = String.format("%s | %s | Size:%d | Add-ons:%s | Total:Rp%d | Bayar:Rp%d | Kembali:Rp%d",
                    LocalDateTime.now(), CoffeeList.getName(coffeeIndex), sizeChoice, addonsDesc, totalHarga, uang, kembalian);
            Log.addLog(logEntry);

            // cek apabila setelah decrement stock menjadi 0 -> pesan ke admin (log)
            if (!CoffeeList.isAvailable(coffeeIndex)) {
                Log.addLog(String.format("%s | NOTICE: %s stock Habis.", LocalDateTime.now(), CoffeeList.getName(coffeeIndex)));
            }
        } else {
            System.out.println("Maaf, stok habis saat pembayaran. Uang dikembalikan.");
            Log.addLog(String.format("%s | %s | Gagal: Stok habis saat pembayaran", LocalDateTime.now(), CoffeeList.getName(coffeeIndex)));
        }
    }
}
