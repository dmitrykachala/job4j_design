package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void doQuery(String query, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    private static Properties convertToProperties(String propertyFile) {
        Properties properties = new Properties();
        try (FileInputStream fi = new FileInputStream(propertyFile)) {
            properties.load(fi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("jdbc.driver"));
        connection = DriverManager
                .getConnection(properties.getProperty("jdbc.url"),
                        properties.getProperty("jdbc.username"),
                        properties.getProperty("jdbc.password"));
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
    }

    public void createTable(String tableName) throws Exception {
        doQuery("create table if not exists " + tableName + "(id serial primary key);", tableName);
    }

    public void dropTable(String tableName) throws Exception {
        doQuery("drop table if exists " + tableName + ";", tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        doQuery("alter table " + tableName + " add column " + columnName
                + " " + type + ";", tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        doQuery("alter table " + tableName + " drop column " + columnName + ";", tableName);
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName) throws Exception {
        doQuery("alter table " + tableName + " rename column " + columnName
                + " to " + newColumnName + ";", tableName);
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

    public static void main(String[] args) throws Exception {
        Properties properties = convertToProperties("app.properties");
        TableEditor editor = new TableEditor(properties);
        editor.createTable("test");
        editor.addColumn("test", "col1", "text");
        editor.addColumn("test", "col2", "text");
        editor.addColumn("test", "col3", "int");
        editor.dropColumn("test", "col2");
        editor.renameColumn("test", "col3", "renamedCol");
        editor.dropTable("test");
    }
}