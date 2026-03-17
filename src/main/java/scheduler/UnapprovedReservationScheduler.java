package scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import service.UnapprovedRegistrationService;

@RequiredArgsConstructor
@Service
public class UnapprovedReservationScheduler {
    private final UnapprovedRegistrationService unapprovedRegistrationService;

    @Scheduled(fixedRate = 1800000)
    public void deleteReservations() {
        unapprovedRegistrationService.deleteUnapprovedReservations();
    }
}

