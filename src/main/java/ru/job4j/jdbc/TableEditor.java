package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Config config;

    private Statement statement;

    public TableEditor(Config config) throws SQLException, ClassNotFoundException {
        this.config = config;
        this.config.load();
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        this.connection = DriverManager.getConnection(url, login, password);
        this.statement = connection.createStatement();
    }

    public void createTable(String tableName) throws Exception {
        if (!statement.isClosed()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s(%s);",
                    tableName, "id serial primary key"
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void dropTable(String tableName) throws Exception {
        if (!statement.isClosed()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
            statement.execute(sql);
            System.out.format("Удалена таблица: %s", tableName);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        if (!statement.isClosed()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                    tableName, columnName, type
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        if (!statement.isClosed()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN IF EXISTS %s;",
                    tableName, columnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        if (!statement.isClosed()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try (TableEditor tableEditor = new TableEditor(new Config("./app.properties"))) {
            String tableName = "new_table1";
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "New_column1", "text");
            tableEditor.addColumn(tableName, "New_column2", "text");
            tableEditor.dropColumn(tableName, "New_column1");
            tableEditor.renameColumn(tableName, "New_column2", "Wow_column");
            tableEditor.dropTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
