package kr.ac.hansung;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static final String key = "aesEncryptionKey"; //16Byte = 128bit
	private static final String initVector = "encryptionIntVec"; //16Byte (block사이즈와 일치해야함) = 128bit
	private static final Base64.Encoder enc = Base64.getEncoder(); 
	private static final Base64.Decoder dec = Base64.getDecoder();
	
	public static String encrypt(String value) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8")); //UTF-8로 바이트로 바꿔서 객체를 생성
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");//UTF-8로 바이트로 바꿔서 객체를 생성
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        //암호화알고리즘AES , block모드는 CBC모드, 패딩은 PKCS5PADDING로 설정하여 Cipher객체를 하나생
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	        //key와 IV를 사용하여 Encrypt모드로 초기화
	 
	        byte[] encrypted = cipher.doFinal(value.getBytes());
	        //doFinal에서 실제로 암호화 작업이 이뤄짐, plain text를 bytes로 바꿔서 실제로 암호화 수행 
	        return enc.encodeToString(encrypted);
	        //암호문을 Base64인코딩으로해서 리
	        //Base64 : binary를 텍스트로 인코딩하는 Scheme 
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	public static String decrypt(String encrypted) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        //대칭키이므로 key나 IV는 동일하게 Decrypt모드로 cipher객체 생성
	        byte[] original = cipher.doFinal(dec.decode(encrypted));
	 
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	 
	    return null;
	}

	
	public static void main(String[] args) {
	    String plainText = "한성대학교 컴퓨터공학부는 소프트웨어 기술과 정보통신 기술(ICT)에 대한 심화 교육을 통해"
	    		+ " 창의적인 소프트웨어 엔지니어를 양성하고 있습니다. 구체적으로 문제 해결 능력과 시스템 구축 능력을 갖추고 "
	    		+ "최신 ICT 기술 트랜드를 활용할 수 있는 인재를 양성하고 있습니다. 이를 위해서 컴퓨터공학의 전반적인 이론과 "
	    		+ "프로그래밍 교육을 수행한 후, 산업체와의 협업을 통한 실무 프로젝트 기반 교육을 진행합니다. 소프트웨어와 "
	    		+ "컴퓨터 관련 기술은 사회 전반에 걸쳐 필수적인 기초 기술로 자리 잡고 있습니다. 따라서 한성대학교 컴퓨터 공학부"
	    		+ " 졸업생은 소프트웨어 및 컴퓨터 시스템을 다루는 모든 산업 분야에 취업해 활약하고 있습니다.";
	    System.out.println("Original String to encrypt - " + plainText);
	    
	    String encryptedString = encrypt(plainText);
	    System.out.println("Encrypted String - " + encryptedString);
	    
	    String decryptedString = decrypt(encryptedString);
	    System.out.println("After decryption - " + decryptedString);
	}

}

