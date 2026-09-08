# Vehicle Rental Management System

A console-based Vehicle Rental Management System written in Java. It lets staff manage a fleet of vehicles, register customers, handle rentals, and process returns — all from an interactive terminal menu.

---

## Features

- View available or all vehicles
- Add and remove vehicles from the fleet
- Register customers
- Rent a vehicle to a customer (with automatic cost calculation)
- Return a rented vehicle
- View active rentals and customer records

---

## Project Structure

```
VehicleRentalManagement.java   # Single-file application
README.md
```

All logic lives in one file with three inner static classes:

| Class      | Purpose                                      |
|------------|----------------------------------------------|
| `Vehicle`  | Stores vehicle details and availability flag |
| `Customer` | Stores customer name and phone number        |
| `Rental`   | Stores an active rental record               |

---

## Prerequisites

- Java 14 or higher (uses switch expressions with arrow cases)
- JDK installed and `javac` available on your PATH

---

## Getting Started

### 1. Clone or download

```bash
git clone https://github.com/your-username/vehicle-rental-management.git
cd vehicle-rental-management
```

### 2. Compile

```bash
javac VehicleRentalManagement.java
```

### 3. Run

```bash
java VehicleRentalManagement
```

---

## Usage

Once running, you'll see the main menu:

```
== Vehicle Rental Management ==
1.  See free vehicles
2.  Show all vehicles
3.  Add a vehicle
4.  Register customer
5.  Rent vehicle
6.  Return vehicle
7.  See current rentals
8.  Show customer records
9.  Remove vehicle
10. Exit
Pick option (1-10):
```

Enter the number of the action you want and follow the prompts.

### Example — Renting a Vehicle

```
Pick option (1-10): 5

Vehicles ready for rent:
ID: 1 | Car, Toyota @ Rs.500/day
ID: 2 | Bike, Honda @ Rs.200/day
...

Choose vehicle by ID: 1
Enter renter's name: Ravi Kumar
Phone number: 9876543210
Rent for how many days? 3
Rental successful. You need to pay Rs. 1500
```

### Example — Returning a Vehicle

```
Pick option (1-10): 6
Vehicle ID to return: 1
Your name: Ravi Kumar
Returned! Hope you are satisfied.
```

---

## Pre-loaded Vehicle Data

The system starts with these six vehicles:

| ID | Type    | Brand    | Rate (Rs/day) |
|----|---------|----------|---------------|
| 1  | Car     | Toyota   | 500           |
| 2  | Bike    | Honda    | 200           |
| 3  | Truck   | Tata     | 1000          |
| 4  | Car     | Hyundai  | 450           |
| 5  | Scooter | Vespa    | 100           |
| 6  | SUV     | Mahindra | 800           |

---

## Notes

- Data is **in-memory only** — all records are lost when the program exits. Add file/database persistence for production use.
- Customers are auto-registered when they rent a vehicle if not already in the system.
- A vehicle currently on rent cannot be removed until it is returned.
- Rental cost is calculated as: `days × rate per day`.

---

## Future Improvements

- Persist data to a file or database (SQLite, MySQL, etc.)
- Add date-based rental tracking and overdue detection
- Support searching vehicles by type or brand
- Build a GUI using Java Swing or JavaFX
- Add input validation and exception handling

---

## License

This project is open source and available under the [MIT License](LICENSE).
