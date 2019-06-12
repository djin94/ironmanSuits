package ru.sberbank.dao.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.dao.repository.WeaponRepository;
import ru.sberbank.dao.repository.mapper.WeaponRowMapper;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;

import java.util.List;

@Transactional
@Repository
public class WeaponRepositoryImpl implements WeaponRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Weapon create(Weapon weapon) {
        String sql = "INSERT INTO WEAPONS(name, capacity_ammo) VALUES (?, ?)";

        int newWeaponId = jdbcTemplate.update(sql, weapon.getName(), weapon.getCapacityAmmo());
        weapon.setId(newWeaponId);
        return weapon;
    }

    @Override
    public void update(Weapon weapon) {
        String sql = "UPDATE WEAPONS SET name = ?, capacity_ammo = ? WHERE id=?;";

        jdbcTemplate.update(sql, weapon.getName(), weapon.getCapacityAmmo(), weapon.getId());
    }

    @Override
    public Weapon findById(int id) {
        String sql = "SELECT id, name, capacity_ammo FROM WEAPONS WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, new WeaponRowMapper(), id);
    }

    @Override
    public List<Weapon> findAll() {
        String sql = "SELECT id, name, capacity_ammo FROM WEAPONS";
        return jdbcTemplate.query(sql, new WeaponRowMapper());
    }

    @Override
    public void delete(Weapon weapon) {
        String sql = "DELETE FROM WEAPONS WHERE id=?";
        jdbcTemplate.update(sql, weapon.getId());
    }

    @Override
    public Weapon findBySuit(Suit suit) {
        String sql = "SELECT id, name, capacity_ammo FROM WEAPONS WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new WeaponRowMapper(), suit.getWeapon().getId());
    }
}
