package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    final GroupService groupService;
    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @PostMapping
    public ResponseEntity<GroupDTO> addGroup(@Valid @RequestBody GroupDTO groupDTO) {
        logger.info("Request to add group: {}", groupDTO);
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        logger.info("Group added successfully: {}", createdGroup);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        logger.info("Request to delete group with id: {}", id);
        groupService.deleteGroup(id);
        logger.info("Group with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        logger.info("Request to update group with id: {}", id);
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        logger.info("Group with id {} updated successfully: {}", id, updatedGroup);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        logger.info("Request to fetch group with id: {}", id);
        GroupDTO group = groupService.getGroupById(id);
        logger.info("Group found: {}", group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        logger.info("Request to get all groups");
        List<GroupDTO> groups = groupService.getAllGroups();
        logger.info("Number of groups found: {}", groups.size());
        return ResponseEntity.ok(groups);
    }
}
