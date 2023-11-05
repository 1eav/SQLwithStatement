package org.example;

import java.sql.*;

public class SQLwithStatement {
    public static void main(String[] args) {
//        url
//        user
//        password
        //1
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet resultSet =  statement.executeQuery("SELECT name, duration, type FROM top.courses WHERE duration >= 30");
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                String duration = resultSet.getString("duration");
                String type = resultSet.getString("type");
                System.out.println("Название курса: " + courseName + " - Продолжительность: " + duration + " - Тип курса: " + type);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2
        String name = "Unity";
        int duration = 60;
        String type = "PROGRAMMING";
        String description = "Представляем вашему вниманию уникальный курс для разработчика игр на Unity";
        int teacherId = 3;
        int studentsCount = 180;
        double price = 70000;
        double pricePerHour = 500.00;

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String insertQuery = "INSERT INTO courses " +
                    "(name, duration, type, description, teacher_id, students_count, price, price_per_hour)" +
                    "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, duration);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, teacherId);
            preparedStatement.setInt(6, studentsCount);
            preparedStatement.setDouble(7, price);
            preparedStatement.setDouble(8, pricePerHour);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Все записи успешно добавились");
            } else {
                System.out.println("Произошла ошибка");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String queryUpdate = "UPDATE courses SET price = ? WHERE type = 'PROGRAMMING'";
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setInt(1,5000);
            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate > 0) {
                System.out.println("Запись обновлена");
            } else {
                System.out.println("Произошла ошибка");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String queryDelete = "DELETE FROM courses WHERE duration <= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setInt(1,10);
            int executeDelete = preparedStatement.executeUpdate();
            if (executeDelete > 0) {
                System.out.println("Запись удалена");
            } else {
                System.out.println("Произошла ошибка");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}