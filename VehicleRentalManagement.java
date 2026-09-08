import java.util.*;

public class VehicleRentalManagement {

    

    static class Vehicle {
        int id;
        String type, brand;
        int rate;
        boolean available;

        Vehicle(int id, String type, String brand, int rate) {
            this.id        = id;
            this.type      = type;
            this.brand     = brand;
            this.rate      = rate;
            this.available = true;
        }
    }

    static class Customer {
        String name, phone;

        Customer(String name, String phone) {
            this.name  = name;
            this.phone = phone;
        }
    }

    static class Rental {
        String name, phone;
        int vehicleId, days, cost;

        Rental(String name, String phone, int vehicleId, int days, int cost) {
            this.name      = name;
            this.phone     = phone;
            this.vehicleId = vehicleId;
            this.days      = days;
            this.cost      = cost;
        }
    }

    // ── State ────────────────────────────────────────────────────────────────

    static List<Vehicle>  vehicles  = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Rental>   rentals   = new ArrayList<>();
    static Scanner        sc        = new Scanner(System.in);

    // ── Entry point ──────────────────────────────────────────────────────────

    public static void main(String[] args) {
        // Seed data
        vehicles.add(new Vehicle(1, "Car",     "Toyota",  500));
        vehicles.add(new Vehicle(2, "Bike",    "Honda",   200));
        vehicles.add(new Vehicle(3, "Truck",   "Tata",   1000));
        vehicles.add(new Vehicle(4, "Car",     "Hyundai", 450));
        vehicles.add(new Vehicle(5, "Scooter", "Vespa",   100));
        vehicles.add(new Vehicle(6, "SUV",     "Mahindra",800));

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1"  -> showAvailableVehicles();
                case "2"  -> showAllVehicles();
                case "3"  -> addVehicle();
                case "4"  -> registerCustomer();
                case "5"  -> rentVehicle();
                case "6"  -> returnVehicle();
                case "7"  -> showRentals();
                case "8"  -> showCustomers();
                case "9"  -> removeVehicle();
                case "10" -> {
                    System.out.println("Thank you for using Vehicle Rental Management. Goodbye!");
                    return;
                }
                default   -> System.out.println("Please pick a number 1 to 10.");
            }
        }
    }

    // ── Menu ─────────────────────────────────────────────────────────────────

    static void showMenu() {
        System.out.println("\n== Vehicle Rental Management ==");
        System.out.println("1.  See free vehicles");
        System.out.println("2.  Show all vehicles");
        System.out.println("3.  Add a vehicle");
        System.out.println("4.  Register customer");
        System.out.println("5.  Rent vehicle");
        System.out.println("6.  Return vehicle");
        System.out.println("7.  See current rentals");
        System.out.println("8.  Show customer records");
        System.out.println("9.  Remove vehicle");
        System.out.println("10. Exit");
        System.out.print("Pick option (1-10): ");
    }

    // ── Feature methods ───────────────────────────────────────────────────────

    static void showAvailableVehicles() {
        System.out.println("\nVehicles ready for rent:");
        boolean anyFree = false;
        for (Vehicle v : vehicles) {
            if (v.available) {
                System.out.printf("ID: %d | %s, %s @ Rs.%d/day%n",
                        v.id, v.type, v.brand, v.rate);
                anyFree = true;
            }
        }
        if (!anyFree) System.out.println("No vehicles left right now.");
    }

    static void showAllVehicles() {
        System.out.println("\nAll vehicles:");
        for (Vehicle v : vehicles) {
            String status = v.available ? "Available" : "Rented";
            System.out.printf("ID: %d | %s, %s @ Rs.%d/day [%s]%n",
                    v.id, v.type, v.brand, v.rate, status);
        }
    }

    static void addVehicle() {
        int nextId = vehicles.isEmpty() ? 1 : vehicles.get(vehicles.size() - 1).id + 1;

        System.out.print("Type (Car/Bike etc): ");
        String type = sc.nextLine();

        System.out.print("Brand: ");
        String brand = sc.nextLine();

        System.out.print("Rate per day (Rs): ");
        int rate = Integer.parseInt(sc.nextLine());

        vehicles.add(new Vehicle(nextId, type, brand, rate));
        System.out.println("Added! Vehicle registered.");
    }

    static void registerCustomer() {
        System.out.print("Customer name: ");
        String name = sc.nextLine();

        System.out.print("Phone number: ");
        String phone = sc.nextLine();

        customers.add(new Customer(name, phone));
        System.out.println("Customer now in records.");
    }

    static void rentVehicle() {
        showAvailableVehicles();

        System.out.print("Choose vehicle by ID: ");
        int vid = Integer.parseInt(sc.nextLine());

        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.id == vid && v.available) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Sorry, unavailable or wrong ID.");
            return;
        }

        System.out.print("Enter renter's name: ");
        String name = sc.nextLine();

        System.out.print("Phone number: ");
        String phone = sc.nextLine();

        // Auto-register customer if not already present
        boolean alreadyExists = false;
        for (Customer c : customers) {
            if (c.name.equals(name) && c.phone.equals(phone)) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) customers.add(new Customer(name, phone));

        System.out.print("Rent for how many days? ");
        int days = Integer.parseInt(sc.nextLine());
        int cost = days * vehicle.rate;

        rentals.add(new Rental(name, phone, vid, days, cost));
        vehicle.available = false;

        System.out.println("Rental successful. You need to pay Rs. " + cost);
    }

    static void returnVehicle() {
        System.out.print("Vehicle ID to return: ");
        int vid = Integer.parseInt(sc.nextLine());

        System.out.print("Your name: ");
        String name = sc.nextLine();

        Rental rental = null;
        for (Rental r : rentals) {
            if (r.vehicleId == vid && r.name.equals(name)) {
                rental = r;
                break;
            }
        }

        if (rental == null) {
            System.out.println("Not found. Please check your ID and name.");
            return;
        }

        for (Vehicle v : vehicles) {
            if (v.id == vid) {
                v.available = true;
                break;
            }
        }

        rentals.remove(rental);
        System.out.println("Returned! Hope you are satisfied.");
    }

    static void showRentals() {
        System.out.println("\nRented vehicles right now:");
        if (rentals.isEmpty()) {
            System.out.println("No rentals active.");
        } else {
            for (Rental r : rentals) {
                System.out.printf("Renter: %s (%s) | Vehicle ID: %d | %d days | Rs.%d%n",
                        r.name, r.phone, r.vehicleId, r.days, r.cost);
            }
        }
    }

    static void showCustomers() {
        System.out.println("\nAll customers:");
        if (customers.isEmpty()) {
            System.out.println("No customers yet.");
        } else {
            for (Customer c : customers) {
                System.out.println(c.name + " | " + c.phone);
            }
        }
    }

    static void removeVehicle() {
        showAllVehicles();

        System.out.print("ID to remove: ");
        int vid = Integer.parseInt(sc.nextLine());

        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.id == vid) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("ID not found.");
            return;
        }

        if (!vehicle.available) {
            System.out.println("Can't remove a rented vehicle! Please wait till it's returned.");
            return;
        }

        vehicles.remove(vehicle);
        System.out.println("Vehicle removed from system.");
    }
}
