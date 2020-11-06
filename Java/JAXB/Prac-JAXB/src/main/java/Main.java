import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		Alice user = setUser();

		try {

			File file = new File("D:\\a.xml");
			
			// JAXBContext 생성
			JAXBContext jaxbContext = JAXBContext.newInstance(Alice.class);
			
			// Java Object로 비직렬화하기 위한 marshalling 생성
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			// Marshalling된 xml표현을 Formatting하기 위한 설정
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// 파일로 마샬링
			jaxbMarshaller.marshal(user, file);
			// 표춘출력으로 마샬링
			//jaxbMarshaller.marshal(user, System.out);
			
			// 언마샬링 수행
			Alice unMarshalledUserObject = (Alice) jaxbContext.createUnmarshaller().unmarshal(file);
			System.out.println("unMarshalledUserObject: " + unMarshalledUserObject);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static Alice setUser() {

		Alice user = new Alice();
		user.setName("XML");
		user.setAge(22);

		List<String> list = new ArrayList<String>();
		list.add("bob");
		list.add("trudy");
		list.add("charile");
		user.setMessages(list);

		return user;
	}

}
