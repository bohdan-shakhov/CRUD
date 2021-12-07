package com.acolyte.crudexample.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceFactory {
    private final DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class);

    DataSourceFactory() {
        MysqlDataSource dataSource = new MysqlDataSource();
        String rootPath =
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().
                        getResource("database.properties")).getPath();
        InputStream input = null;

        try {
            input = new FileInputStream(rootPath);
            Properties properties = new Properties();
            properties.load(input);
            dataSource.setDatabaseName(properties.getProperty("database"));
            dataSource.setServerName(properties.getProperty("servername"));
            dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "File database.properties Not Found", e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "load properties error", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.log(Level.ERROR, "Failed to close input stream", e);
                }
            }
        }
        this.dataSource = dataSource;
    }

    public static Connection getConnection() {
        try {
            return SingletonHelper.INSTANCE.dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Failed to Get Connection", e);
        }
        return null;
    }

    private static class SingletonHelper {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
