package com.justpay.app.dtos;

public class ClientDTO {

	private String publicKey;
	private String privateKey;
	private long clientId;
	private long keyStorageId;
	private String name;
	private String contact;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getKeyStorageId() {
		return keyStorageId;
	}

	public void setKeyStorageId(long keyStorageId) {
		this.keyStorageId = keyStorageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	@Override
	public String toString() {
		return "ClientDTO [publicKey=" + publicKey + ", privateKey=" + privateKey + ", clientId=" + clientId
				+ ", keyStorageId=" + keyStorageId + ", name=" + name + ", contact=" + contact + "]";
	}

}
