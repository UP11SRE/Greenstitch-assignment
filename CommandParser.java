public class CommandParser {
    public static String parseCommand(String input, ParkingLotService parkingLotService) {
        String[] tokens = input.split(" ");

        try {
            switch (tokens[0].toLowerCase()) {
                case "create_parking_lot":
                    int totalSlots = Integer.parseInt(tokens[1]);
                    return parkingLotService.createParking(totalSlots);
                case "park":
                    String registration = tokens[1];
                    String color = tokens[2];
                    return parkingLotService.parkCar(registration, color);
                case "leave":
                    int slotNumber = Integer.parseInt(tokens[1]);
                    return parkingLotService.leaveParking(slotNumber);
                case "status":
                    return parkingLotService.getStatus();
                case "registration_numbers_for_cars_with_colour":
                    String colorToSearch = tokens[1];
                    return parkingLotService.getRegistrationNumbersForColor(colorToSearch);
                case "slot_number_for_registration_number":
                    String registrationToSearch = tokens[1];
                    return parkingLotService.getSlotNumberForRegistration(registrationToSearch);
                case "slot_numbers_for_cars_with_colour":
                    String colorToSearchForSlots = tokens[1];
                    return parkingLotService.getSlotNumbersForColor(colorToSearchForSlots);
                default:
                    return "Invalid command.";
            }
        } catch (NumberFormatException e) {
            return "Something went wrong, Invalid format.";
        }
    }
}
