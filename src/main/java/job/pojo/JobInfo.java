package job.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private Integer salaryMin;
    private Integer salaryMax;
    private String url;
    private String time;

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public String getUrl() {
        return url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyInfo='" + companyInfo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}