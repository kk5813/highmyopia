package com.ly.highmyopia.pool;

import java.sql.Connection;

public interface ConnectionBean {

     Integer maxConnect = 5;
    
    Integer normalConnect = 3;

    Connection getConnection();

    Connection getConnection(long timeout);

    void closeConncetion(Connection con);

    void release();
}
