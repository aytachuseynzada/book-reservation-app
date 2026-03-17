package controller;

import dao.entity.ReservationEntity;
import dao.repository.ReservationRepository;
import dto.ReservationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.ReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
         reservationService.addReservation(reservationRequestDto);
     }

    @GetMapping("/{id}")
    public void approveReservation(@PathVariable Long id){
        reservationService.approveReservation(id);
    }
}
