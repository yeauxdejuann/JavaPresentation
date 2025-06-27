    package com.example.SongStreamAPI;
    
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.scheduling.annotation.Scheduled;
    import org.springframework.stereotype.Component;

import com.example.SongStreamAPI.SongService;

import java.time.LocalDateTime;
    
    @Component // Marks this class as a Spring component, so it's picked up for scheduling
    public class SongScheduler {
    
        private static final Logger logger = LoggerFactory.getLogger(SongScheduler.class);
    
        private final SongService songService;
    
        @Autowired
        public SongScheduler() {
            this.songService = songService;
        }
    
        /**
         * This method will run every 10 seconds (10000 milliseconds).
         * fixedRate ensures the method is invoked every 10 seconds, regardless of how long the previous execution took.
         * If an execution takes longer than the fixedRate, the next execution will start immediately after the previous one finishes.
         */
        @Scheduled(fixedRate = 10000) // Runs every 10 seconds (10,000 milliseconds)
        public void reportSongCount() {


            long count = songService.getAllSongs().size(); // Get current product count
            logger.info("SongScheduler - [{}]: Current song count: {}", LocalDateTime.now(), count);
        }
    
        /**
         * 
         * 
         * 
         * Optional: Example of fixedDelay. Runs 15 seconds after the PREVIOUS task COMPLETES.
         * Useful if you need a guaranteed delay between task executions.
         */
        // @Scheduled(fixedDelay = 15000) // Runs 15 seconds AFTER previous run finishes
        // public void cleanupTask() {
        //     logger.info("ProductScheduler - [{}]: Running cleanup task...", LocalDateTime.now());
        //     // Simulate a task that takes some time
        //     try {
        //         Thread.sleep(5000); // Sleep for 5 seconds
        //     } catch (InterruptedException e) {
        //         Thread.currentThread().interrupt();
        //     }
        //     logger.info("ProductScheduler - [{}]: Cleanup task finished.", LocalDateTime.now());
        // }
    
        /**
         * Optional: Example of a cron expression.
         * This example runs every minute at 0 seconds (e.g., 00:00, 01:00, 02:00, etc.)
         * Cron format: second, minute, hour, day of month, month, day of week.
         */
        // @Scheduled(cron = "0 * * * * *") // Every minute at the 0-second mark
        // public void monthlyReportGeneration() {
        //     logger.info("ProductScheduler - [{}]: Running a cron-scheduled task (every minute example).", LocalDateTime.now());
        // }
    }