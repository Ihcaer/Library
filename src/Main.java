import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Reader> readersMap = new HashMap<>();

        Reader reader1 = new Reader(1, "John", "Doe");
        readersMap.put(reader1.getId(), reader1);

        Library library = new Library();

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your id");
            int currentReaderId = scanner.nextInt();
            scanner.nextLine();
            Reader currentReader = readersMap.get(currentReaderId);
// dodać walidację czy użytkownik istnieje
            System.out.println("\nLogged as: " + currentReader.getName() + " " + currentReader.getLastname());

            int selectedAction;
            do {
                System.out.println("\nChoose action (enter number): [1]Add book | [2]Search for book | [3]Borrow book | [4]Return book | [5]Show book list | [6]Exit");
                selectedAction = scanner.nextInt();

                library.doAction(selectedAction);
            } while (selectedAction != 6);

            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}