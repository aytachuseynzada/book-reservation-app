package service;

import dao.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UnapprovedRegistrationService {
    private final ReservationRepository reservationRepository;
    public void deleteUnapprovedReservations(){
        var time = LocalDateTime.now().minusHours(2);
        var reservations = reservationRepository.findByCreatedAtBefore(time);
        reservationRepository.deleteAll(reservations);
    }
}
