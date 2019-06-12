package ru.sberbank.dao.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.dao.repository.SuitPartRepository;
import ru.sberbank.dao.repository.mapper.SuitPartRowMapper;
import ru.sberbank.domain.entity.SuitPart;

import java.util.List;

@Transactional
@Repository
public class SuitPartRepositoryImpl implements SuitPartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SuitPart create(SuitPart suitPart) {
        String sql = "INSERT INTO SUIT_PARTS(name) VALUES (?)";

        int newSuitPartId = jdbcTemplate.update(sql, suitPart.getName());
        suitPart.setId(newSuitPartId);
        return suitPart;
    }

    @Override
    public void update(SuitPart suitPart) {
        String sql = "UPDATE SUIT_PARTS SET name = ? WHERE id=?;";

        jdbcTemplate.update(sql, suitPart.getName());
    }

    @Override
    public SuitPart findById(int id) {
        String sql = "SELECT id, name FROM SUIT_PARTS WHERE id=?;";
        return jdbcTemplate.queryForObject(sql, new SuitPartRowMapper(), id);
    }

    @Override
    public List<SuitPart> findAll() {
        String sql = "SELECT id, name FROM SUIT_PARTS";
        return jdbcTemplate.query(sql, new SuitPartRowMapper());
    }

    @Override
    public void delete(SuitPart suitPart) {
        String sql = "DELETE FROM SUIT_PARTS WHERE id=?";
        jdbcTemplate.update(sql, suitPart.getId());
    }
}
