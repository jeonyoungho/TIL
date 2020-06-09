import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class CalcClientEx {
	
	private static void getHandler(String msg) {
		System.out.println(msg);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
				socket = new Socket("localhost", 9999);
				System.out.println("서버와 연결 완료");
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while(true) {
					System.out.println("계산식(빈칸으로 띄어 입력, 예:24 + 42)>>");
					String outputMessage = scanner.nextLine();
					
					if(outputMessage.equalsIgnoreCase("bye")) {
						out.write(outputMessage + "\n");
						out.flush();
						break;
					}
					
					out.write(outputMessage + "\n");
					out.flush();
					
					String inputMessage = in.readLine();
					System.out.println("계산 결과: " + inputMessage);
						
					
				}
			} catch (UnknownHostException e) {
				getHandler(e.getMessage());
			} catch (IOException e) {
				getHandler(e.getMessage());
			}finally {
				try {
					scanner.close();
					if(socket != null)
						socket.close();
					
				} catch (IOException e) {
					getHandler(e.getMessage());
				}
			}
		}
		
	}
}
