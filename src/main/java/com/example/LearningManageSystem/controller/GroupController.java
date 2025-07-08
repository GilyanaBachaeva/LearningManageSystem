package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> addGroup(@Valid @RequestBody GroupDTO groupDTO) {
        log.info("Request to add group: {}", groupDTO);
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        log.info("Group added successfully: {}", createdGroup);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete group with id: {}", id);
        groupService.deleteGroup(id);
        log.info("Group with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        log.info("Request to update group with id: {}", id);
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        log.info("Group with id {} updated successfully: {}", id, updatedGroup);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        log.info("Request to fetch group with id: {}", id);
        GroupDTO group = groupService.getGroupById(id);
        log.info("Group found: {}", group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        log.info("Request to get all groups");
        List<GroupDTO> groups = groupService.getAllGroups();
        log.info("Number of groups found: {}", groups.size());
        return ResponseEntity.ok(groups);
    }
}
