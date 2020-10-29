import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientEx {

	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner scanner = new Scanner(System.in);
		
		try {
			socket = new Socket("localhost",9999); //서버의 999번 포트에 접
			// 소켓을 만들면서 접속할 아이피주소와 포트를 파라미터로 넘겨 줌
			System.out.println("연결 완료");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //inputstream은 바이트배열 단위이므로 문자열로 처리해줄 InputStreamReader생성
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //outputstream은 바이트배열 단위이므로 문자열로 처리해줄 OutputStreamWriter생성
			
			while(true) {
				System.out.println("보내기>>");
				String outputMessage = scanner.nextLine(); // 엔터키 까지
				if(outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage + "\n");
					out.flush();
					break;
				}
				
				out.write(outputMessage + "\n");
				out.flush();
				
				String inputMessage = in.readLine(); // 서버로 부터 라인 받기
				System.out.println("서버로부터: " + inputMessage);
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				if(socket != null) // socket을 만들다 exception이 발생 하는 경우에는 닫아줄 필요가 없다
					socket.close();
					
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		

	}

}
