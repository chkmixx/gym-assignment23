package Database;

import model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.BasicMember;
import model.StudentMember;
import model.VIPMember;
public class MemberDAO {
    public boolean insertMember(Member member) {
        String sql = "INSERT INTO members (name, age, membership_type) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getName());
            statement.setInt(2, member.getAge());
            statement.setString(3, member.getMembershipType());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println(" Member inserted: " + member.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Insert Member failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members ORDER BY id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return members;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                members.add(extractMemberFromResultSet(resultSet));
            }

            resultSet.close();
            statement.close();

            System.out.println(" Retrieved " + members.size() + " members");

        } catch (SQLException e) {
            System.out.println(" Select all members failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public Member getMemberById(int memberId) {
        String sql = "SELECT * FROM members WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Member member = extractMemberFromResultSet(resultSet);
                resultSet.close();
                statement.close();
                return member;
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Select member by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }
    public boolean updateMember(Member member) {
        String sql = "UPDATE members SET name = ?, age = ?, membership_type = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getName());
            statement.setInt(2, member.getAge());
            statement.setString(3, member.getMembershipType());
            statement.setInt(4, member.getId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println(" Member updated: " + member.getName());
                return true;
            } else {
                System.out.println(" No member found with ID: " + member.getId());
            }

        } catch (SQLException e) {
            System.out.println(" Update Member failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }
    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM members WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println(" Member deleted (ID: " + memberId + ")");
                return true;
            } else {
                System.out.println(" No member found with ID: " + memberId);
            }

        } catch (SQLException e) {
            System.out.println(" Delete Member failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }
    public List<Member> searchByName(String name) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return members;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                members.add(extractMemberFromResultSet(resultSet));
            }

            resultSet.close();
            statement.close();

            System.out.println(" Found " + members.size() + " members");

        } catch (SQLException e) {
            System.out.println("Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    public List<Member> searchByMembershipType(String type) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE membership_type = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return members;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                members.add(extractMemberFromResultSet(resultSet));
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Search by membership type failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return members;
    }

    private Member extractMemberFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String membershipType = rs.getString("membership_type");

        if ("BASIC".equalsIgnoreCase(membershipType)) {
            return new BasicMember(id, name, age, membershipType);

        } else if ("STUDENT".equalsIgnoreCase(membershipType)) {

            return new StudentMember(id, name, age, membershipType, "Unknown University");
        } else if ("VIP".equalsIgnoreCase(membershipType)) {

            return new VIPMember(id, name, age, membershipType, "Standard VIP Class");
        } else {
            return new BasicMember(id, name, age, membershipType);
        }
    }
}




