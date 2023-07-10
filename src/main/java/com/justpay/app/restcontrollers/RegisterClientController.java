package com.justpay.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justpay.app.dtos.ClientDTO;
import com.justpay.app.services.ClientService;


@RestController
@RequestMapping("/clients")
public class RegisterClientController {
	
	@Autowired
	private ClientService clientService;
	

	@PostMapping("/saveClient")
	public ClientDTO saveClient(@RequestBody ClientDTO dto) {
		return clientService.saveClient(dto);
	}
	
	@GetMapping("/getClientById/{id}")
	public ClientDTO getClientById(@PathVariable("id") long id) {
		return clientService.getClientById(id);
	}
}
