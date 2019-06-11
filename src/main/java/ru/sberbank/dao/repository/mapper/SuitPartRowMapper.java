package ru.sberbank.dao.repository.mapper;

import ru.sberbank.domain.entity.SuitPart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuitPartRowMapper implements RowMapper<SuitPart> {
    @Override
    public SuitPart mapRow(ResultSet resultSet, int i) throws SQLException {
        SuitPart suitPart = new SuitPart();
        suitPart.setId(resultSet.getInt("id"));
        suitPart.setName(resultSet.getString("name"));
        return suitPart;
    }
}
