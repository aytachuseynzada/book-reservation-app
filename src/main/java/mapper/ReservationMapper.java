package mapper;

import dao.entity.ReservationEntity;
import dto.ReservationRequestDto;

public class ReservationMapper {
    public static ReservationEntity mapToEntity(ReservationRequestDto reservationRequestDto) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUserId(reservationRequestDto.getUserId());
        reservationEntity.setBookId(reservationRequestDto.getBookId());
        return reservationEntity;
    }
}
