import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = Library.loadData();  // Load data at the start
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show All Books");
            System.out.println("6. Show All Members");
            System.out.println("7. Check Number of Issued Books");
            System.out.println("8. Check Total Fine");
            System.out.println("9. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    library.addMember(new Member(memberId, name));
                    break;

                case 3:
                    System.out.print("Enter Member ID to issue book: ");
                    int memIdToIssue = scanner.nextInt();
                    System.out.print("Enter Book ID to issue: ");
                    int bookIdToIssue = scanner.nextInt();
                    Member memberToIssue = library.getMemberById(memIdToIssue);
                    Book bookToIssue = library.getBookById(bookIdToIssue);
                    if (memberToIssue != null && bookToIssue != null) {
                        memberToIssue.issueBook(bookToIssue);
                    } else {
                        System.out.println("Member or Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Member ID to return book: ");
                    int memIdToReturn = scanner.nextInt();
                    System.out.print("Enter Book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    Member memberToReturn = library.getMemberById(memIdToReturn);
                    Book bookToReturn = library.getBookById(bookIdToReturn);
                    if (memberToReturn != null && bookToReturn != null) {
                        memberToReturn.returnBook(bookToReturn);
                    } else {
                        System.out.println("Member or Book not found.");
                    }
                    break;

                case 5:
                    library.showAllBooks();
                    break;

                case 6:
                    library.showAllMembers();
                    break;

                case 7:
                    System.out.print("Enter Member ID to check issued books: ");
                    int memberIdToCheck = scanner.nextInt();
                    Member memberToCheck = library.getMemberById(memberIdToCheck);
                    if (memberToCheck != null) {
                        System.out.println("Number of books issued: " + memberToCheck.getNumberOfIssuedBooks());
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 8:
                    System.out.print("Enter Member ID to check total fine: ");
                    int memberIdToFineCheck = scanner.nextInt();
                    Member memberToFineCheck = library.getMemberById(memberIdToFineCheck);
                    if (memberToFineCheck != null) {
                        long totalFine = memberToFineCheck.calculateTotalFine();
                        System.out.println("Total fine for " + memberToFineCheck.getName() + ": " + totalFine + " rupees.");
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 9:
                    library.saveData();
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 9);

        scanner.close();
    }
}
