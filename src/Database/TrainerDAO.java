package Database;

import model.Trainer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    public boolean insertTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (name, specialization, salary) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trainer.getName());
            statement.setString(2, trainer.getSpecialization());
            statement.setInt(3, trainer.getSalary());

            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                System.out.println(" Trainer inserted: " + trainer.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Insert trainer failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainer ORDER BY trainer_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return trainers;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                trainers.add(extractTrainer(rs));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Select trainers failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return trainers;
    }
    public Trainer getTrainerById(int id) {
        String sql = "SELECT * FROM trainer WHERE trainer_id=?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return extractTrainer(rs);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Get trainer by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public boolean updateTrainer(Trainer trainer) {
        String sql = "UPDATE trainer SET name=?, specialization=?, salary=? WHERE trainer_id=?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trainer.getName());
            statement.setString(2, trainer.getSpecialization());
            statement.setInt(3, trainer.getSalary());
            statement.setInt(4, trainer.getTrainerId());

            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                System.out.println(" Trainer updated: " + trainer.getName());
                return true;
            } else {
                System.out.println(" Trainer not found");
            }

        } catch (SQLException e) {
            System.out.println(" Update trainer failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean deleteTrainer(int id) {
        String sql = "DELETE FROM trainer WHERE trainer_id=?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rows = statement.executeUpdate();
            statement.close();

            if (rows > 0) {
                System.out.println(" Trainer deleted (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" Trainer not found");
            }

        } catch (SQLException e) {
            System.out.println(" Delete trainer failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }
    public List<Trainer> searchByName(String name) {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainer WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return trainers;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                trainers.add(extractTrainer(rs));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return trainers;

    }
    public List<Trainer> searchBySalaryRange(int min, int max) {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainer WHERE salary BETWEEN ? AND ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return trainers;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, min);
            statement.setInt(2, max);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                trainers.add(extractTrainer(rs));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Search by salary range failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return trainers;
    }

    public List<Trainer> searchByMinSalary(int minSalary) {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainer WHERE salary >= ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return trainers;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minSalary);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                trainers.add(extractTrainer(rs));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Search by min salary failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return trainers;
    }
    private Trainer extractTrainer(ResultSet rs) throws SQLException {
        return new Trainer(
                rs.getInt("trainer_id"),
                rs.getString("name"),
                rs.getString("specialization"),
                rs.getInt("salary")
        );
    }
}
