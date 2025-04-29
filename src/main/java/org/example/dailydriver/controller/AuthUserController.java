package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dailydriver.model.AuthRequest;
import org.example.dailydriver.model.AuthResponse;
import org.example.dailydriver.model.dto.authUserDto.AuthUserCreateDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.dto.authUserDto.AuthUserUpdateDto;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;
import org.example.dailydriver.service.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class AuthUserController {

    private final AuthUserService userService;

    public AuthUserController(AuthUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthUserDto> getUser(@PathVariable("id") String id) {
        return ResponseEntity.status(200).body(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUserDto> createUser(@RequestBody AuthUserCreateDto userDto) {
        return ResponseEntity.status(201).body(userService.save(userDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AuthUserDto> updateUser(@PathVariable("id") String id,
                                                  @RequestBody AuthUserUpdateDto dto) {
        return ResponseEntity.status(200).body(userService.update(dto, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< ", description = " hamma userlar royxatini korish ")
    public ResponseEntity<List<AuthUserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/status/{id}")
    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< ", description = " userga blocklash uchun ")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestParam Status status) {
        userService.changeUserStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @PatchMapping("/role/{id}")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< ", description = " userga huqu berish ")
    public ResponseEntity<Void> updateRole(@PathVariable String id, @RequestParam Role role) {
        userService.changeUserRole(id, role);
        return ResponseEntity.noContent().build();
    }

}
