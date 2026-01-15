import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Member> allMembers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

       
        allMembers.add(new Member(1, "Savazhan", 17, "Basic"));
        allMembers.add(new StudentMember(2, "Tomiris", 16, "Student", "AITU"));
        allMembers.add(new VIPMember(3, "Aruzhan", 20, "VIP", "Yoga"));
        allMembers.add(new Trainer(4, "Albina", 25, "Fitness", 6));

        System.out.println("=== Polymorphism demonstration for Members ===");
        for (Member m : allMembers) {
            m.attendTraining();
        }

        addVIPMember();
        viewAllMembers();
    }

    private static void addVIPMember() {
        System.out.println("\n--- ADD VIP MEMBER ---");

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter special class: ");
        String specialClass = scanner.nextLine();

        VIPMember vip = new VIPMember(id, name, age, "VIP", specialClass);
        allMembers.add(vip);

        System.out.println("\nVIP Member added successfully!");
    }

    private static void viewAllMembers() {
        System.out.println("\n========================================");
        System.out.println(" ALL MEMBERS (POLYMORPHIC LIST)");
        System.out.println("========================================");

        if (allMembers.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        System.out.println("Total members: " + allMembers.size());
        System.out.println();

        for (int i = 0; i < allMembers.size(); i++) {
            Member m = allMembers.get(i);
            System.out.println((i + 1) + ". " + m);

            if (m instanceof VIPMember) {
                VIPMember vip = (VIPMember) m;
                vip.attendSpecialClass();
            }

            System.out.println();
        }
    }
}
