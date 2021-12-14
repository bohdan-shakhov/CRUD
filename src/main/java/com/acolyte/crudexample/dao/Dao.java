package com.acolyte.crudexample.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    T find(ID id) throws SQLException;
    List<T> findAll(int offset, int noOfRecords) throws SQLException;
    boolean insert(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
}
