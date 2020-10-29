import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class CalcServerEx {

	private static String calc(String inputMessage) {
		StringTokenizer st = new StringTokenizer(inputMessage);

		if (st.countTokens() != 3)
			return "error";

		String result = "";

		int op1 = Integer.parseInt(st.nextToken());
		String operation = st.nextToken();
		int op2 = Integer.parseInt(st.nextToken());

		switch (operation) {
		case "+": {
			result = Integer.toString(op1 + op2);
			break;
		}
		case "-": {
			result = Integer.toString(op1 - op2);
			break;
		}
		case "*": {
			result = Integer.toString(op1 * op2);
			break;
		}

		default:
			result = "error";
		}

		return result;
	}
	
	private static void getHandler(String msg) {
		System.out.println(msg);
		System.exit(0);
	}

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;

		try {
			listener = new ServerSocket(9999);
			System.out.println("연결을 기다리고 있습니다");
			socket = listener.accept();
			System.out.println("클라이언트와 연결 완료");

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (true) {
				String inputMessage = in.readLine();

				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트로 부터 연결 종료");
					break;
				}
				
				System.out.println("클라이언트의 수식: " + inputMessage);
				String outputMessage = calc(inputMessage);
				
				out.write(outputMessage + "\n");
				out.flush();

			}

		} catch (IOException e) {
			getHandler(e.getMessage());
		}finally {
			
				try {
					if(socket != null)
						socket.close();
					if(listener != null)
						listener.close();
					
				} catch (IOException e) {
					getHandler(e.getMessage());
				}

		}
		
	}

}

