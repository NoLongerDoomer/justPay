package com.justpay.app.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.justpay.app.dtos.ClientDTO;

public class KeyHelper {

	public static final String RSA_STR = "RSA";

	public static ClientDTO generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_STR);
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return saveKeysInDto(keyPair.getPublic(), keyPair.getPrivate());
	}

	private static ClientDTO saveKeysInDto(PublicKey publicKey, PrivateKey privateKey) {
		ClientDTO dto = new ClientDTO();
		dto.setPrivateKey(base64EncodeToString(privateKey.getEncoded()));
		dto.setPublicKey(base64EncodeToString(publicKey.getEncoded()));
		return dto;
	}

	private static String base64EncodeToString(byte[] toEncodeBytes) {
		return Base64.getEncoder().encodeToString(toEncodeBytes);
	}

	private static byte[] base64DecodeToBytes(String toBeDecoded) {
		return Base64.getDecoder().decode(toBeDecoded);
	}

	public static String encrypt(String data, String publicKeyStr)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		KeyFactory factory = KeyFactory.getInstance(RSA_STR);
		EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeToBytes(publicKeyStr));
		PublicKey publicKey = factory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(RSA_STR);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedBytes = cipher.doFinal(dataBytes);
		return base64EncodeToString(encryptedBytes);
	}

	public static String decrypt(String encryptedData, String privateKeyStr)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		KeyFactory factory = KeyFactory.getInstance(RSA_STR);
		EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeToBytes(privateKeyStr));
		PrivateKey privateKey = factory.generatePrivate(keySpec);

		Cipher cipher = Cipher.getInstance(RSA_STR);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] decryptedData = cipher.doFinal(base64DecodeToBytes(encryptedData));
		return new String (decryptedData, StandardCharsets.UTF_8);

	}

}
