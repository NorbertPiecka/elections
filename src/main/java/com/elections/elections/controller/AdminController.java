package com.elections.elections.controller;


import com.elections.elections.model.entity.Elector;
import com.elections.elections.service.ElectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@PreAuthorize("hasRole('ADMIN)")
@RestController
@RequestMapping("/elections/v1/admin/electors")
@RequiredArgsConstructor
public class AdminController {
    private final ElectorService electorService;

    @GetMapping
    public ResponseEntity<Void> checkAdminAccess() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{electorId}/lock")
    public ResponseEntity<Elector> lockElector(@PathVariable Long electorId) {
        Elector elector = electorService.lockElector(electorId);
        return ResponseEntity.ok(elector);
    }

    @PutMapping("{electorId}/unlock")
    public ResponseEntity<Elector> unlockElector(@PathVariable Long electorId) {
        Elector elector = electorService.unlockElector(electorId);
        return ResponseEntity.ok(elector);
    }

    @DeleteMapping("{electorId}/delete")
    public ResponseEntity<Void> deleteElector(@PathVariable Long electorId, Principal principal) {
        try {
            String loginToDelete = electorService.getLoginById(electorId);
            if (principal != null && principal.getName().equals(loginToDelete)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            electorService.deleteElector(electorId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
