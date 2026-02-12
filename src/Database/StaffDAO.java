package Database;

import model.Staff;
import model.Trainer;
import model.AdminStaff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public boolean insertTrainer(Trainer trainer) {
        String sql = "INSERT INTO staff (name, salary, experience_years, staff_type, specialization) " +
                "VALUES (?, ?, ?, 'TRAINER', ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, trainer.getName());
            stmt.setDouble(2, trainer.getSalary());
            stmt.setInt(3, trainer.getExperienceYears());
            stmt.setString(4, trainer.getSpecialization());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean insertAdmin(AdminStaff admin) {
        String sql = "INSERT INTO staff (name, salary, experience_years, staff_type) " +
                "VALUES (?, ?, ?, 'ADMIN')";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setDouble(2, admin.getSalary());
            stmt.setInt(3, admin.getExperienceYears());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff ORDER BY staff_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Staff staff = extractStaffFromResultSet(rs);
                if (staff != null) {
                    staffList.add(staff);
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE staff_type = 'TRAINER' ORDER BY staff_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return trainers;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Staff staff = extractStaffFromResultSet(rs);
                if (staff instanceof Trainer) {
                    trainers.add((Trainer) staff);
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return trainers;
    }

    public List<AdminStaff> getAllAdmins() {
        List<AdminStaff> admins = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE staff_type = 'ADMIN' ORDER BY staff_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return admins;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Staff staff = extractStaffFromResultSet(rs);
                if (staff instanceof AdminStaff) {
                    admins.add((AdminStaff) staff);
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return admins;
    }

    public Staff getStaffById(int id) {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractStaffFromResultSet(rs);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    public boolean updateTrainer(Trainer trainer) {
        String sql = "UPDATE staff SET name=?, salary=?, experience_years=?, specialization=? " +
                "WHERE staff_id=? AND staff_type='TRAINER'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, trainer.getName());
            stmt.setDouble(2, trainer.getSalary());
            stmt.setInt(3, trainer.getExperienceYears());
            stmt.setString(4, trainer.getSpecialization());
            stmt.setInt(5, trainer.getStaffId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean updateAdmin(AdminStaff admin) {
        String sql = "UPDATE staff SET name=?, salary=?, experience_years=? " +
                "WHERE staff_id=? AND staff_type='ADMIN'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setDouble(2, admin.getSalary());
            stmt.setInt(3, admin.getExperienceYears());
            stmt.setInt(4, admin.getStaffId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean deleteStaff(int id) {
        String sql = "DELETE FROM staff WHERE staff_id=?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Staff> searchByName(String name) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE name ILIKE ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                staffList.add(extractStaffFromResultSet(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }

    public List<Staff> searchBySalaryRange(double min, double max) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary BETWEEN ? AND ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                staffList.add(extractStaffFromResultSet(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }

    public List<Staff> searchByMinSalary(double minSalary) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary >= ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, minSalary);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                staffList.add(extractStaffFromResultSet(rs));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }

    private Staff extractStaffFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("staff_id");
        String name = rs.getString("name");
        double salary = rs.getDouble("salary");
        int exp = rs.getInt("experience_years");
        String type = rs.getString("staff_type");

        if ("TRAINER".equals(type)) {
            return new Trainer(id, name, salary, exp, rs.getString("specialization"));
        } else if ("ADMIN".equals(type)) {
            return new AdminStaff(id, name, salary, exp);
        }
        return null;
    }
    public void displayAllStaff() {
        List<Staff> staffList = getAllStaff();

        System.out.println("\n========================================");
        System.out.println("   ALL STAFF FROM DATABASE");
        System.out.println("========================================");

        if (staffList.isEmpty()) {
            System.out.println("No staff members in database.");
        } else {
            for (int i = 0; i < staffList.size(); i++) {
                Staff s = staffList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + s.getRole() + "] ");
                System.out.println(s.toString());
            }
        }

        System.out.println("========================================\n");
    }
    public void demonstratePolymorphism() {
        List<Staff> staffList = getAllStaff();

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Staff from Database");
        System.out.println("========================================");

        if (staffList.isEmpty()) {
            System.out.println("No staff to demonstrate.");
        } else {
            for (Staff s : staffList) {
                s.work();
            }
        }

        System.out.println("========================================\n");
    }
}

