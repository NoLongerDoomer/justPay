package com.justpay.app.services.impls;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justpay.app.dtos.ClientDTO;
import com.justpay.app.entities.ClientEntity;
import com.justpay.app.entities.KeyStorageEntity;
import com.justpay.app.repositories.ClientsRepository;
import com.justpay.app.repositories.KeyStorageRepository;
import com.justpay.app.services.ClientService;
import com.justpay.app.utils.KeyHelper;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientsRepository clientsRepository;

	@Autowired
	private KeyStorageRepository keyStorageRepository;

	@Override
	public ClientDTO saveClient(ClientDTO clientDTO) {

		ClientEntity clients = new ClientEntity();
		clients.setContact(clientDTO.getContact());
		clients.setName(clientDTO.getName());

		ClientEntity savedClient = clientsRepository.save(clients);

		ClientDTO generatedKeys = null;

		try {
			generatedKeys = KeyHelper.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		KeyStorageEntity keyStorageEntity = new KeyStorageEntity();
		keyStorageEntity.setClients(savedClient);
		keyStorageEntity.setPrivateKey(generatedKeys.getPrivateKey());
		keyStorageEntity.setPublicKey(generatedKeys.getPublicKey());

		KeyStorageEntity savedKeys = keyStorageRepository.save(keyStorageEntity);

		clientDTO.setClientId(savedClient.getId());
		clientDTO.setPrivateKey(savedKeys.getPrivateKey());
		clientDTO.setPublicKey(savedKeys.getPublicKey());

		return clientDTO;
	}

	@Override
	public ClientDTO getClientById(long id) {
		ClientEntity clients = clientsRepository.getById(id);
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setContact(clients.getContact());
		clientDTO.setName(clients.getName());
		clientDTO.setClientId(clients.getId());
		return clientDTO;
	}

}
