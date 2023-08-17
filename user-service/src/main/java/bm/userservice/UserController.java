package bm.userservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user){
        UserEntity savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") Long userId){
        ResponseDTO responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }
}