package com.pz.task;

import com.pz.task.sub.SubtaskDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("task")
@Produces("application/json")
public class TaskEndpoint {

    @Inject
    TaskService taskService;

    @POST
    @Path("")
    public TaskDto create(TaskDto task) {
        return taskService.create(task);
    }

    @PUT
    @Path("")
    public TaskDto update(TaskDto task) {
        return taskService.update(task);
    }

    @GET
    @Path("{id}")
    public TaskDto find(@PathParam("id") Long id) {
        return taskService.find(id);
    }

    @POST
    @Path("{taskId}")
    public SubtaskDto create(@PathParam("taskId") Long taskId, SubtaskDto subtaskDto) {
        return taskService.create(taskId, subtaskDto);
    }

    @GET
    @Path("")
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }
}
