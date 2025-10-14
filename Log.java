import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final List<String> logs = new ArrayList<>();

    public static void addLog(String entry) {
        logs.add(entry);
    }

    public static void showLogs() {
        if (logs.isEmpty()) {
            System.out.println("Belum ada transaksi/log.");
            return;
        }
        System.out.println("=== Log Transaksi / Notifikasi ===");
        for (int i = 0; i < logs.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, logs.get(i));
        }
    }

    public static List<String> getAllLogs() {
        return new ArrayList<>(logs);
    }
}
