package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.GroupRepository;
import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.exception.GroupNotFoundException;
import com.example.LearningManageSystem.mapper.GroupMapper;
import com.example.LearningManageSystem.model.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupDTO addGroup(GroupDTO groupDTO) {
        log.info("Adding group: {}", groupDTO);
        GroupEntity group = groupMapper.map(groupDTO);
        GroupEntity savedGroup = groupRepository.save(group);
        log.info("Group added successfully: {}", savedGroup);
        return groupMapper.map(savedGroup);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent group with id: {}", id);
            throw new GroupNotFoundException("Group with id " + id + " not found.");
        }
        log.info("Deleting group with id: {}", id);
        groupRepository.deleteById(id);
        log.info("Group with id {} deleted successfully.", id);
    }

    public GroupDTO updateGroup(Long id, GroupDTO groupDTO) {
        log.info("Updating group with id: {}", id);
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Group with id {} not found for update.", id);
                    return new GroupNotFoundException("Group with id " + id + " not found.");
                });

        group.setName(groupDTO.getName());

        GroupEntity updatedGroup = groupRepository.save(group);
        log.info("Groupe with id {} updated successfully: {}", id, updatedGroup);
        return groupMapper.map(updatedGroup);
    }

    public GroupDTO getGroupById(Long id) {
        log.info("Fetching group with id: {}", id);
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Group with id {} not found.", id);
                    return new GroupNotFoundException("Group with id " + id + " not found.");
                });
        return groupMapper.map(group);
    }

    public List<GroupDTO> getAllGroups() {
        log.info("Fetching all groups");
        List<GroupDTO> groups = groupRepository.findAll().stream()
                .map(groupMapper::map)
                .collect(Collectors.toList());
        log.info("Total groups fetched: {}", groups.size());
        return groups;
    }
}
