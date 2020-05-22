package kr.ac.hansung;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESWithFile {
	private static final String key = "aesEncryptionKey"; // 16Byte = 128bit
	private static final String initVector = "encryptionIntVec"; // 16Byte (block사이즈와 일치해야함) = 128bit
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	// Base64 : binary를 텍스트로 인코딩하는 Scheme

	public static String encrypt(String value, File file) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); // UTF-8로 바이트로 바꿔서 객체를 생성
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");// UTF-8로 바이트로 바꿔서 객체를 생성

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		// 암호화알고리즘AES , block모드는 CBC모드, 패딩은 PKCS5PADDING로 설정하여 Cipher객체를 하나생
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		// key와 IV를 사용하여 Encrypt모드로 초기화

		byte[] encrypted = cipher.doFinal(value.getBytes());
		// doFinal에서 실제로 암호화 작업이 이뤄짐, plain text를 bytes로 바꿔서 실제로 암호화 수행
		
		String encryptedText = enc.encodeToString(encrypted);
		Files.write(Paths.get(file.getAbsolutePath()),encryptedText.getBytes()); 
		//암호화를 수행한 바이트배열을 파일에 저장 
		return encryptedText;
	}

	public static String decrypt(File file) throws IOException, Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv); // 대칭키이므로 key나 IV는 동일하게 Decrypt모드로 cipher객체 생성

		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath())); // 파일로 부터 바이트배열로 읽어들이기
		
		byte[] original = cipher.doFinal(dec.decode(new String(encoded))); // 실제로 복호화 작업이 이뤄짐
		
		String decryptedText = new String(original);
		return decryptedText;

	}
	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jeon-yongho/Desktop/TIL/Spring/workspace/aesTest/tmp.txt");// 파일 객체생성
		
		String plainText = "한성대학교 컴퓨터공학부는 소프트웨어 기술과 정보통신 기술(ICT)에 대한 심화 교육을 통해"
				+ " 창의적인 소프트웨어 엔지니어를 양성하고 있습니다.";
		System.out.println("Original String to encrypt - " + plainText);
		
		String encryptedString = encrypt(plainText, file);
		System.out.println("Encrypted String - " + encryptedString);
		
		String decryptedString = decrypt(file);
		System.out.println("After decryption - " + decryptedString);
	}
}
