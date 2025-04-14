package org.example.dailydriver.controller;

import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.service.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthUserController {

    private final AuthUserService userService;

    public AuthUserController(AuthUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserDto> getUser(@PathVariable("id") String id) {
        return ResponseEntity.status(200).body(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthUserDto>> getUsers() {
        return ResponseEntity.status(200).body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<AuthUserDto> createUser(@RequestBody AuthUserCreateDto userDto) {
        return ResponseEntity.status(201).body(userService.save(userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthUserDto> updateUser(@PathVariable("id") String id,
                                                  @RequestBody AuthUserUpdateDto dto) {
        return ResponseEntity.status(200).body(userService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.delete(id));
    }

}
