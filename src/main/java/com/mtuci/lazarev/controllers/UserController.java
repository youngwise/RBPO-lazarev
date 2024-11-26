package com.mtuci.lazarev.controllers;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.requests.DataUserRequest;
import com.mtuci.lazarev.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DataUserRequest request) {
        try {
            ApplicationUser license = userService.save(request);
            request.setId(license.getId());
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<ApplicationUser> licenses = userService.getAll();
            List<DataUserRequest> data = licenses.stream().map(
                    user -> new DataUserRequest(
                            user.getId(),
                            user.getLogin(),
                            user.getPassword_hash(),
                            user.getEmail(),
                            user.getRole().name()
                    )
            ).toList();
            return ResponseEntity.ok(data);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DataUserRequest request) {
        try {
            userService.update(request);
            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Пользователь удалён");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
