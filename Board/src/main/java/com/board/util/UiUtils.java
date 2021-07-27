package com.board.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;

@Controller
public class UiUtils {
	public String showMessageWithRedirect(@RequestParam(value="message", required=false) String message,
			@RequestParam(value="redirectUri",required=false) String redirectUri, 
			@RequestParam(value="method", required=false) Method method,
			@RequestParam(value="params",required=false) Map<String,Object> params,Model model) {
		
		//model: view로 파라미터를 전달할때 사용
		model.addAttribute("message",message);			//사용자에게 전달할 메시지
		model.addAttribute("redirectUri",redirectUri);		//redirect할 uri 주소
		model.addAttribute("method", method);				//http요청 메소드
		model.addAttribute("params", params);				//view로 전달할 파라미터
		return "utils/message-redirect";
	}
}
