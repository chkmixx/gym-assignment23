import  Database.MemberDAO;
import model.*;

public class Main {
    public static void main(String[] args) {

        MemberDAO dao = new MemberDAO();

        Member m1 = new BasicMember(1, "Ali", 20, "BASIC");
        dao.insertMember(m1);

        Member m2 = new StudentMember(2, "Aruzhan", 19, "STUDENT", "AITU");
        dao.insertMember(m2);

        Member m3 = new VIPMember(3, "Dias", 25, "VIP", "Yoga");
        dao.insertMember(m3);


        System.out.println("\n--- ALL MEMBERS ---");
        dao.getAllMembers().forEach(System.out::println);

        System.out.println("\n--- SEARCH 'Ali' ---");
        dao.searchByName("Ali").forEach(System.out::println);

        m1.setName("Ali Updated");
        dao.updateMember(m1);
        dao.deleteMember(3);
    }
}



