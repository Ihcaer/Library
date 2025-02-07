import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Readers {
    Map<Integer, Reader> readerMap = new HashMap<>();

    public Readers() {
        Reader reader1 = new Reader(1, "John", "Doe");
        readerMap.put(reader1.getId(), reader1);
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your id.");

            boolean isLogged = false;
            do {
                if (scanner.hasNextInt()) {
                    int currentReaderId = scanner.nextInt();
                    Reader currentReader = readerMap.getOrDefault(currentReaderId, null);

                    if (currentReader != null) {
                        isLogged = true;
                        System.out.println("\nLogged as: " + currentReader.getName() + " " + currentReader.getLastname() + ".");
                    } else {
                        System.out.println("Reader with id " + currentReaderId + " not found. Please enter correct id.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter correct id.");
                    scanner.next();
                }
            } while (!isLogged);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
