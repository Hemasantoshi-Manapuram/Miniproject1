import java.util.Scanner;

// Base class representing a flight
class Flight {
    private String source;
    private String destination;
    private String date;

    public Flight(String source, String destination, String date) {
        this.source = source;
        this.destination = destination;
        this.date = date;
    }

    // Getters
    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public void displayFlightDetails() {
        System.out.println("Flight Details:");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Date: " + date);
    }
}

// Economy class extending Flight
class EconomyClass extends Flight {
    private double cost;

    public EconomyClass(String source, String destination, String date) {
        super(source, destination, date);
        this.cost = 1000.0; // Base cost for Economy
    }

    public void displayEconomyFeatures() {
        System.out.println("Economy Class Features: Standard seating, in-flight entertainment.");
        System.out.println("Cost: ₹" + cost);
    }

    public double getCost() {
        return cost;
    }
}

// Premium class extending Flight
class PremiumClass extends Flight {
    private double cost;

    public PremiumClass(String source, String destination, String date) {
        super(source, destination, date);
        this.cost = 2000.0; // Base cost for Premium
    }

    public void displayPremiumFeatures() {
        System.out.println("Premium Class Features: Extra legroom, priority boarding, exclusive meals.");
        System.out.println("Cost: ₹" + cost);
    }

    public double getCost() {
        return cost;
    }
}

// Business class extending Flight
class BusinessClass extends Flight {
    private double cost;

    public BusinessClass(String source, String destination, String date) {
        super(source, destination, date);
        this.cost = 3000.0; // Base cost for Business
    }

    public void displayBusinessFeatures() {
        System.out.println("Business Class Features: Spacious seating, premium meals, lounge access.");
        System.out.println("Cost: ₹" + cost);
    }

    public double getCost() {
        return cost;
    }
}

// Main class to handle user interaction
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Available Flights:");
        Flight flight1 = new Flight("Hyd", "Vizag", "7/12/2024");
        Flight flight2 = new Flight("Bengaluru", "Hyd", "8/12/2024");

        flight1.displayFlightDetails();
        flight2.displayFlightDetails();

        System.out.println("\nChoose a class:");
        System.out.println("Enter 1 to choose the Economy class (₹1000)");
        System.out.println("Enter 2 to choose the Premium class (₹2000)");
        System.out.println("Enter 3 to choose the Business class (₹3000)");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.println("Enter source:");
        String source = sc.nextLine();
        System.out.println("Enter destination:");
        String destination = sc.nextLine();

        switch (choice) {
            case 1:
                if (isFlightAvailable(flight1, source, destination) || isFlightAvailable(flight2, source, destination)) {
                    EconomyClass economyFlight = new EconomyClass(source, destination, flight1.getDate());
                    economyFlight.displayEconomyFeatures();
                    offerUpgrade(economyFlight.getCost(), "Economy", source, destination, flight1.getDate());
                } else {
                    System.out.println("No Economy class flights available for the entered route.");
                }
                break;
            case 2:
                if (isFlightAvailable(flight1, source, destination) || isFlightAvailable(flight2, source, destination)) {
                    PremiumClass premiumFlight = new PremiumClass(source, destination, flight2.getDate());
                    premiumFlight.displayPremiumFeatures();
                } else {
                    System.out.println("No Premium class flights available for the entered route.");
                }
                break;
            case 3:
                if (isFlightAvailable(flight1, source, destination) || isFlightAvailable(flight2, source, destination)) {
                    BusinessClass businessFlight = new BusinessClass(source, destination, flight2.getDate());
                    businessFlight.displayBusinessFeatures();
                } else {
                    System.out.println("No Business class flights available for the entered route.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Helper method to check flight availability
    public static boolean isFlightAvailable(Flight flight, String source, String destination) {
        return flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination);
    }

    // Offer upgrade option
    public static void offerUpgrade(double currentCost, String currentClass, String source, String destination, String date) {
        System.out.println("\nDo you want to upgrade your fare? (yes/no)");
        String upgradeChoice = sc.nextLine();

        if (upgradeChoice.equalsIgnoreCase("yes")) {
            System.out.println("Choose an upgrade:");
            if (currentClass.equals("Economy")) {
                System.out.println("1 - Premium (Additional ₹1000)");
                System.out.println("2 - Business (Additional ₹2000)");
            } else {
                System.out.println("1 - Business (Additional ₹1000)");
            }

            int upgradeOption = sc.nextInt();
            sc.nextLine(); // Consume newline character

            if (currentClass.equals("Economy") && upgradeOption == 1) {
                PremiumClass premiumFlight = new PremiumClass(source, destination, date);
                premiumFlight.displayPremiumFeatures();
                System.out.println("Total Cost: ₹" + (currentCost + 1000));
            } else if (currentClass.equals("Economy") && upgradeOption == 2 || currentClass.equals("Premium") && upgradeOption == 1) {
                BusinessClass businessFlight = new BusinessClass(source, destination, date);
                businessFlight.displayBusinessFeatures();
                System.out.println("Total Cost: ₹" + (currentCost + 2000));
            } else {
                System.out.println("Invalid upgrade option.");
            }
        }
    }
}
