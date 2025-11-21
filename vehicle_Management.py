vehicles = [
    {'id': 1, 'type': 'Car', 'brand': 'Toyota', 'rate': 500, 'available': True},
    {'id': 2, 'type': 'Bike', 'brand': 'Honda', 'rate': 200, 'available': True},
    {'id': 3, 'type': 'Truck', 'brand': 'Tata', 'rate': 1000, 'available': True},
    {'id': 4, 'type': 'Car', 'brand': 'Hyundai', 'rate': 450, 'available': True},
    {'id': 5, 'type': 'Scooter', 'brand': 'Vespa', 'rate': 100, 'available': True},
    {'id': 6, 'type': 'SUV', 'brand': 'Mahindra', 'rate': 800, 'available': True}
]

customers = []
rentals = []

def show_menu():
    print("\n== Vehicle Rental Management ==")
    print("1. See free vehicles")
    print("2. Show all vehicles")
    print("3. Add a vehicle")
    print("4. Register customer")
    print("5. Rent vehicle")
    print("6. Return vehicle")
    print("7. See current rentals")
    print("8. Show customer records")
    print("9. Remove vehicle")
    print("10. Exit")

def show_available_vehicles():
    print("\nVehicles ready for rent:")
    any_free = False
    for v in vehicles:
        if v['available']:
            print(f"ID: {v['id']} | {v['type']}, {v['brand']} @ Rs.{v['rate']}/day")
            any_free = True
    if not any_free:
        print("No vehicles left right now.")

def show_all_vehicles():
    print("\nAll vehicles:")
    for v in vehicles:
        stat = "Available" if v['available'] else "Rented"
        print(f"ID: {v['id']} | {v['type']}, {v['brand']} @ Rs.{v['rate']}/day [{stat}]")

def add_vehicle():
    vid = vehicles[-1]['id'] + 1 if vehicles else 1
    vtype = input("Type (Car/Bike etc): ")
    brand = input("Brand: ")
    rate = int(input("Rate per day (Rs): "))
    vehicles.append({'id': vid, 'type': vtype, 'brand': brand, 'rate': rate, 'available': True})
    print("Added! Vehicle registered.")

def register_customer():
    name = input("Customer name: ")
    phone = input("Phone number: ")
    customers.append({'name': name, 'phone': phone})
    print("Customer now in records.")

def rent_vehicle():
    show_available_vehicles()
    vid = int(input("Choose vehicle by ID: "))
    
    vehicle = None
    for v in vehicles:
        if v['id'] == vid and v['available']:
            vehicle = v
            break

    if vehicle:
        name = input("Enter renter's name: ")
        phone = input("Phone number: ")

        already = False
        for c in customers:
            if c['name'] == name and c['phone'] == phone:
                already = True
        
        if not already:
            customers.append({'name': name, 'phone': phone})

        days = int(input("Rent for how many days? "))
        cost = days * vehicle['rate']

        rentals.append({'name': name, 'phone': phone, 'id': vid, 'days': days, 'cost': cost})
        vehicle['available'] = False

        print("Rental successful. You need to pay Rs.", cost)

    else:
        print("Sorry, unavailable or wrong ID.")

def return_vehicle():
    vid = int(input("Vehicle ID to return: "))
    name = input("Your name: ")
    
    rental = None
    for r in rentals:
        if r['id'] == vid and r['name'] == name:
            rental = r
            break

    if rental:
        for v in vehicles:
            if v['id'] == vid:
                v['available'] = True
        
        rentals.remove(rental)
        print("Returned! Hope you are satisfied.")
    else:
        print("Not found. Please check your ID and name.")

def show_rentals():
    print("\nRented vehicles right now:")
    if not rentals:
        print("No rentals active.")
    else:
        for r in rentals:
            print(f"Renter: {r['name']} ({r['phone']}) | Vehicle ID: {r['id']} | {r['days']} days | Rs.{r['cost']}")

def show_customers():
    print("\nAll customers:")
    if not customers:
        print("No customers yet.")
    else:
        for c in customers:
            print(f"{c['name']} | {c['phone']}")

def remove_vehicle():
    show_all_vehicles()
    vid = int(input("ID to remove: "))

    vehicle = None
    for v in vehicles:
        if v['id'] == vid:
            vehicle = v
            break

    if vehicle:
        if vehicle['available']:
            vehicles.remove(vehicle)
            print("Vehicle removed from system.")
        else:
            print("Can't remove a rented vehicle! Please wait till it's returned.")
    else:
        print("ID not found.")

while True:
    show_menu()
    choice = input("Pick option (1-10): ").strip()

    if choice == '1':
        show_available_vehicles()
    elif choice == '2':
        show_all_vehicles()
    elif choice == '3':
        add_vehicle()
    elif choice == '4':
        register_customer()
    elif choice == '5':
        rent_vehicle()
    elif choice == '6':
        return_vehicle()
    elif choice == '7':
        show_rentals()
    elif choice == '8':
        show_customers()
    elif choice == '9':
        remove_vehicle()
    elif choice == '10':
        print("Thank you for using Vehicle Rental Management. Goodbye!")
        break
    else:
        print("Please pick a number 1 to 10.")
