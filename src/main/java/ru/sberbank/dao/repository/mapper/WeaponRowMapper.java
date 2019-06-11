package ru.sberbank.dao.repository.mapper;

import ru.sberbank.domain.entity.Weapon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeaponRowMapper implements RowMapper<Weapon> {
    @Override
    public Weapon mapRow(ResultSet resultSet, int i) throws SQLException {
        Weapon weapon = new Weapon();
        weapon.setId(resultSet.getInt("id"));
        weapon.setName(resultSet.getString("name"));
        weapon.setCapacityAmmo(resultSet.getInt("capacity_ammo"));
        return weapon;
    }
}
