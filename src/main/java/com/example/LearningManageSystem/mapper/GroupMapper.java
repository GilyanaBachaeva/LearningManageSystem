package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.model.Group;

public interface GroupMapper {
    GroupDTO map(Group group);
    Group map(GroupDTO groupDTO);
}
