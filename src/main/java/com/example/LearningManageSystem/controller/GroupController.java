package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @ApiOperation(value = "Add Group", notes = "Creates a new group")
    public ResponseEntity<GroupDTO> addGroup(@ApiParam(value = "Groups data to be added", required = true)
                                             @Valid @RequestBody GroupDTO groupDTO) {
        log.info("Request to add group: {}", groupDTO);
        GroupDTO createdGroup = groupService.addGroup(groupDTO);
        log.info("Group added successfully: {}", createdGroup);
        return ResponseEntity.ok(createdGroup);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Group", notes = "Deletes a group by its ID")
    public ResponseEntity<Void> deleteGroup(@ApiParam(value = "Group ID", required = true)
                                            @PathVariable Long id) {
        log.info("Request to delete group with id: {}", id);
        groupService.deleteGroup(id);
        log.info("Group with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Group", notes = "Updates the information of a group by its ID")
    public ResponseEntity<GroupDTO> updateGroup(@ApiParam(value = "Group ID", required = true) @PathVariable Long id,
                                                @ApiParam(value = "Updated group data", required = true)
                                                @Valid @RequestBody GroupDTO groupDTO) {
        log.info("Request to update group with id: {}", id);
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        log.info("Group with id {} updated successfully: {}", id, updatedGroup);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Group by ID", notes = "Returns information about a group by its ID")
    public ResponseEntity<GroupDTO> getGroupById(@ApiParam(value = "Group ID", required = true)
                                                 @PathVariable Long id) {
        log.info("Request to fetch group with id: {}", id);
        GroupDTO group = groupService.getGroupById(id);
        log.info("Group found: {}", group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @ApiOperation(value = "Get All Groups", notes = "Returns a list of all groups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        log.info("Request to get all groups");
        List<GroupDTO> groups = groupService.getAllGroups();
        log.info("Number of groups found: {}", groups.size());
        return ResponseEntity.ok(groups);
    }
}
