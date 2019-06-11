package ru.sberbank.dao.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.dao.repository.SuitRepository;
import ru.sberbank.dao.repository.mapper.SuitRowMapper;
import ru.sberbank.domain.entity.Suit;

import java.util.List;

@Transactional
@Repository
public class SuitRepositoryImpl implements SuitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Suit create(Suit suit) {
        String sql = "INSERT INTO SUITS(name, is_developed, weapons_id) VALUES (?, ?, ?)";

        int newSuitId = jdbcTemplate.update(sql, suit.getName(), suit.isDeveloped(), suit.getWeapon().getId());
        suit.setId(newSuitId);
        return suit;
    }

    @Override
    public void update(Suit suit) {
        String sql = "UPDATE SUITS SET name = ?, isDeveloped = ?, weapon_id = ? WHERE id=?;";

        jdbcTemplate.update(suit.getName(), suit.isDeveloped(), suit.getWeapon().getId(), suit.getId());
    }

    @Override
    public Suit findById(int id) {
        String sql = "SELECT id, name, is_developed FROM SUITS WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, new SuitRowMapper(), id);
    }

    @Override
    public List<Suit> findAll() {
        String sql = "SELECT id, name, is_developed FROM SUITS";
        return jdbcTemplate.query(sql, new SuitRowMapper());
    }

    @Override
    public void delete(Suit suit) {
        String sql = "DELETE FROM SUITS WHERE id=?";
        jdbcTemplate.update(sql, suit.getId());
    }
}
