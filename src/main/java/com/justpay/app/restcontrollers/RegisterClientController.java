package com.justpay.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.justpay.app.dtos.ClientDTO;
import com.justpay.app.services.ClientService;


@RestController("/clients")
public class RegisterClientController {
	
	@Autowired
	private ClientService clientService;
	

	@PostMapping("/saveClient")
	public ClientDTO saveClient(@RequestBody ClientDTO dto) {
		return clientService.saveClient(dto);
	}
}
