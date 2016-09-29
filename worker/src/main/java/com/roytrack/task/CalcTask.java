package com.roytrack.task;

import com.roytrack.vo.TaskVo;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by roytrack on 2016-09-29.
 */
@Service("calcTask")
public class CalcTask implements IScheduleTaskDealSingle<TaskVo> {
    Logger logger= LoggerFactory.getLogger(com.roytrack.task.CalcTask.class);
    AtomicInteger i=new AtomicInteger(0);
    @Override
    public boolean execute(TaskVo task, String ownSign) throws Exception {
        logger.info("this is process ################################## "+i);
        return true;
    }

    @Override
    public List<TaskVo> selectTasks(String taskParameter, String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        logger.info(" invoke select tasks");
        List<TaskVo> tasks=new ArrayList<>();
        TaskVo v=new TaskVo();
        v.setPolicyNo(i.toString());
        i.incrementAndGet();
        tasks.add(v);
        return tasks;
    }

    @Override
    public Comparator<TaskVo> getComparator() {
        return new Comparator<TaskVo>() {
            @Override
            public int compare(TaskVo o1, TaskVo o2) {
                Integer i1=Integer.valueOf(o1.getPolicyNo());
                Integer i2=Integer.valueOf(o2.getPolicyNo());
                logger.info("o1 "+o1+"   o2 "+o2 +" compare result "+i1.compareTo(i2));
                return i1.compareTo(i2);
            }
        };

    }
}
