package com.toursandtravel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/maps")
@CrossOrigin(origins = "*")
public class GoogleMapController {
	@Value("${google.maps.api.key}")
	private String apiKey;

	private final RestTemplate restTemplate = new RestTemplate();

	// Example: Get coordinates from address
	@GetMapping("/geocode")
	public ResponseEntity<String> getCoordinates(@RequestParam String address) {
	    String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
	    String response = restTemplate.getForObject(url, String.class);
	    return ResponseEntity.ok(response);
	}

	// Example: Get address from coordinates
	@GetMapping("/reverse-geocode")
	public ResponseEntity<String> reverseGeocode(@RequestParam double lat, @RequestParam double lng) {
	    String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + apiKey;
	    String response = restTemplate.getForObject(url, String.class);
	    return ResponseEntity.ok(response);
	}
}
