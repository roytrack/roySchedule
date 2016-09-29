package com.roytrack.init;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by roytrack on 2016-09-29.
 */
@Component
public class ScheduleInitUtil implements InitializingBean,ApplicationContextAware {
    Logger logger= LoggerFactory.getLogger(com.roytrack.init.ScheduleInitUtil.class);

    private ApplicationContext context;

    @Autowired
    private TBScheduleManagerFactory factory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.context=applicationContext;


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        factory.setApplicationContext(context);
        logger.info("任务调度启动成功！");
    }
}
