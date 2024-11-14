package com.example.productapi.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT 'p' || nextval('p_sequence')");

            if (rs.next()) return rs.getString(1);
        } catch (Exception e) {
            throw new RuntimeException("Error generating ID", e);
        }

        return null;
    }
}
