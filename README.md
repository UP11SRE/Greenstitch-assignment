--- Parking Lot System Readme ---

---> Introduction

This project is a simple parking lot management system written in Java. It allows users to manage a parking lot by issuing tickets to cars entering the lot, recording registration numbers and colors, allocating parking slots, and freeing up slots when cars leave. Additionally, it supports several commands to retrieve information such as registration numbers by color, slot numbers by registration number, and slot numbers by color.


---> Getting Started

To use the Parking Lot System, you have three options:

1. Compilation and Execution
   
Compile all Java files and then run the ParkingLotApp class.


javac *.java
java ParkingLotApp

2. Using JAR file

You can also run the provided JAR file directly.


java -jar parkinglot.jar

3. Docker
   
Alternatively, you can use Docker to run the project without setting it up locally.


docker run -it --rm raja110199/parkinglot

---> Commands:

The Parking Lot System supports the following commands:

1- create_parking_lot <capacity> - Creates a parking lot with the specified capacity.
2- extend_parking_lot <new_capacity> - Extends the parking lot to accommodate more vehicles.
3- park <registration_number> <color> - Parks a car with the given registration number and color.
4- leave <slot_number> - Removes the car parked in the specified slot.
5- status - Displays the current status of the parking lot.
6- registration_numbers_for_cars_with_colour <color> - Retrieves registration numbers of cars with the specified color.
7- slot_number_for_registration_number <registration_number> - Retrieves the slot number where a car with the specified registration number is parked.
8- slot_numbers_for_cars_with_colour <color> - Retrieves slot numbers of cars with the specified color.
9- exit - Exits the parking lot system.

---> Example Usage

Here's an example of using the Parking Lot System:

->Welcome to the Parking Lot System!

->create_parking_lot 6
Created a parking lot with 6 slots.

->park up-11-ax-0122 black
Allocated slot number: 1

->park up-11-ax-0122 black
This registration number already has a parking slot.

->park up-11-bx-0122 black
Allocated slot number: 2

->status
Slot No. Registration No Colour
1 up-11-ax-0122 black
2 up-11-bx-0122 black

->registration_numbers_for_cars_with_colour black
up-11-ax-0122, up-11-bx-0122

->registration_numbers_for_cars_with_colour white
Not found

->slot_number_for_registration_number 1
Not found

->slot_number_for_registration_number up-11-ax-0122
1

->slot_numbers_for_cars_with_colour black
1, 2

->slot_numbers_for_cars_with_colour white
Not found

->park ui-19-oi-192 red
Allocated slot number: 3

->create_parking_lot 5
Parking lot already created. You can Extend The Parking lot with this extend_parking_lot command

->extend_parking_lot 4
Extended parking lot to 10 slots.
Extended parking lot to 10 slots.

->leave 1
Slot number 1 is free.

->leave 2
Slot number 2 is free.

->leave3
Invalid command.

->leave 3
Slot number 3 is free.

->park up-11-ax-0122 black
Allocated slot number: 1

->exit
Bye...

---> Additional Information


1- This project follows best practices and includes error handling using try-catch blocks.
2- It utilizes a min heap data structure for assigning parking slots and tree map and hashmap for efficient retrieval of information.
3- You can interact with the system through a command-line interface.
4- Enjoy managing your parking lot efficiently! If you encounter any issues or have suggestions for improvement, feel free to reach out to the project maintainers.
