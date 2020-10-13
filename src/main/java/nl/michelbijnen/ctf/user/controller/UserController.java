package nl.michelbijnen.ctf.user.controller;

import nl.michelbijnen.ctf.user.dto.UserDto;
import nl.michelbijnen.ctf.user.model.Team;
import nl.michelbijnen.ctf.user.model.User;
import nl.michelbijnen.ctf.user.repository.TeamRepository;
import nl.michelbijnen.ctf.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

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

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id does not exist");
        }

        User user = optionalUser.get();
        this.userRepository.delete(user);

        List<User> teamUsers = this.userRepository.getUsersByTeam(user.getTeam());
        if (teamUsers.size() == 0) {
            this.teamRepository.delete(user.getTeam());
        }

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/promote/{userId}")
    public ResponseEntity promoteUser(@PathVariable String userId) {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id does not exist");
        }

        User user = optionalUser.get();
        user.setRole("ROLE_ADMIN");

        this.userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
