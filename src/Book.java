public class Book {
    private String title;
    private String author;
    private String isbn;
    private int releaseDate;
    private boolean isBorrowed = false;

    public Book(String title, String author, String isbn, int releaseDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
    }

    public void changeStatus() {
        isBorrowed = !isBorrowed;
    }

    public void getBookInfo() {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", title, author, isbn, releaseDate, (isBorrowed ? "Borrowed" : "Available"));
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn(){
        return isbn;
    }

    public int getReleaseDate(){
        return releaseDate;
    }

    public boolean isBorrowed(){
        return isBorrowed;
    }
}
