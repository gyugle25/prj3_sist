package kr.co.sist.prj3.admin.resdetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sist.prj3.admin.resdetail.service.ReservationService;

/**
 * ������Ȳ ��Ʈ�ѷ�
 * @author user
 */ 
@Controller
public class AdminReservationController {
	
	@Autowired(required=false)
	private ReservationService rs;
	
	
	//������Ȳȭ��
	@GetMapping("/reservation_detail.do")
	public String reservationFrm(Model model) {
		
		model.addAttribute("resDeatil",rs.reservationService());
		
		return "reservation_detail/reservation_detail";
	}//reservationFrm
	

}//class
