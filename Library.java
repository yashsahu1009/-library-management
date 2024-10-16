import java.io.*;
import java.util.ArrayList;

class Library implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added: " + member);
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Book not found
    }

    public Member getMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null; // Member not found
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library_data.ser"))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library loadData() {
        File file = new File("library_data.ser");
        if (!file.exists()) {
            System.out.println("No previous data found. Starting with a new library.");
            return new Library();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Library) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Library();
        }
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }
}