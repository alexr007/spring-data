package app.controller;

import app.entity.Person;
import app.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final UserInterface userService;

  public UserController(
      @Autowired
      @Qualifier("UserService_V2")
          UserInterface service) {
    this.userService = service;
  }

  @GetMapping("/users")
  public List<Person> get_all_users() {
    return userService.get();
  }

  // we use `@PathVariable` when variable is the part of the PATH
  @GetMapping("/users/add/{name}")
  public List<Person> add1(@PathVariable("name") String name) {
    userService.add(name);
    return userService.get();
  }

  // we use `@PathParam` when variable is passed via /users/add?name=Jim
  @RequestMapping(method = RequestMethod.POST, path = "/users/add")
  public List<Person> add2(@RequestParam("name") String name) {
    userService.add(name);
    return userService.get();
  }

}
