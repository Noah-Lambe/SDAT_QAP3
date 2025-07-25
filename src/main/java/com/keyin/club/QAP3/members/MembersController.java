package com.keyin.club.QAP3.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/members")
public class MembersController {
    @Autowired
    private MembersService membersService;

    @GetMapping
    public ResponseEntity<List<Members>> getAllMembers() {
        try {
            List<Members> members = membersService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Members> getMemberById(@PathVariable long id) {
        try {
            Members member = membersService.getMemberById(id);
            if (member != null) {
                return ResponseEntity.ok(member);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/member_search")
    public ResponseEntity<List<Members>> searchMembers(
            @RequestParam(value = "member_name", required = false) String name,
            @RequestParam(value = "member_email", required = false) String email,
            @RequestParam(value = "member_phone", required = false) String phoneNumber) {

        if ((name == null || name.isEmpty()) &&
                (email == null || email.isEmpty()) &&
                (phoneNumber == null || phoneNumber.isEmpty())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        try {
            List<Members> results = membersService.searchMembers(name, email, phoneNumber);
            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(results);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Members> createMember(@RequestBody Members member) {
        try {
            Members createdMember = membersService.createMember(member);
//            return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Location", "/members/" + createdMember.getId())
                    .body(createdMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Members> updateMember(@PathVariable long id, @RequestBody Members updatedMember) {
        try {
            Members member = membersService.updateMember(id, updatedMember);
            if (member != null) {
                return ResponseEntity.ok(member);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable long id) {
        try {
            membersService.deleteMemberById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
