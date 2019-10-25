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

                userList.add(new User(id, email, password, name, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User get(long id) {

        User user = null;
        String select = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(select)) {
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                int age = result.getInt("age");

                user = new User(id, email, password, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User get(String email) throws SQLException {

        User user = null;
        String select = "SELECT * FROM users WHERE email = ?";

        PreparedStatement stmt = connection.prepareStatement(select);
        stmt.setString(1, email);

        ResultSet result = stmt.executeQuery();

        if (result.next()) {
            long id = result.getLong("id");
            String password = result.getString("password");
            String name = result.getString("name");
            int age = result.getInt("age");

            user = new User(id, email, password, name, age);
        }

        return user;
    }

    public boolean add(String email, String password, String name, int age) {

        String insert = "INSERT INTO users(email, password, name, age) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insert)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setInt(4, age);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(long id, String email, String password, String name, int age) {

        String update = "UPDATE users SET email = ?, password = ?, name = ?, age = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(update)) {

            stmt.setLong(5, id);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setInt(4, age);

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