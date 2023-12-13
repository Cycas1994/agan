package com.cycas.xxljob.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobTest {

    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String s) throws Exception {
        String param = XxlJobHelper.getJobParam();
        log.info("dadasdadadadsaddad------" +param);
        return ReturnT.SUCCESS;
    }

}
