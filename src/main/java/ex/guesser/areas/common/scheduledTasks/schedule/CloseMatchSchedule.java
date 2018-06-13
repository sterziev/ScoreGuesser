package ex.guesser.areas.common.scheduledTasks.schedule;

import ex.guesser.areas.common.scheduledTasks.services.CloseMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloseMatchSchedule {
    private final CloseMatchService closeMatchService;

    @Autowired
    public CloseMatchSchedule(CloseMatchService closeMatchService) {
        this.closeMatchService = closeMatchService;
    }


//    @Scheduled(fixedRate = 600000)
//    public void reportCurrentTime() {
//        this.closeMatchService.closeMatch();
//    }
}
