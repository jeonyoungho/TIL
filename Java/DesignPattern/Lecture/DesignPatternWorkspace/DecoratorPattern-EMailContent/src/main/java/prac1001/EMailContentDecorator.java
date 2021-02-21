package prac1001;

public abstract class EMailContentDecorator extends EMailContent {
	
	private EMailContent emailContent;
	
	
	public EMailContentDecorator(EMailContent emailContent) {
		super();
		this.emailContent = emailContent;
	}


	public String getContent() {
		return emailContent.getContent();
	}
}
