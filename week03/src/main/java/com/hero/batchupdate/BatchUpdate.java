package com.hero.batchupdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchUpdate {
    private static final String URL = "jdbc:mysql://localhost:3306/compress?useInformationSchema=false&charset=utf8mb4&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void batchUpdate () throws SQLException {
        String sql = "insert into t_user (name, password, email, role, status) values (?, ?, ?, ?, ?)";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        int batchSize = 1000;
        int size = 1000000;
        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < size; i++) {
                preparedStatement.setString(1, "zp" + i);
                preparedStatement.setString(2, "9090" + i);
                preparedStatement.setString(3, "zp"+i+"@hpe.com");
                preparedStatement.setInt(4, 1);
                preparedStatement.setInt(5, 0);
                preparedStatement.addBatch();
                if ((i%batchSize) == 0 && i>=batchSize){
                    preparedStatement.executeBatch();
                    connection.commit();
                    System.out.println("commit " + batchSize + " to db");
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
            assert preparedStatement != null;
            preparedStatement.close();
        }


    }

    public static void main(String[] args) throws SQLException {
        BatchUpdate batchUpdate = new BatchUpdate();
        batchUpdate.batchUpdate();
    }
}
