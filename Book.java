import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;
    private LocalDate issueDate;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            issueDate = LocalDate.now();
            System.out.println("Book issued successfully on " + issueDate);
        } else {
            System.out.println("Book is already issued.");
        }
    }

    public void returnBook() {
        if (isIssued) {
            LocalDate returnDate = LocalDate.now();
            isIssued = false;
            System.out.println("Book returned successfully on " + returnDate);
            calculateFine(returnDate);
        } else {
            System.out.println("Book is not issued.");
        }
    }

    private void calculateFine(LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(issueDate.plusDays(7), returnDate); // 7 days allowed for return
        if (daysLate > 0) {
            long fine = daysLate * 10; // Fine is 10 rupees per day
            System.out.println("You are late by " + daysLate + " days. Fine: " + fine + " rupees.");
        } else {
            System.out.println("Book returned on time. No fine.");
        }
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}