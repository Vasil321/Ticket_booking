package org.example;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    //Counter for the files/Ticket counter
    static int count = 0;
    //Sales counter
    static int total = 0;
    public static void main(String[] args) {

        //Creates a directory for the text files
        File directory = new File("./files/tickets");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // The Seating plan that is going to be updated
        String[][] seats = {
                {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
                {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
                {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"},
                {"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"}
        };

        // Makes inputs possible
        Scanner scan = new Scanner(System.in);

        int price = 0;
        //Decides the option of the menu
        int option;
        //Row
        int row = 0;
        // Seat
        int column = 0;
        //switches row to a sting
        String rows = "A";

        //Looping menu
        do {
            System.out.println("     ");
            System.out.println("*********************************");
            System.out.println("**             Menu            **");
            System.out.println("*********************************");
            System.out.println("    1) Buy a seat");
            System.out.println("    2) Cancel seat");
            System.out.println("    3) Find first available seat");
            System.out.println("    4) Show seating plan");
            System.out.println("    5) Print tickets information and total sales");
            System.out.println("    6) Search ticket");
            System.out.println("    0) Quit");
            System.out.println("*********************************");


            //Ask for option input


            // forces loop for error handling
            while (true) {
                try {
                    System.out.print("Select an option: ");
                    option = scan.nextInt();

                    //shows the seating plan
                    if (option == 4) {
                        seatingPlan(seats);

                    }

                    //Buys a seat
                    if (option == 1) {
                        buySeats(scan, seats, row, column, directory, rows, price);
                    }

                    //Finds the first available seat
                    if (option == 3) {
                        findFirstAvailableSeat(seats, scan, directory, price);
                    }

                    //Cancels seat
                    if (option == 2) {
                        cancelSeats(scan, seats, row, column, directory);
                    }

                    //Print all tickets
                    if (option == 5) {
                        ticketInfo(directory);
                    }

                    //Tickets search
                    if (option == 6) {
                        findTicket(scan, rows, column, directory);
                    }

                    if (option > 6){
                        System.out.println("Only numbers allowed(between 0 and 6)!");
                    }
                    break;
                //Error handling
                } catch (Exception e) {
                    System.out.println("Only numbers allowed(between 0 and 6)!");
                    scan.next();
                }
            }
        // Forces the end of the loop
        } while (option != 0);
    }

    //Method for printing seat plan
    private static void seatingPlan(String[][] seats) {
        //Loops through the array
        for (String[] seat : seats) {
            //Adds new line every integration.
            System.out.println(" ");
            for (String s : seat) {
                //Prints the Strings
                System.out.print(s + " ");
            }
        }
        //prints new line at the end of the loop
        System.out.println(" ");
    }


    //Methods for finding  the first available seat
    private static void findFirstAvailableSeat(String[][] seats, Scanner scan, File directory, int price) {
        //Helps to break the nested loops
        int i;
        int j = 0;
        outerloop:

        //forces nested loop
        for (i = 0; i < seats.length; i++) {
            for (j = 0; j < seats[i].length; j++) {
                //checks if seat on the current position is free
                if (seats[i][j].equals("O")) {
                    //Saves seat
                    seats[i][j] = "X";
                    //output that the seat a seat has been found
                    System.out.println("Seat has been found:");
                    //breaks both loops
                    break outerloop;

                } else {
                    //in case no seat is available
                    System.out.println("Not available seats!!");
                }
            }
        }
        String rows;

        rows = switch (i) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            default -> "";

        };


        if (j < 5) {
            price = 200;
        } else if (j > 5 && j < 10) {
            price = 150;
        } else {
            price = 180;
        }

        total += price;
        count += 1;

        System.out.println("Name: ");
        String name = scan.next();
        System.out.println("Surname: ");
        String surname = scan.next();
        System.out.println("Email: ");
        String email = scan.next();



        String fileName = rows.toLowerCase() + j + ".txt";


        try {
            FileWriter ticketWriter = new FileWriter(directory + "/" + fileName, true);
            BufferedWriter ticketss = new BufferedWriter(ticketWriter);
            ticketss.write("-----------------------" + "\n");
            ticketss.write("Ticket " + count + "\n");
            ticketss.write("Row: " + rows + "\n");
            ticketss.write("Seat: " + j + "\n");
            ticketss.write("Price: £" + price + "\n");
            ticketss.write("Name: " + name + "\n");
            ticketss.write("Surname: " + surname + "\n");
            ticketss.write("Email: " + email + "\n");
            ticketss.close();
        } catch (IOException a) {
            a.printStackTrace();

        }


    }

    private static void buySeats(Scanner scan, String[][] seats, int row, int column, File directory, String rows, int price) {
        do {
            System.out.print("Enter row(A - D): ");
            rows = scan.next();
            row = switch (rows) {
                case "A", "a" -> 0;
                case "B", "b" -> 1;
                case "C", "c" -> 2;
                case "D", "d" -> 3;
                default -> 5;
            };
        } while (row > 4);
        while (true) {
            try {
                System.out.print("Enter seat number: ");
                column = scan.nextInt();
                if (seats[row][column - 1].equals("X")) {
                    System.out.println("Seat already taken!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Only numbers allowed!");
                scan.nextLine();
            } catch (IndexOutOfBoundsException n) {
                if (row == 1 || row == 2) {
                    System.out.println("seats between 1 and 12");
                } else {
                    System.out.println("Seats between 1 and 14");
                }
                scan.nextLine();
            }
        }

        if (column < 5) {
            price = 200;
        } else if (column > 5 && column < 10) {
            price = 150;
        } else {
            price = 180;
        }

        total += price;
        count += 1;

        System.out.print("Name: ");
        String name = scan.next();
        System.out.print("Surname: ");
        String surname = scan.next();
        System.out.print("Email: ");
        String email = scan.next();

        seats[row][column - 1] = "X";

        Ticket ticket = new Ticket(rows, column, price);
        Person person = new Person(name, surname, email);
        ticket.setSeat(column);
        ticket.setRow(rows);
        ticket.setPrice(price);
        person.setEmail(email);
        person.setName(name);
        person.setSurname(surname);

        String fileName = rows.toLowerCase() + column + ".txt";


        try {
            FileWriter ticketWriter = new FileWriter(directory + "/" + fileName, true);
            BufferedWriter ticketss = new BufferedWriter(ticketWriter);
            ticketss.write("-----------------------" + "\n");
            ticketss.write("Ticket " + count + "\n");
            ticketss.write("Row: " + rows + "\n");
            ticketss.write("Seat: " + column + "\n");
            ticketss.write("Price: £" + price + "\n");
            ticketss.write("Name: " + name + "\n");
            ticketss.write("Surname: " + surname + "\n");
            ticketss.write("Email: " + email + "\n");
            ticketss.close();
        } catch (IOException a) {
            a.printStackTrace();

        }


    }

    private static void cancelSeats(Scanner scan, String[][] seats, int row, int column, File directory) {

        String rows;

        do {
            System.out.print("Enter row(A - D): ");
            rows = scan.next();
            row = switch (rows) {
                case "A", "a" -> 0;
                case "B", "b" -> 1;
                case "C", "c" -> 2;
                case "D", "d" -> 3;
                default -> 5;
            };

        } while (row > 4);



        outerloop:
        while (true) {


            try {
                // Error handling not to get stuck on forever loop
                for (int i = 0; i< seats[row].length; i++){
                    if (seats[row][i].equals("O")){
                        break outerloop;
                    }
                    else if (seats[row][i].equals("X")){
                        break;
                    }
                }
                System.out.print("Enter seat number to cancel: ");
                column = scan.nextInt();
                seats[row][column - 1] = "O";
                if (seats[row][column - 1].equals("O")) {
                    System.out.println("This  seat is not taken!");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Only numbers allowed!");
                scan.nextLine();
            } catch (IndexOutOfBoundsException n) {
                if (row == 1 || row == 2) {
                    System.out.println("seats between 1 and 12");
                } else {
                    System.out.println("Seats between 1 and 14");
                }
                scan.nextLine();
            }
        }

        System.out.println("No taken seats on this row!");


        //Deletes text file/Ticket
        File file = new File(directory + "/" + rows.toLowerCase() + column + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }

    // Print all tickets and total sales
    private static void ticketInfo(File directory) {
        //checks if directory is empty
        File[] dicfiles = directory.listFiles();
        if (dicfiles == null || dicfiles.length == 0) {
            System.out.println("There are no tickets!");
        } else {
            //loops through directory
            for (File file : dicfiles) {
                if (file.isFile()) {
                    try {
                        //Prints file line by line
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            // Process each line of the file
                            System.out.println(line); // Example: Print each line
                        }
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: File not found.");
                        e.printStackTrace();
                    }

                }

                System.out.println("-----------------------");
                System.out.println("Total Sales: " + total);


            }
        }


    }

    //Finds ticket by row and seat
    private static void findTicket(Scanner scan, String rows, int column, File directory) {
        //asks for row and seat number
        System.out.print("Enter row: ");
        rows = scan.next();
        System.out.print("Enter Seat number: ");
        column = scan.nextInt();

        //looks for the file by name
        File file = new File(directory + "/" + rows.toLowerCase() + column + ".txt");
        if (file.exists()) {
            try {
                //prints information
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // Process each line of the file
                    System.out.println(line); // Example: Print each line
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
                e.printStackTrace();
            }
        } else {
            //if file does not exist
            System.out.println("File does not exist.");
        }

    }
}