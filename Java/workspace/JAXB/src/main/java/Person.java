import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "person", namespace="com.example", propOrder = {
	    "name",
	    "age",
	    "friends"
})
@SuppressWarnings("restriction")
@XmlRootElement
public class Person {

	private String name;
	private int age;
	private List<String> friends;

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

	public List<String> getFriends() {
		return this.friends;
	}

	@XmlElementWrapper(name = "friends")
	@XmlElement(name = "friends")
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "[name: " + name + ",age: " + age + ",friends: " + friends.toString() + "]";
	}
}
