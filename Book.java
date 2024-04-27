import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;
    private String isbn;

    public Book(String title, String author, double price, String isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return title + " by " + author + " - $" + price;
    }
}

class Customer {
    private String name;
    private String email;
    private String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;
    private List<Book> books;
    private double totalAmount;

    public Order(Customer customer) {
        this.customer = customer;
        this.books = new ArrayList<>();
        this.totalAmount = 0;
    }

    // Add book to order
    public void addBook(Book book) {
        books.add(book);
        totalAmount += book.getPrice();
    }

    // Getters
    public List<Book> getBooks() {
        return books;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Generate invoice
    public void generateInvoice() {
        System.out.println("Invoice for: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Shipping Address: " + customer.getAddress());
        System.out.println("Books Ordered:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " ($" + book.getPrice() + ")");
        }
        System.out.println("Total Amount: $" + totalAmount);
    }
}

class Bookstore {
    private Map<String, Book> inventory;

    public Bookstore() {
        this.inventory = new HashMap<>();
    }

    // Add book to inventory
    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
    }

    // Browse books
    public void browseBooks() {
        System.out.println("Available Books:");
        for (Book book : inventory.values()) {
            System.out.println(book);
        }
    }

    // Get book by ISBN
    public Book getBook(String isbn) {
        return inventory.get(isbn);
    }
}

public class OnlineBookstoreApplication {
    public static void main(String[] args) {
        // Create bookstore and add books to inventory
        Bookstore bookstore = new Bookstore();
        bookstore.addBook(new Book("Java Programming", "John Doe", 29.99, "978-0134685991"));
        bookstore.addBook(new Book("Python Programming", "Jane Smith", 24.99, "978-0134853981"));
        // Add more books as needed

        Scanner scanner = new Scanner(System.in);

        // Prompt user to browse books
        System.out.println("Welcome to the Online Bookstore!");
        System.out.println("Browse Books:");
        bookstore.browseBooks();

        // Simulate customer placing an order
        Customer customer = new Customer("John Smith", "john@example.com", "123 Main St, Anytown");
        Order order = new Order(customer);
        while (true) {
            System.out.print("Enter ISBN of book to add to cart (or 'done' to complete order): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            Book book = bookstore.getBook(input);
            if (book != null) {
                order.addBook(book);
                System.out.println("Added " + book.getTitle() + " to cart.");
            } else {
                System.out.println("Book not found. Please enter a valid ISBN.");
            }
        }

        // Display invoice
        System.out.println("\nOrder Summary:");
        order.generateInvoice();

        scanner.close();
    }
}
