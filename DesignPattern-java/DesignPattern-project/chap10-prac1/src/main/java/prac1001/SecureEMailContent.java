package prac1001;

public class SecureEMailContent extends EMailContentDecorator {

	public SecureEMailContent(EMailContent emailContent) {
		super(emailContent);
		
	}
	
	public String getContent() {
		String content = super.getContent();
		String externalContent = encrypt(content);
		return externalContent;
	}
	
	private String encrypt(String content) {
		return content + " Encrypted";
	}
	
}
