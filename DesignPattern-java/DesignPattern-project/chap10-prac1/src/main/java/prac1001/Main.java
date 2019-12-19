package prac1001;

public class Main {

	public static void main(String[] args) {
		EMailContent basicEMailContent = new BasicEMailContent("this is a email");
		System.out.println(basicEMailContent.getContent());
		
		EMailContent secureEMailContent = new SecureEMailContent(basicEMailContent);
		System.out.println(secureEMailContent.getContent());
		
		EMailContent externalEMailContent = new ExternalEMailContent(secureEMailContent);
		System.out.println(externalEMailContent.getContent());
		

	}

}
