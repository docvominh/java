package com.vominh.example.jooq;

import static com.vominh.example.jooq.generate.Tables.*;

import com.vominh.example.jooq.generate.Tables;
import com.vominh.example.jooq.generate.tables.Device;
import com.vominh.example.jooq.generate.tables.records.DeviceRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.meta.derby.sys.Sys;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 */
public class QueryTest {
    public static void main(String[] args) throws SQLException {
        DataSource ds = DataSourceUtils.get();
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);

        DSLContext context = DSL.using(connection, SQLDialect.POSTGRES);
        Query query = context.select(DEVICE.ID, DEVICE.NAME, DEVICE.SERIAL).from(DEVICE);
        System.out.println(query.getSQL());

        Result<DeviceRecord> devices = context.selectFrom(DEVICE).where(DEVICE.ID.greaterThan(0)).fetch();


        devices.forEach(d -> {
            System.out.println(d.getName());
        });
    }
}
