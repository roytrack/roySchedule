package com.roytrack.task;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by roytrack on 2016-09-28.
 */

public class FirstTask  implements IScheduleTaskDealSingle<TaskVo>{
    SimpleDateFormat sdf_yyyyMMddHHmmss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public boolean execute(TaskVo task, String ownSign) throws Exception {
        System.out.println("task execute");
        System.out.println(sdf_yyyyMMddHHmmss.format(new Date()));
        return false;
    }

    @Override
    public List selectTasks(String taskParameter, String ownSign, int taskItemNum, List taskItemList, int eachFetchDataNum) throws Exception {
        System.out.println("select task");
        return null;
    }

    @Override
    public Comparator getComparator() {

        return new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1.equals(o2)){
                    return  1;
                }else{
                    return  0;
                }
            }
        };
    }
}
