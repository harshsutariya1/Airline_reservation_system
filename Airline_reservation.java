import java.io.*;
import java.util.*;

class Flight {
     String flightNumber, sourceCity, destinationCity;
     // int totalSeats, availableSeats = totalSeats;
     double ticketPrice;

     Flight() {

     }

     Flight(String flightNumber, String sourceCity, String destinationCity, /* int totalSeats, int availableSeats, */
               Double ticketPrice) {
          this.flightNumber = flightNumber;
          this.sourceCity = sourceCity;
          this.destinationCity = destinationCity;
          // this.totalSeats = totalSeats;
          // this.availableSeats = availableSeats;
          this.ticketPrice = ticketPrice;
     }

     void insertIntoFile() throws Exception {
          FileWriter fw = new FileWriter("flights.txt", true);
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write("Flight_Number:" + this.flightNumber);
          bw.write("  Source_City:" + this.sourceCity);
          bw.write("  Destination_City:" + this.destinationCity);
          // bw.write(" Total_Seats:" + this.totalSeats);
          // bw.write(" Available_Seats:" + this.availableSeats);
          bw.write("  Ticket_Price:" + this.ticketPrice + "\n");
          bw.close();
          fw.close();
     }

     void viewAllFlights() throws Exception {
          try {
               File file = new File("flights.txt");
               Scanner sc = new Scanner(file);
               while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    if (data != null) {
                         System.out.println(data);
                    } else {
                         System.out.println("There are no flights!\nAdd flight data.");
                    }
               }
               sc.close();
          } catch (Exception e) {
               System.out.println("There are no flights!\nAdd flight data...");
          }
     }

     boolean flightAvailibility(String f_num) throws Exception {
          boolean con = false;
          try {
               File file = new File("flights.txt");
               Scanner sc = new Scanner(file);
               while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    if (data != null && data.contains(f_num)) {
                         con = true;
                    }
               }
               sc.close();
          } catch (Exception e) {
          }
          return con;
     }
}

class Passenger {
     String name, gender, contactNumber;
     int age;

     Passenger() {
     }

     Passenger(String name, int age, String gender, String contactNumber) {
          this.name = name;
          this.gender = gender;
          this.age = age;
          this.contactNumber = contactNumber;
     }

     void insertIntoFile() throws Exception {
          FileWriter fw = new FileWriter("passenger.txt", true);
          BufferedWriter bw = new BufferedWriter(fw);
          // bw.write("\n");
          bw.write("Passenger_Name:" + this.name);
          bw.write("  Gender:" + this.gender);
          bw.write("  Age:" + this.age);
          bw.write("  Contact_Number:" + this.contactNumber + "\n");
          bw.close();
          fw.close();
     }

     void viewAllPassengers() throws Exception {
          try {
               File file = new File("passenger.txt");
               Scanner sc = new Scanner(file);
               while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    if (data != null) {
                         System.out.println(data);
                    } else {
                         System.out.println("There are no passengers!,\nAdd passenger's data.");
                    }
               }
               sc.close();
          } catch (Exception e) {
               System.out.println("There are no passengers!,\nAdd passenger's data...");
          }
     }
}

class Bookings {
     String line;
     String flight_num;

     Bookings() {
     }

     Bookings(String line, String flight_num) {
          this.line = line;
          this.flight_num = flight_num;
     }

     void insertIntoFile() throws Exception {
          FileWriter fw = new FileWriter("bookings.txt", true);
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write("_________________________________________________\n");
          bw.write("Passenger Details:\n");
          bw.write(this.line);
          bw.write("\nFlight Details:\n");
          try (BufferedReader reader = new BufferedReader(new FileReader("flights.txt"))) {
               String line;
               while ((line = reader.readLine()) != null) {
                    if (line.contains(this.flight_num)) {
                         bw.write(line);
                         break;
                    }
               }
          } catch (IOException e) {
               e.printStackTrace();
          }
          bw.write("\n_________________________________________________\n\n\n");
          bw.close();

     }

