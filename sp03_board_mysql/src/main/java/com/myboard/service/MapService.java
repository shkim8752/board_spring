package com.myboard.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

//interface 
public interface MapService  {
	public Map<String,Double> getGeocoding(String address) throws IOException, ParseException, org.json.simple.parser.ParseException; 
}
