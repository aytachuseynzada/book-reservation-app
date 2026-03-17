package service;

import dao.entity.BookEntity;
import dao.entity.ReservationEntity;
import dao.entity.UserEntity;
import dao.repository.BookRepository;
import dao.repository.ReservationRepository;
import dao.repository.UserRepository;
import dto.ReservationRequestDto;
import exceptions.*;
import lombok.RequiredArgsConstructor;
import mapper.ReservationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public void addReservation(ReservationRequestDto reservationRequestDto) {
        var user = fetchUserIfExists(reservationRequestDto.getUserId());

        if (!user.getActive()) {
            throw new UserNotActiveException("USER IS NOT ACTIVE");
        }

        var book = fetchBookIfExists(reservationRequestDto.getBookId());

        if (book.getStock() <= 0) {
            throw new StockNotEnoughException("NO STOCK AVAILABLE");
        }

        long count = reservationRepository.countByUserId(reservationRequestDto.getUserId());

        if (count >= 3){
            throw new ReservationNotEnoughException("USER CANNOT RESERVE MORE THAN 3 BOOKS");
        }

        ReservationEntity reservation = ReservationMapper.mapToEntity(reservationRequestDto);
        reservation.setCreatedAt(LocalDateTime.now());
        reservationRepository.save(reservation);
    }
    @Transactional
    public void approveReservation(Long id) {
        var reservation = fetchReservationIfExist(id);

        var book = fetchBookIfExists(reservation.getBookId());

        if (book.getStock() <= 0) {
            throw new StockNotEnoughException("STOCK NOT ENOUGH");
        }
        book.setStock(book.getStock() - 1);

        reservation.setApprovedAt(LocalDateTime.now());

        bookRepository.save(book);
        reservationRepository.save(reservation);

    }

    private ReservationEntity fetchReservationIfExist(Long id) {
        var reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new ReservationNotEnoughException("RESERVATION NOT FOUND");
        }
        return reservation.get();
    }
    private BookEntity fetchBookIfExists(Long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("BOOK NOT FOUND");
        }
        return book.get();
    }
    private UserEntity fetchUserIfExists(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("USER NOT FOUND");
        }
        return user.get();
    }
}


