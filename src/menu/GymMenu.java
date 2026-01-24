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
        allMembers.add(new Trainer(4, "Nazerke", 25, "Fitness", 6));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== GYM MENU =====");
        System.out.println("1. View all members");
        System.out.println("2. Add VIP member");
        System.out.println("3. Polymorphism demo");
        System.out.println("0. Exit");
    }

    @Override
    public void run(){
        boolean running=true;

        while(running){
            displayMenu();
            System.out.println("Enter choice");

            try{
                int choice=Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1->viewAllMembers();
                    case 2->addVIPMember();
                    case 3->polymorphismDemo();
                    case 0->running=false;
                    default -> System.out.println("Invalid choice");
                }

            }catch(NumberFormatException e){
                System.out.println("Error:enter a number!");
            } catch (Exception e) {
                System.out.println("Eror:"+e.getMessage());
            }
        }
    }

    private void viewAllMembers() {
        System.out.println("\n--- ALL MEMBERS ---");
        for (Member m : allMembers) {
            System.out.println(m);

            if (m instanceof VIPMember vip) {
                vip.attendSpecialClass();
            }
        }
    }

    private void addVIPMember() {
        System.out.println("\n--- ADD VIP MEMBER ---");

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

        System.out.println(" VIP Member added!");
    }

    private void polymorphismDemo() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Member m : allMembers) {
            m.attendTraining();
        }
    }
}