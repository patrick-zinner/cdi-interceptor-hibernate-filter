package com.pz.task.sub;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubtaskDto {

    private Long id;

    private Long userId;

    private String name;

    private Boolean done;


}
