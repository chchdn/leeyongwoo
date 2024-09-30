package com.example.reservation.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations") // 테이블명 지정
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되는 ID
    private Long id; // 예약번호

    @Column(nullable = false) // 비어있으면 안되는 예약일 컬럼
    private LocalDate date; // 예약일

    @Column(nullable = false) // 비어있으면 안되는 객실번호 컬럼
    private String roomNumber; // 객실번호

    @Column(nullable = false) // 비어있으면 안되는 예약자 이름 컬럼
    private String guestName; // 예약자

    @Column(nullable = false) // 비어있으면 안되는 예약자 연락처 컬럼
    private String guestPhone; // 예약자 연락처

    // 기본 생성자
    public Reservation() {}

    // 생성자
    public Reservation(LocalDate date, String roomNumber, String guestName, String guestPhone) {
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
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", roomNumber='" + roomNumber + '\'' +
                ", guestName='" + guestName + '\'' +
                ", guestPhone='" + guestPhone + '\'' +
                '}';
    }
}
package com.example.reservation.repository;

import com.example.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 추가적인 커스텀 쿼리를 만들 수도 있습니다.
}

package com.reservation;

import com.example.reservation.entity.Reservation;
import com.example.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    // 1. 데이터 추가 테스트
    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation(
                LocalDate.of(2024, 9, 10),
                "101",
                "둘리",
                "010-1111-2222"
        );
        reservationRepository.save(reservation); // 예약 정보 저장
        System.out.println("Reservation saved: " + reservation);
    }

    // 2. 데이터 조회 테스트
    @Test
    public void testFindAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(System.out::println); // 모든 예약 정보 출력
    }

    // 3. 특정 데이터 조회 테스트
    @Test
    public void testFindReservationById() {
        Optional<Reservation> reservation = reservationRepository.findById(1L);
        reservation.ifPresent(System.out::println); // ID가 1인 예약 출력
    }

    // 4. 데이터 수정 테스트
    @Test
    public void testUpdateReservation() {
        Optional<Reservation> reservationOpt = reservationRepository.findById(1L);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setRoomNumber("202"); // 객실번호 수정
            reservationRepository.save(reservation); // 저장
            System.out.println("Updated Reservation: " + reservation);
        }
    }

    // 5. 데이터 삭제 테스트
    @Test
    public void testDeleteReservation() {
        reservationRepository.deleteById(1L); // ID가 1인 예약 삭제
        System.out.println("Reservation deleted");
    }
}
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
