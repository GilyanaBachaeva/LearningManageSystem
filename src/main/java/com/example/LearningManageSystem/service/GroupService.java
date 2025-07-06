package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.GroupRepository;
import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.exception.GroupNotFoundException;
import com.example.LearningManageSystem.mapper.GroupMapper;
import com.example.LearningManageSystem.model.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public GroupDTO addGroup(GroupDTO groupDTO) {
        logger.info("Adding group: {}", groupDTO);
        GroupEntity group = groupMapper.map(groupDTO);
        GroupEntity savedGroup = groupRepository.save(group);
        logger.info("Group added successfully: {}", savedGroup);
        return groupMapper.map(savedGroup);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existent group with id: {}", id);
            throw new GroupNotFoundException("Group with id " + id + " not found.");
        }
        logger.info("Deleting group with id: {}", id);
        groupRepository.deleteById(id);
        logger.info("Group with id {} deleted successfully.", id);
    }

    public GroupDTO updateGroup(Long id, GroupDTO groupDTO) {
        logger.info("Updating group with id: {}", id);
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Group with id {} not found for update.", id);
                    return new GroupNotFoundException("Group with id " + id + " not found.");
                });

        group.setName(groupDTO.getName());

        GroupEntity updatedGroup = groupRepository.save(group);
        logger.info("Groupe with id {} updated successfully: {}", id, updatedGroup);
        return groupMapper.map(updatedGroup);
    }

    public GroupDTO getGroupById(Long id) {
        logger.info("Fetching group with id: {}", id);
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Group with id {} not found.", id);
                    return new GroupNotFoundException("Group with id " + id + " not found.");
                });
        return groupMapper.map(group);
    }

    public List<GroupDTO> getAllGroups() {
        logger.info("Fetching all groups");
        List<GroupDTO> groups = groupRepository.findAll().stream()
                .map(groupMapper::map)
                .collect(Collectors.toList());
        logger.info("Total groups fetched: {}", groups.size());
        return groups;
    }
}
