package com.example.reservation.service;

import com.example.reservation.dto.ReservationDTO;
import java.util.List;

public interface ReservationService {
    // 예약 등록
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    
    // 예약 목록 조회
    List<ReservationDTO> getAllReservations();
    
    // 예약 상세 조회
    ReservationDTO getReservationById(Long id);
}
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

    // DTO -> Entity 변환
    private Reservation dtoToEntity(ReservationDTO reservationDTO) {
        return new Reservation(
                reservationDTO.getDate(),
                reservationDTO.getRoomNumber(),
                reservationDTO.getGuestName(),
                reservationDTO.getGuestPhone()
        );
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

    // 예약 등록
    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = dtoToEntity(reservationDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return entityToDto(savedReservation);
    }

    // 예약 목록 조회
    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // 예약 상세 조회
    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        return entityToDto(reservation);
    }
}
package com.example.reservation.dto;

import java.time.LocalDate;

public class ReservationDTO {

    private Long id; // 예약번호
    private LocalDate date; // 예약일
    private String roomNumber; // 객실번호
    private String guestName; // 예약자 이름
    private String guestPhone; // 예약자 연락처

    // 기본 생성자
    public ReservationDTO() {
    }

    // 모든 필드를 포함하는 생성자
    public ReservationDTO(Long id, LocalDate date, String roomNumber, String guestName, String guestPhone) {
        this.id = id;
        this.date = date;
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", date=" + date +
                ", roomNumber='" + roomNumber + '\'' +
                ", guestName='" + guestName + '\'' +
                ", guestPhone='" + guestPhone + '\'' +
                '}';
    }
}
package com.reservation;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    // 1. 예약 등록 테스트
    @Test
    public void testCreateReservation() {
        ReservationDTO reservationDTO = new ReservationDTO(
                null,
                LocalDate.of(2024, 9, 15),
                "301",
                "홍길동",
                "010-1234-5678"
        );
        ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);
        System.out.println("Saved Reservation: " + savedReservation);
    }

    // 2. 예약 목록 조회 테스트
    @Test
    public void testGetAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        reservations.forEach(System.out::println);
    }

    // 3. 예약 상세 조회 테스트
    @Test
    public void testGetReservationById() {
        Long id = 1L; // 존재하는 예약 ID를 입력
        ReservationDTO reservation = reservationService.getReservationById(id);
        System.out.println("Reservation Details: " + reservation);
    }
}
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
