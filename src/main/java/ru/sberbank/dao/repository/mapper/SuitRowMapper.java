package ru.sberbank.dao.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.sberbank.domain.entity.Suit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuitRowMapper implements RowMapper<Suit> {

    public Suit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Suit suit = new Suit();
        suit.setId(rs.getInt("id"));
        suit.setName(rs.getString("name"));
        suit.setDeveloped(rs.getBoolean("is_developed"));
        return suit;
    }

}
