package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GymMenu implements Menu {

    private ArrayList<Member> allMembers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public GymMenu() {
        allMembers.add(new BasicMember(1, "Savazhan", 17, "Basic"));
        allMembers.add(new StudentMember(2, "Tomiris", 16, "Student", "AITU"));
        allMembers.add(new VIPMember(3, "Aruzhan", 20, "VIP", "Yoga"));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== GYM MENU =====");
        System.out.println("1. View all members");
        System.out.println("2. Add Basic member");
        System.out.println("3. Add Student member");
        System.out.println("4. Add VIP member");
        System.out.println("5. Demonstrate training (Polymorphism)");
        System.out.println("0. Exit");
    }
    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> viewAllMembers();
                    case 2 -> addBasicMember();
                    case 3 -> addStudentMember();
                    case 4 -> addVIPMember();
                    case 5 -> polymorphismDemo();
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: enter a number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewAllMembers() {
        System.out.println("\n--- ALL MEMBERS ---");

        if (allMembers.isEmpty()) {
            System.out.println("No members yet!");
            return;
        }

        for (Member m : allMembers) {
            System.out.println(m);


            if (m instanceof VIPMember vip) {
                vip.attendSpecialClass();
            }
        }
    }

    private void addBasicMember() {
        System.out.println("\n--- ADD BASIC MEMBER ---");

        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            BasicMember basic = new BasicMember(id, name, age, "Basic");
            allMembers.add(basic);

            System.out.println("Basic Member added!");
        } catch (NumberFormatException e) {
            System.out.println("Error: ID and age must be numbers!");
        }
    }

    private void addStudentMember() {
        System.out.println("\n--- ADD STUDENT MEMBER ---");

        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter university: ");
            String university = scanner.nextLine();

            StudentMember student = new StudentMember(id, name, age, "Student", university);
            allMembers.add(student);

            System.out.println("Student Member added!");
        } catch (NumberFormatException e) {
            System.out.println("Error: ID and age must be numbers!");
        }
    }

    private void addVIPMember() {
        System.out.println("\n--- ADD VIP MEMBER ---");

        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter special class: ");
            String specialClass = scanner.nextLine();

            VIPMember vip = new VIPMember(id, name, age, "VIP", specialClass);
            allMembers.add(vip);

            System.out.println("VIP Member added!");
        } catch (NumberFormatException e) {
            System.out.println("Error: ID and age must be numbers!");
        }
    }

    private void polymorphismDemo() {
        System.out.println("\n--- TRAINING / POLYMORPHISM DEMO ---");

        if (allMembers.isEmpty()) {
            System.out.println("No members to train!");
            return;
        }

        for (Member m : allMembers) {
            m.attendTraining(); // Polymorphism
        }
    }
}
