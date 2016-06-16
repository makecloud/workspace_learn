package com.liuyihui.lrn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyihui.lrn.entity.Admin;
import com.liuyihui.lrn.service.AdminService;

@Controller
@RequestMapping(value="/demo")
public class DemoController {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin")
	@ResponseBody
	public String getAllAdmin(Model model){
		List<Admin> list = adminService.getAllAdmin();
		model.addAttribute(list);
		//model娣诲姞鍗曚釜鍙傛暟灞炴�鏃讹紝浼氳皟鐢ㄤ笅闈㈢殑鏂规硶鐢熸垚灞炴�鍚嶏紝鍦‥L琛ㄨ揪寮忛噷鐢ㄨ繖涓敓鎴愮殑鍚�
		System.out.println(Conventions.getVariableName(list));
		model.addAttribute("aaa", list.get(1));
		return "showAdmin";
	}
}
