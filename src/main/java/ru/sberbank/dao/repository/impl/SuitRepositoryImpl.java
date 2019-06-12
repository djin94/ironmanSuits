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
        String sql = "INSERT INTO SUITS(name, is_developed) VALUES (?, ?, ?)";
        int newSuitId = jdbcTemplate.update(sql, suit.getName(), suit.isDeveloped());
        suit.setId(newSuitId);
        suit.getSuitParts().forEach(suitPart -> createRelationBetweenSuitAndSuitPart(suit.getId(), suitPart.getId()));
        return suit;
    }

    @Override
    public void update(Suit suit) {
        String sql = "UPDATE SUITS SET name = ?, isDeveloped = ? WHERE id=?;";

        jdbcTemplate.update(sql, suit.getName(), suit.isDeveloped(), suit.getId());
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

    @Override
    public List<Integer> findSuitPartsIdsForSuit(Suit suit) {
        String sql = "SELECT suit_parts_id FROM suits_suit_parts WHERE suits_id = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> resultSet.getInt("suit_parts_id"), suit.getId());
    }

    @Override
    public Suit findByName(String name) {
        String sql = "SELECT id, name, is_developed FROM SUITS WHERE name=?;";
        return jdbcTemplate.queryForObject(sql, new SuitRowMapper(), name);
    }

    private void createRelationBetweenSuitAndSuitPart(int suitId, int suitPartId) {
        String sql = "INSERT INTO suits_suit_parts(suits_id, suit_parts_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, suitId, suitPartId);
    }
}
