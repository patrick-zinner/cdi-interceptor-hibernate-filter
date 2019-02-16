package com.pz.task;


import com.pz.task.sub.SubtaskDto;
import lombok.Data;

import java.util.List;

@Data
public class TaskDto {

    private Long id;

    private Long userId;

    private String name;

    private Boolean done;

    private List<SubtaskDto> subtasks;

}
