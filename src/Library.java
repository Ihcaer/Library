import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    List<Book> bookList = new ArrayList<>();

    public Library() {
        bookList.add(new Book("title1", "author1", "isbn1", 2025));
        bookList.add(new Book("title2", "author2", "isbn2", 2025));
        bookList.add(new Book("title3", "author3", "isbn3", 2025));
        bookList.add(new Book("title4", "author4", "isbn4", 2025));
    }

    public void doAction(int actionId) {
        switch (actionId) {
            case 1 -> addBook();
            case 2 -> searchBook();
            case 3 -> borrowBook();
            case 4 -> returnBook();
            case 5 -> displayBookList();
            case 6 -> System.out.println("Exited");
            default -> System.out.println("Unknown number");
        }
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter title");
        String title = scanner.nextLine();

        System.out.println("Enter author");
        String author = scanner.nextLine();

        System.out.println("Enter ISBN");
        String isbn = scanner.nextLine();

        System.out.println("Enter release year");
        int releaseYear = scanner.nextInt();

        bookList.add(new Book(title, author, isbn, releaseYear));
    }

    private void searchBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search for book");
        String searchValue = scanner.nextLine();

        System.out.println("Found books:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Title", "Author", "ISBN", "Release year", "Status");
        bookList.stream().filter(book -> book.getTitle().toLowerCase().equals(searchValue) || book.getAuthor().toLowerCase().equals(searchValue) || book.getIsbn().toLowerCase().equals(searchValue) || String.valueOf(book.getReleaseDate()).equals(searchValue)).forEach(Book::getBookInfo);
    }

    private void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book title");
        String borrowingBookTitle = scanner.nextLine();

        bookList.stream().filter(book -> book.getTitle().toLowerCase().equals(borrowingBookTitle)).findAny().ifPresentOrElse(book -> {
            if (book.isBorrowed()) {
                System.out.println("Book is already borrowed");
            } else {
                book.changeStatus();
                System.out.println("Thank you for borrowing the book");
            }
        }, () -> System.out.println("Book not found"));
    }

    private void returnBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter book title");
        String returningBookTitle = scanner.nextLine();

        bookList.stream().filter(book -> book.getTitle().toLowerCase().equals(returningBookTitle)).findAny().ifPresentOrElse(book -> {
            if (book.isBorrowed()) {
                book.changeStatus();
                System.out.println("Thank you for returning the book");
            } else {
                System.out.println("Book isn't borrowed");
            }
        }, () -> System.out.println("Book not found"));
    }

    private void displayBookList() {
        System.out.println("\nBook list:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Title", "Author", "ISBN", "Release year", "Status");
        for (Book book : bookList) {
            book.getBookInfo();
        }
    }
}
