package job.task;

import job.pojo.JobInfo;
import job.service.JobInfoService;
import job.service.impl.Pipeline;
import org.apache.catalina.Container;
import org.apache.catalina.Valve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.Set;

@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task){
        JobInfo jobInfo = resultItems.get("jobInfo");

        if(jobInfo != null)
            this.jobInfoService.save(jobInfo);
    }


}
