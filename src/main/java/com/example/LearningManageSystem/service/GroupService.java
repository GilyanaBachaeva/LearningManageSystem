package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.GroupRepository;
import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.exception.GroupNotFoundException;
import com.example.LearningManageSystem.mapper.GroupMapper;
import com.example.LearningManageSystem.model.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupDTO addGroup(GroupDTO groupDTO) {
        GroupEntity group = groupMapper.map(groupDTO);
        GroupEntity savedGroup = groupRepository.save(group);
        return groupMapper.map(savedGroup);
    }

    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException("Group with id " + id + " not found.");
        }
        groupRepository.deleteById(id);
    }

    public GroupDTO updateGroup(Long id, GroupDTO groupDTO) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with id " + id + " not found."));

        group.setName(groupDTO.getName());

        GroupEntity updatedGroup = groupRepository.save(group);
        return groupMapper.map(updatedGroup);
    }

    public GroupDTO getGroupById(Long id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with id " + id + " not found."));
        return groupMapper.map(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::map)
                .collect(Collectors.toList());
    }
}
