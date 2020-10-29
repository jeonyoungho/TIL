import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {
	
	public static void main(String args[]) {
		ServerSocket listener =null;
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner scanner = new Scanner(System.in);
		try {
			listener = new ServerSocket(9999); // 9999번 포트와 바인딩한다, 애플리케이션이 9999번 포트를 사용하겠다 하고 요청
			System.out.println("연결을 기다리고 있습니다....");
			socket = listener.accept(); // 클라이언트로부터 리턴하지 않고 접속을 기다리고 있음, blocking call
			
			// 소켓에 연결 된 inputstream, outputstream을 통해 주고 받고 한다
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //inputstream은 바이트배열 단위이므로 문자열로 처리해줄 InputStreamReader생성
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //outputstream은 바이트배열 단위이므로 문자열로 처리해줄 OutputStreamWriter생성
			// 소켓으로부터 직접사용하려면 바이트 단위이므로 굉장히 불편함
			// 좀 더 편하게 다른 스트림으로 연결하기 위해 InputStreamReader, OutputStreamWriter를 생성
			// Reader, Writer 붙으면 문자열 단위임
			// out에 쓰고 버퍼에 쌓여서 outputstreamwriter를 통해 바이트형식으로 소켓에 전달
			
			while(true) {
				String inputMessage = in.readLine(); // 서버는 여기서 한 라인을 보내 올 때까지 계속 기다림
				
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트로 부터 bye 메시지 받고 종료함");
					break;
				}
				
				System.out.println("클라이언트로부터: " + inputMessage);
				System.out.print("보내기>>");
				String outputMessage = scanner.nextLine(); // 공백 포함해서 엔터키를 누르기 전까지의 문자열 입력 받음
				out.write(outputMessage + "\n");
				out.flush(); // 버퍼 다 비우기, 버퍼에 있던 메시지들이 클라이언트쪽으로 전달 됨
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				scanner.close();
				socket.close();
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}
}
