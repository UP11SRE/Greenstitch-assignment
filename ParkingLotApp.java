import java.util.Scanner;

public class ParkingLotApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot System!");

        Scanner scanner = new Scanner(System.in);
        ParkingLotService parkingLotService = null;

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Bye...");
                    break;
                }

                String[] tokens = input.split(" ");
                if (tokens[0].equalsIgnoreCase("create_parking_lot")) {
                    if (parkingLotService != null) {
                        System.out.println("Parking lot already created. You can Extend The Parking lot with this " + "extend_parking_lot" + " command");
                        continue;
                    }
                    int totalSlots = Integer.parseInt(tokens[1]);
                    parkingLotService = new ParkingLotService(totalSlots);
                    System.out.println(parkingLotService.createParking(totalSlots));
                } else if (tokens[0].equalsIgnoreCase("extend_parking_lot")) {
                    if (parkingLotService == null) {
                        System.out.println("No parking lot created yet. Please create one first.");
                        continue;
                    }
                    int additionalSlots = Integer.parseInt(tokens[1]);
                    int totalSlots = parkingLotService.getTotalSlots() + additionalSlots;
                    System.out.println("Extended parking lot to " + totalSlots + " slots.");
                    System.out.println(parkingLotService.extendParking(totalSlots));
                } else {
                    if (parkingLotService == null) {
                        System.out.println("Please create parking lot first.");
                        continue;
                    }

                    if (tokens[0].equalsIgnoreCase("park")) {
                        String registration = tokens[1];
                        if (parkingLotService.getSlotNumberForRegistration(registration).equals("Not found")) {
                            String color = tokens[2];
                            System.out.println(parkingLotService.parkCar(registration, color));
                        } else {
                            System.out.println("This registration number already has a parking slot.");
                        }
                    } else {
                        String output = CommandParser.parseCommand(input, parkingLotService);
                        System.out.println(output);
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong," + e.getMessage());
            }
        }
    }
}
