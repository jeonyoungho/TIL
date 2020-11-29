import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "practice", namespace="com.example.practice", propOrder = {
	    "name",
	    "age",
	    "messages"
})
@SuppressWarnings("restriction")
@XmlRootElement
public class Alice {

	private String name;
	private int age;
	private List<String> familys;

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getMessages() {
		return familys;
	}

	@XmlElementWrapper(name = "messages")
	@XmlElement(name = "message")
	public void setMessages(List<String> messages) {
		this.familys = messages;
	}

	@Override
	public String toString() {

		String result = "";

		result = "[name:" + name + ",age:" + age + ",familys:" + familys + "]";

		return result;
	}
}
