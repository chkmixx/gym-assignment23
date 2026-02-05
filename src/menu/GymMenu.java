package menu;

import model.Staff;
import model.Trainer;
import model.AdminStaff;

import Database.StaffDAO;

import java.util.List;
import java.util.Scanner;


public class GymMenu implements Menu {

    private final Scanner scanner;
    private final StaffDAO staffDAO;

    public GymMenu() {
        this.scanner = new Scanner(System.in);
        this.staffDAO = new StaffDAO();

        System.out.println("\n========================================");
        System.out.println("        GYM MANAGEMENT SYSTEM");
        System.out.println("        Week 8: Database Driven");
        System.out.println("========================================");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n============= MAIN MENU ================");
        System.out.println("1. Add Trainer");
        System.out.println("2. Add Admin Staff");
        System.out.println("3. View All Staff");
        System.out.println("4. View Trainers Only");
        System.out.println("5. View Admin Staff Only");
        System.out.println("6. Update Staff");
        System.out.println("7. Delete Staff");
        System.out.println("8. Search by Name");
        System.out.println("9. Search by Salary Range");
        System.out.println("10. High Paid Staff");
        System.out.println("11. Polymorphism Demo");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Choose option: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addTrainer();
                    case 2 -> addAdmin();
                    case 3 -> viewAllStaff();
                    case 4 -> viewTrainers();
                    case 5 -> viewAdmins();
                    case 6 -> updateStaff();
                    case 7 -> deleteStaff();
                    case 8 -> searchByName();
                    case 9 -> searchBySalaryRange();
                    case 10 -> searchHighPaidStaff();
                    case 11 -> staffDAO.demonstratePolymorphism();
                    case 0 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid option!");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }


    private void addTrainer() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Specialization: ");
        String spec = scanner.nextLine();

        Trainer trainer = new Trainer(0, name, salary, exp, spec);
        staffDAO.insertTrainer(trainer);
    }

    private void addAdmin() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        AdminStaff admin = new AdminStaff(0, name, salary, exp);
        staffDAO.insertAdmin(admin);
    }



    private void viewAllStaff() {
        staffDAO.displayAllStaff();
    }

    private void viewTrainers() {
        List<Trainer> trainers = staffDAO.getAllTrainers();

        System.out.println("\n===== TRAINERS =====");
        if (trainers.isEmpty()) {
            System.out.println("No trainers found.");
        } else {
            for (Trainer t : trainers) {
                System.out.println(t);
            }
        }
    }

    private void viewAdmins() {
        List<AdminStaff> admins = staffDAO.getAllAdmins();

        System.out.println("\n===== ADMIN STAFF =====");
        if (admins.isEmpty()) {
            System.out.println("No admin staff found.");
        } else {
            for (AdminStaff a : admins) {
                System.out.println(a);
            }
        }
    }



    private void updateStaff() {
        System.out.print("Enter staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Staff staff = staffDAO.getStaffById(id);

        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }

        System.out.print("New name: ");
        String name = scanner.nextLine();

        System.out.print("New salary: ");
        double salary = scanner.nextDouble();

        System.out.print("New experience years: ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        if (staff instanceof Trainer) {
            System.out.print("New specialization: ");
            String spec = scanner.nextLine();
            staffDAO.updateTrainer(new Trainer(id, name, salary, exp, spec));
        } else if (staff instanceof AdminStaff) {
            staffDAO.updateAdmin(new AdminStaff(id, name, salary, exp));
        }
    }



    private void deleteStaff() {
        System.out.print("Enter staff ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        staffDAO.deleteStaff(id);
    }



    private void searchByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        List<Staff> results = staffDAO.searchByName(name);
        displayResults(results);
    }

    private void searchBySalaryRange() {
        System.out.print("Min salary: ");
        double min = scanner.nextDouble();

        System.out.print("Max salary: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        List<Staff> results = staffDAO.searchBySalaryRange(min, max);
        displayResults(results);
    }

    private void searchHighPaidStaff() {
        System.out.print("Minimum salary: ");
        double min = scanner.nextDouble();
        scanner.nextLine();

        List<Staff> results = staffDAO.searchByMinSalary(min);
        displayResults(results);
    }

    private void displayResults(List<Staff> list) {
        if (list.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (Staff s : list) {
                System.out.println("[" + s.getRole() + "] " + s);
            }
        }
    }


    private void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}