     void viewBookedTickets() throws Exception {
          try {
               File file = new File("bookings.txt");
               Scanner sc = new Scanner(file);
               while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    if (data != null && data != "     ") {
                         System.out.println(data);
                    } else {
                         System.out.println("No Bookings...");
                    }
               }
               sc.close();
          } catch (Exception e) {
               System.out.println("No Bookings Found...");
          }
     }

}

class Airline_reservation {
     static Scanner sc = new Scanner(System.in);
     static Flight flight = new Flight();
     static Passenger passenger = new Passenger();
     static Bookings booking = new Bookings();

     public static void main(String[] args) throws Exception {
          int choice;
          do {
               System.out.println("\nAirline Reservation System");
               System.out.println("1. Add Flight");
               System.out.println("2. Add Passenger");
               System.out.println("3. Book a Ticket");
               System.out.println("4. Show All Flights");
               System.out.println("5. Show All Passengers");
               System.out.println("6. Show All Bookings");
               System.out.println("7. Exit");

               System.out.print("Enter your choice: ");
               choice = sc.nextInt();
               System.out.println("\n");

               switch (choice) {
                    case 1:
                         addFlight();
                         break;
                    case 2:
                         addPassenger();
                         break;
                    case 3:
                         bookTicket();
                         break;
                    case 4:
                         flight.viewAllFlights();
                         break;
                    case 5:
                         passenger.viewAllPassengers();
                         break;
                    case 6:
                         booking.viewBookedTickets();
                         break;
                    case 7:
                         System.out.println("Thank you for using the Airline Reservation System!\n");
                         break;
                    default:
                         System.out.println("Invalid choice. Please try again!");
                         break;
               }

          } while (choice != 7);
     }

     private static void addFlight() throws Exception {
          System.out.println("Add Flight:");
          System.out.print("Enter Flight Number: ");
          String flightNumber = sc.next();
          System.out.print("Enter Source City: ");
          String sourceCity = sc.next();
          System.out.print("Enter Destination City: ");
          String destinationCity = sc.next();
          // System.out.print("Enter Total Seats: ");
          // int totalSeats = sc.nextInt();
          // int availableSeats = totalSeats;
          System.out.print("Enter Ticket Price: ");
          double ticketPrice = sc.nextDouble();

          Flight flight = new Flight(flightNumber, sourceCity, destinationCity, /* totalSeats, availableSeats, */
                    ticketPrice);
          flight.insertIntoFile();

          System.out.println("\nFlight Added Successfully!");
     }

     private static void addPassenger() throws Exception {
          System.out.println("Add Passenger:");
          System.out.print("Enter Passenger Name: ");
          String n = sc.nextLine();
          String name = sc.nextLine();
          System.out.print("Enter Age: ");
          int age = sc.nextInt();
          System.out.print("Enter Gender (M/F): ");
          String gender = sc.next();
          System.out.print("Enter Contact Number: ");
          String contact = sc.nextLine();
          String contactNumber = sc.nextLine();

          Passenger passenger = new Passenger(name, age, gender, contactNumber);
          passenger.insertIntoFile();

          System.out.println("\nPassenger Added Successfully!");
     }

     private static void bookTicket() throws Exception {
          System.out.println("Enter Your Name: ");
          String n = sc.nextLine();
          String name = sc.nextLine();
          try {
               BufferedReader reader = new BufferedReader(new FileReader("passenger.txt"));
               String line;
               boolean con = true;
               String f_num;
               while ((line = reader.readLine()) != null) {
                    if (line.contains(name)) {
                         System.out.println(line);
                         while (true) {
                              System.out.println("\nAvailable Flights:");
                              flight.viewAllFlights();
                              System.out.println("\nChoose Flight_Number:");
                              f_num = sc.nextLine();
                              if (flight.flightAvailibility(f_num)) {
                                   Bookings book = new Bookings(line, f_num);
                                   book.insertIntoFile();
                                   System.out.println("Ticket Booked!!!");
                                   break;
                              } else {
                                   System.out.println(f_num + " Flight Not Available...");
                              }
                         }

                         con = false;
                         break;
                    }
               }
               if (con) { // means user need to register.
                    System.out.println("Passenger not found!\nPlease Add passenger.");
               }
          } catch (IOException e) {
               System.out.println("Passenger not found!\nPlease Add passenger...");
          }

     }
}