package org.example.dailydriver.controller;

import lombok.RequiredArgsConstructor;
import org.example.dailydriver.model.dto.authUserDto.AuthUserDto;
import org.example.dailydriver.model.enums.Role;
import org.example.dailydriver.model.enums.Status;
import org.example.dailydriver.service.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AuthUserService adminUserService;

    @GetMapping
    public ResponseEntity<List<AuthUserDto>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestParam Status status) {
        adminUserService.changeUserStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Void> updateRole(@PathVariable String id, @RequestParam Role role) {
        adminUserService.changeUserRole(id, role);
        return ResponseEntity.noContent().build();
    }

}
