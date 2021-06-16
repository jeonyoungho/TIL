import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		Person user = setUser();

		try {
			File file = new File("/Users/jeon-yongho/Desktop/test.xml");
			
			// JAXBContext 생성
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			
			// Java Object로 비직렬화하기 위한 marshalling 생성
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			// Marshalling된 xml표현을 Formatting하기 위한 설정
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// 파일로 마샬링
			jaxbMarshaller.marshal(user, file);
			// 표춘출력으로 마샬링
			//jaxbMarshaller.marshal(user, System.out);
			
			// 언마샬링 수행
			Person unMarshalledUserObject = (Person) jaxbContext.createUnmarshaller().unmarshal(file);
			System.out.println("unMarshalledUserObject: " + unMarshalledUserObject);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static Person setUser() {

		Person user = new Person();
		user.setName("Alice");
		user.setAge(22);

		List<String> friends = new ArrayList<String>();
		friends.add("Bob");
		friends.add("Charlie");
		friends.add("Trudy");
		user.setFriends(friends);

		return user;
	}

}
