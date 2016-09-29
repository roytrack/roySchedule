package com.roytrack.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by roytrack on 2016-09-28.
 */
@Data
public class TaskVo {
    private String policyName;
    private String policyNo;
    private List<String> departments;
}
