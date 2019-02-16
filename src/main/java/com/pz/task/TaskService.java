package com.pz.task;

import com.pz.EnableUserFilter;
import com.pz.task.sub.Subtask;
import com.pz.task.sub.SubtaskDao;
import com.pz.task.sub.SubtaskDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
@EnableUserFilter
public class TaskService {

    @Inject
    TaskDao taskDao;

    @Inject
    SubtaskDao subtaskDao;

    public TaskDto create(TaskDto taskDto) {
        Task task = convert(taskDto);
        return convert(taskDao.create(task));
    }

    public TaskDto update(TaskDto taskDto) {
        Task task = taskDao.findById(taskDto.getId());
        return convert(taskDao.create(task));
    }

    public TaskDto find(Long id) {
        return convert(taskDao.findById(id));
    }

    public List<TaskDto> findAll() {
        return taskDao.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    public SubtaskDto create(Long taskId, SubtaskDto subtaskDto) {
        Task task = taskDao.findById(taskId);
        Subtask subtask = convert(subtaskDto);
        task.add(subtask);
        return convert(subtaskDao.create(subtask));
    }

    private Subtask convert(SubtaskDto dto) {
        return convert(new Subtask(), dto);
    }

    private Subtask convert(Subtask entity, SubtaskDto dto) {
        entity.setDone(dto.getDone());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUserId(dto.getUserId());
        return entity;
    }

    private SubtaskDto convert(Subtask entity) {
        if (entity == null) {
            return null;
        }
        SubtaskDto dto = new SubtaskDto();
        dto.setDone(entity.getDone());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUserId(entity.getUserId());
        return dto;
    }


    private Task convert(TaskDto dto) {
        return convert(new Task(), dto);
    }

    private Task convert(Task entity, TaskDto dto) {
        entity.setDone(dto.getDone());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUserId(dto.getUserId());
        return entity;
    }

    private TaskDto convert(Task entity) {
        if (entity == null) {
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setDone(entity.getDone());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUserId(entity.getUserId());
        dto.setSubtasks(entity.getSubtasks().stream().map(this::convert).collect(Collectors.toList()));
        return dto;
    }

}
