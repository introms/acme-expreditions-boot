package it.itresources.demo.acme.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

	@GetMapping("")
	public String info() {
		return "Acme Expeditions Service - Boot version";
	}
	
}
