# Email Client

Email Client is a Java-based application that allows users to send and receive emails using the javax.mail API. This application has the following features:

- Send emails automatically to recipients on their birthdays.
- Send emails to specific recipients with a subject and content.
- Receive emails and read them through the application.

## Features

### Sending Birthday Emails

The application automatically sends birthday emails to recipients based on their birthdate. It retrieves the list of recipients from a database and sends them personalized birthday messages.

### Sending Custom Emails

Users can compose and send custom emails to specific recipients. They can specify the subject, content, and recipient email address.

### Receiving and Reading Emails

The application can receive emails  using the IMAP protocol. Maintains a log of received emails in a local file.


## Implementation Details

### MySQL Database

The Email Client application utilizes a MySQL database to store recipient information and birthday dates. It interacts with the database using JDBC (Java Database Connectivity) to perform CRUD (Create, Read, Update, Delete) operations on recipient records.

### Object-Oriented Programming (OOP) Concept

The Email Client application is designed using object-oriented principles. It utilizes classes and objects to represent email messages, recipients, and other entities.

### Multi-Threading

Multi-threading is employed in the application to handle concurrent tasks efficiently. Threads are used for sending and receiving emails asynchronously, ensuring smooth operation without blocking the main thread.

