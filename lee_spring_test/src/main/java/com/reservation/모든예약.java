package com.example.reservation.service.impl;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.entity.Reservation;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<ReservationDTO> getAllReservations() {
        // Entity 리스트를 DTO 리스트로 변환
        return reservationRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Entity -> DTO 변환
    private ReservationDTO entityToDto(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getDate(),
                reservation.getRoomNumber(),
                reservation.getGuestName(),
                reservation.getGuestPhone()
        );
    }
}
package com.example.reservation.repository;

import com.example.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
package com.example.reservation.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private Long id;
    private LocalDate date;
    private String roomNumber;
    private String guestName;
    private String guestPhone;

    // 생성자, getter, setter 생략
}
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
