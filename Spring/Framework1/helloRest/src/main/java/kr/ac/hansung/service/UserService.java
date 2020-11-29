package kr.ac.hansung.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import kr.ac.hansung.model.User;

@Service
public class UserService {
	private static final AtomicLong counter = new AtomicLong();

	// 세 개의 request가 들어오 Tomcat에서 스레드 세 개를 만들어 이 request를 처리하는데
	// 이때 상호배제문제(M.E)를 해결해주기 위해 AtomicLong을 사용함

	private static List<User> users;

	public UserService() {
		users = new ArrayList<User>();

		users.add(new User(counter.incrementAndGet(), "Alice", 30, 70000));
		// AtomicLong의 초깃값이 0이고 불릴때마다 1증가 시키고 그값을 가져와서 id로 사용
		users.add(new User(counter.incrementAndGet(), "Bob", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Charile", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Danile", 50, 40000));
	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	public User findByName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name))
				return user;
		}
		return null;
	}

	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean doesUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

}
