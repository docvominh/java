package com.vominh.example.spring.batch.tasks;

import com.vominh.example.spring.batch.dto.PopulationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Writer implements ItemWriter<PopulationDto> {

    final JdbcTemplate jdbcTemplate;

    public Writer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void write(List<? extends PopulationDto> list) throws Exception {
        jdbcTemplate.execute("DELETE FROM population");

        for (PopulationDto p : list) {
            jdbcTemplate.update("INSERT INTO population VALUES (?,?,?)", p.getYear(), p.getNation(), p.getPopulation());
        }
    }
}
