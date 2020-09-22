package com.myboard.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
	
	public Map<String,Double> getGeocoding(String address) throws IOException, ParseException, org.json.simple.parser.ParseException {
		//리턴객체생성
		Map<String, Double> resultMap = new HashMap<>(); 
		
		System.out.println("getGeocoding:" + address);
		address = URLEncoder.encode(address, "utf-8");
		//Geocodding api호출
		String api  = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+address;
		URL url = new URL(api);
		HttpURLConnection http =  (HttpURLConnection) url.openConnection();	
		http.setRequestProperty("Content-Type", "application/json");
		http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "f2aihev7t7");
		http.setRequestProperty("X-NCP-APIGW-API-KEY", "knqx3Fw0EApehVuxrAPw34wXejb2ES7GsNLSh7v7");

		http.setRequestMethod("GET");
		http.connect();
		BufferedReader br =
				new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
		
		String rdData;
		while ((rdData = br.readLine())!= null ) {
			//System.out.println(rdData);
			JSONObject object = (JSONObject)new JSONParser().parse(rdData);
			JSONArray array = (JSONArray)object.get("addresses"); //가져온 값이 배열
			for(int i = 0 ;i < array.size(); i++) {
				object = (JSONObject)array.get(i);
				System.out.println(object);
				String roadAddress = (String) object.get("roadAddress");
				System.out.println("도로명:" + roadAddress);
				//x : 경도, y : 위도  파싱 출력
				double x = Float.parseFloat((String)object.get("x"));
				double y = Float.parseFloat((String)object.get("y"));
				System.out.println("x(경도):" + x );
				System.out.println("y(위도):" + y );
				resultMap.put("x", x);
				resultMap.put("y", y);
			}
			
		}
		
		return resultMap;
	}
}
