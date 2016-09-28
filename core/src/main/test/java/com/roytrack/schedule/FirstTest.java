package com.roytrack.schedule;

import com.taobao.pamirs.schedule.strategy.ScheduleStrategy;
import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import com.taobao.pamirs.schedule.taskmanager.ScheduleTaskType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by roytrack on 2016-09-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*schedule.xml")
public class FirstTest {

    @Autowired
    TBScheduleManagerFactory tBScheduleManagerFactory;

    public void setScheduleManagerFactory(
            TBScheduleManagerFactory tbScheduleManagerFactory) {
        this.tBScheduleManagerFactory = tbScheduleManagerFactory;
    }

    String baseTaskTypeBean="firstTask";
    @Test
    public void showFactory() throws Exception {
        while(this.tBScheduleManagerFactory.isZookeeperInitialSucess()==false){
            Thread.sleep(1000);
        }
        boolean ready=false;

        while (!ready){
            try{
                if(this.tBScheduleManagerFactory.getScheduleDataManager()!=null){
                    ready=true;
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            Thread.sleep(1000);
        }
        tBScheduleManagerFactory.stopServer(null);
        try{
            this.tBScheduleManagerFactory.getScheduleDataManager().deleteTaskType(baseTaskTypeBean);
        }catch (Exception e){

        }
        ScheduleTaskType baseTaskType=new ScheduleTaskType();
        baseTaskType.setBaseTaskType(baseTaskTypeBean);
        baseTaskType.setDealBeanName(baseTaskTypeBean);
        baseTaskType.setHeartBeatRate(2000);
        baseTaskType.setJudgeDeadInterval(10000);
        baseTaskType.setTaskParameter("aaa");
        baseTaskType.setTaskItems(ScheduleTaskType.splitTaskItem("0:{TYPE=A,KIND=1},1:{TYPE=A,KIND=2}"));
        this.tBScheduleManagerFactory.getScheduleDataManager().createBaseTaskType(baseTaskType);
        System.out.println("创建调度任务成功");


        String taskName=baseTaskTypeBean+"$TEST";
        String strategyName=baseTaskTypeBean+"-Strategy";
        try{
            this.tBScheduleManagerFactory.getScheduleStrategyManager().deleteMachineStrategy(strategyName,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        ScheduleStrategy strategy=new ScheduleStrategy();
        strategy.setStrategyName(strategyName);
        strategy.setKind(ScheduleStrategy.Kind.Schedule);
        strategy.setTaskName(taskName);
        strategy.setTaskParameter("abc");

        strategy.setNumOfSingleServer(1);
        strategy.setAssignNum(10);
        strategy.setIPList("127.0.0.1".split(","));
        this.tBScheduleManagerFactory.getScheduleStrategyManager().createScheduleStrategy(strategy);

        System.out.println("创建调度策略成功");
        System.out.println(tBScheduleManagerFactory.start);





    }

}
