
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LogicThread extends Thread {
	private String searchKidsPension = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EA%B0%80%ED%8F%89+%ED%82%A4%EC%A6%88%ED%8E%9C%EC%85%98";
	private String searchAngPang = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%95%99%ED%8C%A1%ED%82%A4%EC%A6%88%ED%92%80%EB%B9%8C%EB%9D%BC&oquery=%EA%B0%80%ED%8F%89+%ED%82%A4%EC%A6%88%ED%8E%9C%EC%85%98&tqi=UjV4ewp0Jy0ssP3D64NssssstoK-028212";
	private String angPangHomepage = "http://enfantpv.kr/";

	private String kidsBlog1_1 = null;
	private String kidsBlog1_2 = null;
	private String angPangBlog2_1 = null;
	private String angPangBlog2_2 = null;

	private int shortTermTime = 1000 * 5;
	private int longTermTime = 1000 * 60 * 90;

	private void setStrings() {
		int indexFirst = (int) (Math.random() * 4 + 1);// 1~4���� ���� �߻�
		int indexSecond = (int) (Math.random() * 4 + 1);// 1~4���� ���� �߻�
		
		switch (indexFirst) {
		case 1:
			kidsBlog1_1 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=26448603&memberNo=25041664&vType=VERTICAL";
			kidsBlog1_2 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=26442277&memberNo=25041664";
			break;
		case 2:
			kidsBlog1_1 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=15756122&memberNo=3265970&vType=VERTICAL";
			kidsBlog1_2 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=15599873&memberNo=3265970";
			break;
		case 3:
			kidsBlog1_1 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=17868466&memberNo=1063900&vType=VERTICAL";
			kidsBlog1_2 = "https://m.post.naver.com/viewer/postView.nhn?volumeNo=17833394&memberNo=1063900";
			break;
		case 4:
			kidsBlog1_1 = "http://www.alliepension.com/";
			kidsBlog1_2 = "http://alliepension.com/main.html";
			break;
		}
		
		switch (indexFirst) {
		case 1:
			angPangBlog2_1 = "https://blog.naver.com/kaggta/221593865322";
			angPangBlog2_2 = "https://blog.naver.com/kaggta";
			break;
		case 2:
			angPangBlog2_1 = "https://blog.naver.com/land-agent/221647220814";
			angPangBlog2_2 = "https://blog.naver.com/land-agent/221648006758";
			break;
		case 3:
			angPangBlog2_1 = "https://blog.naver.com/wjddkdiazz/221547254742";
			angPangBlog2_2 = "https://blog.naver.com/wjddkdiazz/221253362192";
			break;
		case 4:
			angPangBlog2_1 = "https://blog.naver.com/rlagksmf82/221551325870";
			angPangBlog2_2 = "https://blog.naver.com/rlagksmf82/221571287385";
			break;
		}

	}

	public void run() {

		while (true) {
			setStrings();
			
			
			// ���̹� ����Ű����� �˻�
			try {
				Desktop.getDesktop().browse(new URI(searchKidsPension));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ����Ű����� ����Ʈ����
			try {
				Desktop.getDesktop().browse(new URI(kidsBlog1_1));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace

				();
			}

			// ����Ű����� ����Ʈ���� �ٸ� �޴�����
			try {
				Desktop.getDesktop().browse(new URI(kidsBlog1_2));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace

				();
			}

			// ����Ű��Ǯ����˻�
			try {
				Desktop.getDesktop().browse(new URI(searchAngPang));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace

				();
			}

			// ����Ű��Ǯ���� - ���α� ����
			try {
				Desktop.getDesktop().browse(new URI(angPangBlog2_1));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace

				();
			}

			// ����Ű��Ǯ���� - ���α� �� �ٸ� �޴�����
			try {
				Desktop.getDesktop().browse(new URI(angPangBlog2_2));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ���̹� ����Ű��Ǯ����˻�
			try {
				Desktop.getDesktop().browse(new URI(searchAngPang));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(shortTermTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ����Ű��Ǯ���� Ȩ������ ����
			try {
				Desktop.getDesktop().browse(new URI(angPangHomepage));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(longTermTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
