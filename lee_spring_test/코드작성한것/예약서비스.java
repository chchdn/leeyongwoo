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
