package prac1001;

public class BasicEMailContent extends EMailContent {
	protected String content;
	
	public BasicEMailContent(String content) {
		super();
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

}
