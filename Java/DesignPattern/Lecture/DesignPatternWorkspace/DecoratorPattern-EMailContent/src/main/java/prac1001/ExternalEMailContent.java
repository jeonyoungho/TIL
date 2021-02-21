package prac1001;

public class ExternalEMailContent extends EMailContentDecorator {

	public ExternalEMailContent(EMailContent emailContent) {
		super(emailContent);
		
	}
	
	public String getContent() {
		String content = super.getContent();
		String externalContent = addDisclaimer(content);
		return externalContent;
	}
	
	private String addDisclaimer(String content) {
		return content + " Company Disclaimer";
	}
	
}
