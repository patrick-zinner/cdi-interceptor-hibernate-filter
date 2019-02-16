package com.pz.task.sub;

import com.pz.task.Task;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Getter
@Setter
@FilterDef(name = "subtaskByUserFilter", defaultCondition = ":userId = userId", parameters = {
        @ParamDef(name = "userId", type = "integer")
})
@Filters({
        @Filter(name = "subtaskByUserFilter"),
})
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private Long userId;

    @Column
    private String name;

    @Column
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
