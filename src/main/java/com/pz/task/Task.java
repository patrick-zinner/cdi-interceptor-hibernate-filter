package com.pz.task;

import com.pz.task.sub.Subtask;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@FilterDef(name = "taskByUserFilter", defaultCondition = ":userId = userId", parameters = {
        @ParamDef(name = "userId", type = "integer")
})
@Filters({
        @Filter(name = "taskByUserFilter"),
})
public class Task {

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

    @OneToMany(mappedBy = "task")
    @Cascade(CascadeType.PERSIST)
    @Filters({
            @Filter(name = "subtaskByUserFilter")
    })
    private Set<Subtask> subtasks = new HashSet<>();

    public void add(Subtask st) {
        subtasks.add(st);
        st.setTask(this);
    }
}
