package ru.sberbank.dao.repository.mapper;

import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuitRowMapper implements RowMapper<Suit> {

    public Suit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Suit suit = new Suit();
        suit.setId(rs.getInt("id"));
        suit.setName(rs.getString("name"));
        suit.setDeveloped(rs.getBoolean("is_developed"));
        suit.setWeapon(new Weapon());
        suit.getWeapon().setId(rs.getInt("weapons_id"));
        return suit;
    }

}
