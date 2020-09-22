package com.myboard.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboard.service.MapService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Resource
	private MapService service;
	

	//�ּҸ� �̿��ؼ� ���浵 ã��
	@RequestMapping("/geocodingFind")
	@ResponseBody
	public Map<String,Double> mapGeocodingFind(@RequestParam String address) throws IOException, ParseException, org.json.simple.parser.ParseException {
		System.out.println(address);
		Map<String,Double> resultMap = service.getGeocoding(address);
		
		return resultMap;
	}
}
