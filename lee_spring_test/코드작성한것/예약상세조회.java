package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // 예약 목록 조회
    @GetMapping("/reservations")
    public String listReservations(Model model) {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    // 예약 등록 페이지
    @GetMapping("/reservations/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new ReservationDTO());
        return "reservation/form";
    }

    // 예약 등록 처리
    @PostMapping("/reservations")
    public String registerReservation(@ModelAttribute ReservationDTO reservationDTO) {
        reservationService.addReservation(reservationDTO);
        return "redirect:/reservations"; // 등록 후 목록으로 리다이렉트
    }

    // 예약 상세 조회
    @GetMapping("/reservations/{id}")
    public String showReservationDetails(@PathVariable Long id, Model model) {
        ReservationDTO reservationDTO = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservationDTO);
        return "reservation/details"; // 예약 상세 조회 페이지로 이동
    }
}
package com.example.reservation.service;

import com.example.reservation.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations(); // 예약 목록 조회
    void addReservation(ReservationDTO reservationDTO); // 예약 추가
    ReservationDTO getReservationById(Long id); // 예약 상세 조회
}
package com.example.reservation.service.impl;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.entity.Reservation;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setDate(reservationDTO.getDate());
        reservation.setRoomNumber(reservationDTO.getRoomNumber());
        reservation.setGuestName(reservationDTO.getGuestName());
        reservation.setGuestPhone(reservationDTO.getGuestPhone());
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return reservationOptional.map(this::entityToDto).orElse(null); // 없으면 null 반환
    }

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
