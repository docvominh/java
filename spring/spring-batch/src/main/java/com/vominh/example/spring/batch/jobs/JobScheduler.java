package com.vominh.example.spring.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobScheduler {

    private final JobFactory jobFactory;

    private final JobLauncher jobLauncher;

    public JobScheduler(JobFactory jobFactory, JobLauncher jobLauncher) {
        this.jobFactory = jobFactory;
        this.jobLauncher = jobLauncher;
    }


    @Scheduled(cron = "0 0/1 * * * ?")
    public void simple() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Job j = jobFactory.createReadAndProcessAndWriteJob();
        jobLauncher.run(j, new JobParameters());
    }


}
