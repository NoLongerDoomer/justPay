package com.justpay.app.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "justpay", name = "key_storage")
public class KeyStorageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
	private ClientEntity clients;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "encrypted_public_key", nullable = false)
	private String publicKey;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "encrypted_private_key", nullable = false)
	private String privateKey;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public ClientEntity getClients() {
		return clients;
	}

	public void setClients(ClientEntity clients) {
		this.clients = clients;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
