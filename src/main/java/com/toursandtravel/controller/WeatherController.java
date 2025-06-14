package com.toursandtravel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.toursandtravel.entity.WeatherResponse;



@Controller
public class WeatherController {
	
	@Value("${api.key}")
	private String apiKey;

	@GetMapping("/weather")
	public ResponseEntity<?> getWeather(@RequestParam("city") String city) {
	    String url = String.format(
	            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
	            city, apiKey);
	    RestTemplate restTemplate = new RestTemplate();

	    try {
	        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);

	        if (weatherResponse != null) {
	            return ResponseEntity.ok(weatherResponse); // ✅ return JSON to React
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No weather found for city: " + city);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error retrieving weather for: " + city);
	    }
	}
}
