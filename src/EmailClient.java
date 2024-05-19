
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailClient {

    private BlockingQueue myEmailQueue;
    private EmailReceiver receiver;
    private EmailTxtNotification notifier;

    public EmailClient() {
        myEmailQueue = new BlockingQueue(5);
        receiver = new EmailReceiver(myEmailQueue);
        notifier = new EmailTxtNotification(myEmailQueue);
    }

    public static void main(String[] args) throws Exception {
        // Instantiate the EmailClient to initialize components
        EmailClient emailClient = new EmailClient();

        // Sending birthday message to persons having birthdays
        ArrayList<Sendable> birthdaywishRecipient = DBconnection.getTodayFriends();

        BdayMsg obj = new BdayMsg();
        for (Sendable s : birthdaywishRecipient) {
            if (s instanceof OfficeFriend) {
                OfficeFriend of = (OfficeFriend) s;
                obj.sendBdayMsg(of);
            } else {
                PersonalFriend pf = (PersonalFriend) s;
                obj.sendBdayMsg(pf);
            }
        }

        // Display welcome message
        System.out.println("*********************************************************");
        System.out.println("               Welcome to Email-Client App               ");
        System.out.println("*********************************************************");
        System.out.println("This application allows you to manage your email communications efficiently.");
        System.out.println("You can add new recipients, send emails, and see who has birthdays today.");
        System.out.println();
        System.out.println("Today's birthday wishes have been sent. There is much more to do...");
        System.out.println();

        // Display options for the user
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays today\n"
                    + "4 - Exit");
            int option = scanner.nextInt();

            // Process the selected option
            switch (option) {
                case 1:
                    System.out.println("You selected: Adding a new recipient");
                    emailClient.addNewRecipient(scanner);
                    break;
                case 2:
                    System.out.println("You selected: Sending an email");
                    emailClient.sendEmail(scanner);
                    break;
                case 3:
                    System.out.println("You selected: Printing out all the recipients who have birthdays today");
                    emailClient.printBirthdayRecipients(birthdaywishRecipient);

                    break;
                case 4:
                    System.out.println("Exiting the application. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid option. Please restart the application and select a valid option.");
                    break;
            }
        }
    }

    private void addNewRecipient(Scanner scanner) {
        System.out.println("Enter recipient details:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
       while(true){
           System.out.print("Client Type (office/friend/office-friend): ");
           String clientType = scanner.next();

           if (clientType.equalsIgnoreCase("office") || clientType.equalsIgnoreCase("office-friend")) {
               System.out.print("Designation: ");
               String designation = scanner.next();
               if (clientType.equalsIgnoreCase("office")) {
                   DBconnection.addOffice(name, clientType, email, designation);
               } else {
                   System.out.print("Birthdate (yyyy-mm-dd): ");
                   String birthdate = scanner.next();
                   DBconnection.addOfficeFriend(name, clientType, email, designation, Date.valueOf(birthdate));
               }
               break;
           } else if (clientType.equalsIgnoreCase("friend")) {
               System.out.print("Birthdate (yyyy-mm-dd): ");
               String birthdate = scanner.next();
               DBconnection.addFriend(name, clientType, email, Date.valueOf(birthdate));
               break;
           } else {
               System.out.println("Invalid client type. Please restart and enter a valid client type.");
           }
       }


    }

    private void printBirthdayRecipients(ArrayList<Sendable> birthdayRecipients) {
        System.out.println("Recipients with birthdays today:");
        for (Sendable recipient : birthdayRecipients) {
            System.out.println("Name: " + recipient.getName() + ", Email: " + recipient.getEmail());
        }
    }

    private void sendEmail(Scanner scanner) {

            System.out.println("Sending email...");
            System.out.print("Enter recipient email: ");
            String recipientEmail = scanner.next();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter email subject: ");
            String subject = scanner.nextLine();
            System.out.print("Enter email content: ");
            String content = scanner.nextLine();

            // Get today's date
            LocalDate today = LocalDate.now();
            String date = today.toString();

            // Create a Mail object
            Mail mail = new Mail(date, recipientEmail, subject, content);

            // Send the email
            mail.sendMail();

    }

}
