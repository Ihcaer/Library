import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Library {
    List<Book> bookList = new ArrayList<>();

    public Library() {
        for (int i = 1; i <= 4; i++) {
            int randomYear = ThreadLocalRandom.current().nextInt(2000, 2026);
            bookList.add(new Book("title" + i, "author" + i, "isbn" + i, randomYear));
        }
    }

    public void chooseAction() {
        try {
            Scanner scanner = new Scanner(System.in);

            int selectedAction;
            do {
                System.out.println("\nChoose action (enter number): [1]Add book | [2]Search for book | [3]Borrow book | [4]Return book | [5]Show book list | [6]Exit");
                selectedAction = scanner.nextInt();

                doAction(selectedAction);
            } while (selectedAction != 6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doAction(int actionId) {
        switch (actionId) {
            case 1 -> addBook();
            case 2 -> searchBook();
            case 3 -> changeBookStatus("borrow");
            case 4 -> changeBookStatus("return");
            case 5 -> displayBookList();
            case 6 -> System.out.println("Exiting...");
            default -> System.out.println("Unknown number");
        }
    }

    private void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter title.");
        String title = scanner.nextLine();

        System.out.println("Enter author.");
        String author = scanner.nextLine();

        System.out.println("Enter ISBN.");
        String isbn = scanner.nextLine();

        System.out.println("Enter release year.");
        int releaseYear = scanner.nextInt();

        bookList.add(new Book(title, author, isbn, releaseYear));
    }

    private void searchBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search for book.");
        String searchValue = scanner.nextLine();

        System.out.println("Found books:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Title", "Author", "ISBN", "Release year", "Status");
        bookList.stream().filter(book -> book.getTitle().toLowerCase().contains(searchValue) || book.getAuthor().toLowerCase().contains(searchValue) || book.getIsbn().toLowerCase().contains(searchValue) || String.valueOf(book.getReleaseDate()).contains(searchValue)).forEach(Book::getBookInfo);
    }

    private void changeBookStatus(String action) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book title.");
        String bookTitle = scanner.nextLine();

        bookList.stream().filter(book -> book.getTitle().toLowerCase().equals(bookTitle)).findAny().ifPresentOrElse(book -> {
            if (action.equals("borrow")) {
                if (book.isBorrowed()) {
                    System.out.println("Book is currently unavailable.");
                } else {
                    book.changeStatus();
                    System.out.println("Thank you for borrowing the book.");
                }
            } else if (action.equals("return")) {
                if (book.isBorrowed()) {
                    book.changeStatus();
                    System.out.println("Thank you for returning the book.");
                } else {
                    System.out.println("Book isn't borrowed.");
                }
            }
        }, () -> System.out.println("Book not found."));
    }

    private void displayBookList() {
        System.out.println("\nBook list:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Title", "Author", "ISBN", "Release year", "Status");
        for (Book book : bookList) {
            book.getBookInfo();
        }
    }
}
