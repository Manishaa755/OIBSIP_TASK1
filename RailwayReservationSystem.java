import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

class RailwayReservationSystem {
    static final int MAX_SEATS = 72;
    private static final int max_book = 2;
    static boolean[] seats = new boolean[MAX_SEATS];
    static HashMap<String, String> userData = new HashMap<>();
    static HashMap<String, String> pnrStatus = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // Initialize user login data
        userData.put("Teena16", "123456");
        userData.put("Gwalior52", "gwalior@16");
        userData.put("user52", "Indore@16");

        // User login
        boolean correct = false;
        while (!correct) {
            System.out.print("Enter your login ID: ");
            String ID = sc.next();

            System.out.print("Enter your password: ");
            String password = sc.next();

            correct = authenticateUser(ID, password);

            if (!correct) {
                System.out.println("Invalid login credentials. Please try again.");
                System.out.println();
            }
        }

        int choice;

        do {
            System.out.println("====IRCTC Railway Reservation System ====");
            System.out.println("1. Reserve a seat");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Check seat availability");
            System.out.println("4. Get train name from train number");
            System.out.println("5. Check PNR status");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    reserveSeat(random);
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    checkAvailability();
                    break;
                case 4:
                    getTrainName();
                    break;

                case 5:
                    checkPNRStatus(sc);
                    break;
                case 6:
                    System.out.println("Thank you for using the Railway Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 6);
    }

    static void reserveSeat(Random random) {
        int bookingAttempts = 0;
        int seatnum;
        do {
            seatnum = random.nextInt(MAX_SEATS) + 1;
            bookingAttempts++;
        } while (seats[seatnum - 1] && bookingAttempts <= max_book);

        if (seats[seatnum - 1]) {
            System.out.println("All seats are occupied. Please try again later.");
        } else {
            seats[seatnum - 1] = true;
            System.out.println("Seat reserved. Your seat number is " + seatnum);
        }
    }

    static void cancelReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the seat number to cancel the reservation: ");
        int seatnum = scanner.nextInt();

        if (seatnum < 1 || seatnum > MAX_SEATS) {
            System.out.println("Invalid seat number.");
        } else if (!seats[seatnum - 1]) {
            System.out.println("No reservation found for seat " + seatnum + ".");
        } else {
            seats[seatnum - 1] = false;
            System.out.println("Reservation for seat " + seatnum + " has been canceled.");
        }
    }

    static void checkPNRStatus(Scanner scanner) {
        System.out.print("Enter the PNR number: ");
        String pnrNumber = scanner.next();

        String status = pnrStatus.get(pnrNumber);
        if (status != null) {
            System.out.println("PNR status for PNR number " + pnrNumber + " is " + status);
        } else {
            System.out.println("PNR number not found.");
        }
    }

    static void checkAvailability() {
        System.out.println("Seat availability:");
        for (int i = 0; i < MAX_SEATS; i++) {
            if (seats[i]) {
                System.out.println("Seat " + (i + 1) + ": Reserved");
            } else {
                System.out.println("Seat " + (i + 1) + ": Available");
            }
        }
    }

    static void getTrainName() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the train number: ");
        int trainNumber = scanner.nextInt();

        String trainName = getName(trainNumber);
        if (trainName != null) {
            System.out.println("Train name for train number " + trainNumber + " is " + trainName);
        } else {
            System.out.println("Train name not found for train number " + trainNumber);
        }
    }

    static boolean authenticateUser(String ID, String password) {
        String savedpass = userData.get(ID);

        if (savedpass != null && savedpass.equals(password)) {
            System.out.println("Login successful! Welcome " + ID + ".");
            return true;
        } else {
            return false;
        }
    }

    static String getName(int trainNumber) {
        HashMap<Integer, String> trainMap = new HashMap<>();
        trainMap.put(22436, "Vande Bharat Express");
        trainMap.put(11126, "Gwalior-Indore Intercity Express");
        trainMap.put(21125, "Indore Bhind Express ");

        return trainMap.get(trainNumber);
    }
}
