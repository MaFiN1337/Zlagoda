package com.example.dao;

public interface DaoConnection extends AutoCloseable{
    void begin();

    void commit();

    void rollback();

    void close();
}
