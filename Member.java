import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberId;
    private String name;
    private ArrayList<Book> issuedBooks;
    private static final int MAX_ISSUED_BOOKS = 3; // Max books a member can issue

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < MAX_ISSUED_BOOKS;
    }

    public void issueBook(Book book) {
        if (canIssueMoreBooks()) {
            issuedBooks.add(book);
            book.issueBook();
            System.out.println("Book issued to " + name);
        } else {
            System.out.println(name + " has already issued the maximum number of books.");
        }
    }

    public void returnBook(Book book) {
        if (issuedBooks.remove(book)) {
            book.returnBook();
        } else {
            System.out.println(name + " does not have this book issued.");
        }
    }

    public long calculateTotalFine() {
        long totalFine = 0;
        for (Book book : issuedBooks) {
            if (book.isIssued()) {
                long daysLate = ChronoUnit.DAYS.between(book.getIssueDate().plusDays(7), LocalDate.now());
                if (daysLate > 0) {
                    totalFine += daysLate * 10; // Fine is 10 rupees per day
                }
            }
        }
        return totalFine;
    }

    public int getNumberOfIssuedBooks() {
        return issuedBooks.size();
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Issued Books: " + issuedBooks.size();
    }
}