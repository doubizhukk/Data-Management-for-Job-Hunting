package job.service.impl;

import job.Dao.JobInfoDao;
import job.pojo.JobInfo;
import job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    public void save(JobInfo jobInfo) {
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());

        List<JobInfo> list = this.findJobInfo(param);

        if(list.size() == 0){
            this.jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        Example example = Example.of(jobInfo);
        List list = this.jobInfoDao.findAll(example);
        return list;
    }
}
