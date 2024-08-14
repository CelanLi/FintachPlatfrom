package com.celan.micrdataservice;

import com.celan.dataservice.MicrDataserviceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest(classes = MicrDataserviceApplication.class)
class MicrDataserviceApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void testDatabaseConnection() {
        try {
            System.out.println("Successfully connected to the database" + dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database" + e.getMessage());
        }
    }


}
