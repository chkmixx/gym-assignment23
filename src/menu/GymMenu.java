package menu;

import Database.TrainerDAO;
import model.Trainer;
import java.util.List;
import java.util.Scanner;

public class GymMenu implements Menu {
    private Scanner scanner;
    private TrainerDAO trainerDAO;

    public GymMenu() {
        this.scanner = new Scanner(System.in);
        this.trainerDAO = new TrainerDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("     GYM MANAGEMENT SYSTEM        ");
        System.out.println("     Week 8: Database-Driven       ");
        System.out.println("     PostgreSQL database           ");
        System.out.println("     CRUD + Search (Week 8)        ");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n");
        System.out.println("            MAIN MENU                  ");
        System.out.println("═════════════════════════════════");
        System.out.println("┌─ TRAINER MANAGEMENT ───────────────────┐");
        System.out.println("│ 1. Add Trainer                         │");
        System.out.println("│ 2. View All Trainers                   │");
        System.out.println("│ 3. Update Trainer                      │");
        System.out.println("│ 4. Delete Trainer                      │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 5. Search Trainer by Name              │");
        System.out.println("│ 6. Search by Salary Range              │");
        System.out.println("│ 7. High-Paid Trainers (Salary >= X)    │");
        System.out.println("│ 0. Exit                                │");
        System.out.println("└────────────────────────────────────────┘");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addTrainer();
                    case 2 -> viewAllTrainers();
                    case 3 -> updateTrainer();
                    case 4 -> deleteTrainer();
                    case 5 -> searchByName();
                    case 6 -> searchBySalaryRange();
                    case 7 -> searchHighPaidTrainers();
                    case 0 -> {
                        running = false;
                        System.out.println("\n Goodbye!");
                    }
                    default -> System.out.println(" Invalid choice!");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (Exception e) {
                System.out.println(" Error: Invalid input!");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void addTrainer() {
        try {
            System.out.println("\n--- ADD TRAINER ---");

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Specialization: ");
            String specialization = scanner.nextLine();

            System.out.print("Enter Salary: ");
            int salary = scanner.nextInt();
            scanner.nextLine();

            Trainer trainer = new Trainer(0, name, specialization, salary);
            trainerDAO.insertTrainer(trainer);

        } catch (Exception e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }

    private void viewAllTrainers() {
        List<Trainer> trainers = trainerDAO.getAllTrainers();

        System.out.println("\n--- ALL TRAINERS ---");

        if (trainers.isEmpty()) {
            System.out.println(" No trainers found.");
        } else {
            for (int i = 0; i < trainers.size(); i++) {
                System.out.println((i + 1) + ". " + trainers.get(i));
            }
        }
    }

    private void updateTrainer() {
        try {
            System.out.print("\nEnter Trainer ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("New Name: ");
            String name = scanner.nextLine();

            System.out.print("New Specialization: ");
            String specialization = scanner.nextLine();

            System.out.print("New Salary: ");
            int salary = scanner.nextInt();
            scanner.nextLine();

            Trainer trainer = new Trainer(id, name, specialization, salary);
            trainerDAO.updateTrainer(trainer);

        } catch (Exception e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }

    private void deleteTrainer() {
        try {
            System.out.print("\nEnter Trainer ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Are you sure you want to delete this trainer? (y/n): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                trainerDAO.deleteTrainer(id);
            } else {
                System.out.println(" Deletion cancelled.");
            }

        } catch (Exception e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }

    private void searchByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine();

        List<Trainer> trainers = trainerDAO.searchByName(name);
        displaySearchResults(trainers);
    }

    private void searchBySalaryRange() {
        try {
            System.out.print("\nEnter minimum salary: ");
            int min = scanner.nextInt();

            System.out.print("Enter maximum salary: ");
            int max = scanner.nextInt();
            scanner.nextLine();

            List<Trainer> trainers = trainerDAO.searchBySalaryRange(min, max);
            displaySearchResults(trainers);

        } catch (Exception e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }

    private void searchHighPaidTrainers() {
        try {
            System.out.print("\nEnter minimum salary: ");
            int min = scanner.nextInt();
            scanner.nextLine();

            List<Trainer> trainers = trainerDAO.searchByMinSalary(min);
            displaySearchResults(trainers);

        } catch (Exception e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }

    private void displaySearchResults(List<Trainer> trainers) {
        System.out.println("\n--- SEARCH RESULTS ---");

        if (trainers.isEmpty()) {
            System.out.println(" No trainers found.");
        } else {
            for (int i = 0; i < trainers.size(); i++) {
                System.out.println((i + 1) + ". " + trainers.get(i));
            }
        }
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue]");
        scanner.nextLine();
    }
}


