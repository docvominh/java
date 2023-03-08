package com.vominh.example.spring.batch.jobs;

import com.vominh.example.spring.batch.config.JobCompletionNotificationListener;
import com.vominh.example.spring.batch.dto.PopulationDto;
import com.vominh.example.spring.batch.tasks.Processor;
import com.vominh.example.spring.batch.tasks.Reader;
import com.vominh.example.spring.batch.tasks.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class JobFactory {
    public final JdbcTemplate jdbcTemplate;
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    public JobFactory(JdbcTemplate jdbcTemplate, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;

    }

    public Job createReadAndProcessAndWriteJob() {

        var r = new Reader();
        var p = new Processor();
        var w = new Writer(jdbcTemplate);


        var step1 = stepBuilderFactory.get("step1")
                .<PopulationDto, PopulationDto>chunk(10)
                .reader(r)
                .processor(p)
                .writer(w)
                .build();

        return jobBuilderFactory.get("readAndProcessAndWriteJob" + new Random().nextInt(10000))
                .incrementer(new RunIdIncrementer())
                .listener(new JobCompletionNotificationListener())
                .flow(step1)
                .end()
                .build();
    }

}
