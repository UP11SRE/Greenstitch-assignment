import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotService {
    private int totalSlots;
    private PriorityQueue<Integer> minHeap;
    private Map<Integer, String> slots;
    private TreeMap<String, Integer> registrationToSlot;
    private Map<String, List<Integer>> colorToSlots;

    public ParkingLotService(int totalSlots) {
        this.totalSlots = totalSlots;
        this.minHeap = new PriorityQueue<>();
        this.slots = new HashMap<>();
        this.registrationToSlot = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        this.colorToSlots = new HashMap<>();
        for (int i = 1; i <= totalSlots; i++) {
            minHeap.offer(i);
        }
    }

    public String createParking(int numSlots) {
        this.totalSlots = numSlots;
        slots.clear();
        registrationToSlot.clear();
        colorToSlots.clear();
        minHeap.clear();
        for (int i = 1; i <= totalSlots; i++) {
            minHeap.offer(i);
        }
        return "Created a parking lot with " + totalSlots + " slots.";
    }

    public String extendParking(int numSlots) {
        int additionalSlots = numSlots - totalSlots;
        this.totalSlots = numSlots;

        try {
            for (int i = totalSlots - additionalSlots + 1; i <= totalSlots; i++) {
                minHeap.offer(i);
            }
            return "Extended parking lot to " + totalSlots + " slots.";
        } catch (Exception e) {
            return "An error occurred while extending the parking lot: " + e.getMessage();
        }
    }

    public String parkCar(String registration, String color) {
        if (minHeap.isEmpty()) {
            return "Sorry, parking lot is full.";
        }

        try {
            if (registrationToSlot.containsKey(registration)) {
                return "This registration number already has a parking slot.";
            }

            int slotNumber = minHeap.poll();
            slots.put(slotNumber, registration + " " + color.toLowerCase());
            registrationToSlot.put(registration, slotNumber);
            colorToSlots.computeIfAbsent(color.toLowerCase(), k -> new ArrayList<>()).add(slotNumber);
            return "Allocated slot number: " + slotNumber;
        } catch (Exception e) {
            return "An error occurred while parking the car: " + e.getMessage();
        }
    }
    

    public String leaveParking(int slotNumber) {
        if (!slots.containsKey(slotNumber)) {
            return "Slot number " + slotNumber + " is already free.";
        }

        try {
            String carDetails = slots.get(slotNumber);
            String[] details = carDetails.split(" ");
            String registration = details[0];
            String color = details[1];
            slots.remove(slotNumber);
            registrationToSlot.remove(registration);
            colorToSlots.get(color).remove(Integer.valueOf(slotNumber));
            minHeap.offer(slotNumber);
            return "Slot number " + slotNumber + " is free.";
        } catch (Exception e) {
            return "An error occurred while leaving the parking lot: " + e.getMessage();
        }
    }

    public String getStatus() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Slot No. Registration No Colour\n");
            for (int slotNumber : slots.keySet()) {
                String carDetails = slots.get(slotNumber);
                sb.append(slotNumber).append(" ").append(carDetails).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "An error occurred while getting the parking lot status: " + e.getMessage();
        }
    }

    public String getRegistrationNumbersForColor(String color) {
        try {
            List<Integer> slotsWithColor = colorToSlots.getOrDefault(color.toLowerCase(), Collections.emptyList());
            if (slotsWithColor.isEmpty()) {
                return "Not found";
            }
            List<String> registrationNumbers = new ArrayList<>();
            for (int slotNumber : slotsWithColor) {
                String carDetails = slots.get(slotNumber);
                String[] details = carDetails.split(" ");
                registrationNumbers.add(details[0]);
            }
            return String.join(", ", registrationNumbers);
        } catch (Exception e) {
            return "An error occurred while getting registration numbers for color: " + e.getMessage();
        }
    }
    

    public String getSlotNumberForRegistration(String registration) {
        try {
            Integer slotNumber = registrationToSlot.get(registration);
            if (slotNumber == null) {
                return "Not found";
            } else {
                return String.valueOf(slotNumber);
            }
        } catch (Exception e) {
            return "An error occurred while getting slot number for registration: " + e.getMessage();
        }
    }

    public String getSlotNumbersForColor(String color) {
        try {
            List<Integer> slotsWithColor = colorToSlots.getOrDefault(color.toLowerCase(), Collections.emptyList());
            if (slotsWithColor.isEmpty()) {
                return "Not found";
            } else {
                return slotsWithColor.stream().map(String::valueOf).collect(Collectors.joining(", "));
            }
        } catch (Exception e) {
            return "An error occurred while getting slot numbers for color: " + e.getMessage();
        }
    }

    public int getTotalSlots() {
        return totalSlots;
    }
}
