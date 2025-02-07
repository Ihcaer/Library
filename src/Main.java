public class Main {
    public static void main(String[] args) {
        Readers readers = new Readers();
        Library library = new Library();

        readers.login();
        library.chooseAction();
    }
}