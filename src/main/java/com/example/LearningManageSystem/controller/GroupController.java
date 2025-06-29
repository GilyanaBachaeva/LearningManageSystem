package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        GroupDTO group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }
}
