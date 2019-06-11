package ru.sberbank.dao.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.dao.repository.AmmoRepository;
import ru.sberbank.dao.repository.mapper.AmmoRowMapper;
import ru.sberbank.domain.entity.Ammo;

import java.util.List;

@Transactional
@Repository
public class AmmoRepositoryImpl implements AmmoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Ammo create(Ammo ammo) {
        String sql = "INSERT INTO Ammo(amount, name, weapons_id) VALUES (?, ?, ?)";

        int newAmmoId = jdbcTemplate.update(sql, ammo.getAmount(), ammo.getName(), ammo.getWeaponId());
        ammo.setId(newAmmoId);
        return ammo;
    }

    @Override
    public void update(Ammo ammo) {
        String sql = "UPDATE AMMO SET amount = ?, name = ?, weapon_id = ? WHERE id=?;";

        jdbcTemplate.update(sql, ammo.getAmount(), ammo.getName(), ammo.getWeaponId(), ammo.getId());
    }

    @Override
    public Ammo findById(int id) {
        String sql = "SELECT id, amount, name, weapons_id FROM AMMO WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, new AmmoRowMapper(), id);
    }

    @Override
    public List<Ammo> findAll() {
        String sql = "SELECT id, amount, name, weapons_id FROM AMMO";
        return jdbcTemplate.query(sql, new AmmoRowMapper());
    }

    @Override
    public void delete(Ammo ammo) {
        String sql = "DELETE FROM ammo WHERE id=?";
        jdbcTemplate.update(sql, ammo.getId());
    }

    @Override
    public Ammo findByWeaponId(int weaponId) {
        String sql = "SELECT id, amount, name, weapons_id FROM AMMO WHERE weapons_id=?;";
        return jdbcTemplate.queryForObject(sql, new AmmoRowMapper(), weaponId);
    }
}
