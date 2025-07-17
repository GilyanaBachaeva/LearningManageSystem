package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.GroupDTO;
import com.example.LearningManageSystem.model.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDTO map(GroupEntity group);
    GroupEntity map(GroupDTO groupDTO);
}
