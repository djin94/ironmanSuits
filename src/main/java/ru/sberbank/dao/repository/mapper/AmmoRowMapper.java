package ru.sberbank.dao.repository.mapper;

import ru.sberbank.domain.entity.Ammo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmmoRowMapper implements RowMapper<Ammo> {

    public Ammo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ammo ammo = new Ammo();
        ammo.setId(rs.getInt("id"));
        ammo.setAmount(rs.getInt("amount"));
        ammo.setName(rs.getString("name"));
        ammo.setWeaponId(rs.getInt("weapons_id"));
        return ammo;
    }
}
