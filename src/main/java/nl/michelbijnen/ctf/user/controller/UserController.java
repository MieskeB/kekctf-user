package nl.michelbijnen.ctf.user.controller;

import nl.michelbijnen.ctf.user.dto.UserDto;
import nl.michelbijnen.ctf.user.model.User;
import nl.michelbijnen.ctf.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getTeam().getName(), user.getRole());
            userDtos.add(userDto);
        }
        return ResponseEntity.ok(userDtos);
    }
}
