package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAll() {

        List<User> userList = null;
        String select = "SELECT * FROM users";

        try (PreparedStatement stmt = connection.prepareStatement(select)) {

            ResultSet result = stmt.executeQuery();
            userList = new ArrayList<>();

            while (result.next()) {
                long id = result.getLong("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                int age = result.getInt("age");
                String role = result.getString("role");

                userList.add(new User(id, email, password, name, age, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User getById(long id) {

        String select = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(select)) {

            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                int age = result.getInt("age");
                String role = result.getString("role");

                user = new User(id, email, password, name, age, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getByEmail(String email) throws SQLException {

        String select = "SELECT * FROM users WHERE email = ?";

        PreparedStatement stmt = connection.prepareStatement(select);
        stmt.setString(1, email);

        ResultSet result = stmt.executeQuery();
        User user = null;

        if (result.next()) {
            long id = result.getLong("id");
            String password = result.getString("password");
            String name = result.getString("name");
            int age = result.getInt("age");
            String role = result.getString("role");

            user = new User(id, email, password, name, age, role);
        }

        return user;
    }

    public boolean add(User user) {

        String insert = "INSERT INTO users(email, password, name, age, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insert)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(User user) {

        String update = "UPDATE users SET email = ?, password = ?, name = ?, age = ?, role = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(update)) {

            stmt.setLong(6, user.getId());
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getRole());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {

        String delete = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(delete)) {
            stmt.setLong(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validate(String email, String password) {

        boolean isValidated = false;
        String select = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(select)) {

            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {

                String dbPassword = result.getString("password");

                if (password.equals(dbPassword)) {
                    isValidated = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValidated;
    }

    public void createTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, email VARCHAR(256), password VARCHAR(256), name VARCHAR(256), age INT, PRIMARY KEY (id))";

        try (PreparedStatement stmt = connection.prepareStatement(createTable)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {

        String dropTable = "DROP TABLE IF EXISTS users";

        try (PreparedStatement stmt = connection.prepareStatement(dropTable)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}