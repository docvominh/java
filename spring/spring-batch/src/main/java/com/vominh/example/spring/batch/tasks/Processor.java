package com.vominh.example.spring.batch.tasks;

import com.vominh.example.spring.batch.dto.PopulationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<PopulationDto, PopulationDto> {
    private Logger logger = LoggerFactory.getLogger(Processor.class);

    @Override
    public PopulationDto process(PopulationDto populationDto) throws Exception {
        // Do nothing, just log information
        logger.info(populationDto.toString());
        return populationDto;
    }


}
