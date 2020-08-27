package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kr.ac.hansung.exception.UserDuplicatedException;
import kr.ac.hansung.exception.UserNotFoundException;
import kr.ac.hansung.model.User;
import kr.ac.hansung.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private UserService userService;

	// --- Retrive All Users
	// @RequestMapping(value="/users", method=RequestMethod.GET)
	@GetMapping(value = "/users") // http://localhost:8080/helloRest/api/users
	public ResponseEntity<List<User>> listAllUsers() { // status, header, body
		List<User> users = userService.findAllUsers();

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // status
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK); // status, body (json)
	}

	// --- Retrieve single User
	// @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {

		User user = userService.findById(id);

		if (user == null) {

			// to do list: custom exception
			throw new UserNotFoundException(id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// --- Create a User
	// @RequestMapping(value="/users", method=RequestMethod.POST) // request
	// body(json)
	@PostMapping(value = "/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

		if (userService.doesUserExist(user)) {
			// to do list: exception
			throw new UserDuplicatedException(user.getName());
		}

		userService.saveUser(user);

		// 새롭게 만들어진 사용자를 위한 uri를 리턴하는 부분
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED); // status, headers
	}

	// --- Update a User
	// @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	@PutMapping(value = "users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		User currentUser = userService.findById(id);

		if (currentUser == null) {
			// to do list; exception
			throw new UserNotFoundException(id);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);

		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// --- Delete a User
	// @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		User user = userService.findById(id);
		if (user == null) {
			// to do lit: exception
			throw new UserNotFoundException(id);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// --- Delete All Users
	// @RequestMapping(value="/users", method=RequestMethod.DELETE)
	@DeleteMapping(value = "/users")
	public ResponseEntity<Void> deleteAllUser() {

		userService.deleteAllUsers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
