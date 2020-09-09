package job.task;

import job.pojo.JobInfo;
import org.hibernate.sql.Select;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class JobProcessor implements PageProcessor {
    private String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    @Override
    public void process(Page page) {
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        
        if(list.size() == 0){
            this.saveJobeInfo(page);
        }else{
            for(Selectable selectable : list){
                String JobInfoUrl = selectable.links().toString();
                page.addTargetRequest(JobInfoUrl);

            }
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            page.addTargetRequest(bkUrl);
        }

        
    }

    private void saveJobeInfo(Page page) {
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();

        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        jobInfo.setJobAddr(html.css("div.cn span.lname", "text").toString());
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);
        String time = Jsoup.parse(html.css("div.t1 span").regex(".*发布").toString()).text();
        jobInfo.setTime(time.substring(0, time.length()-2));

        System.out.println(jobInfo.toString());
        page.putField("jobInfo", jobInfo);

    }

    private Site site = Site.me()
            .setCharset("gbk")
            .setTimeOut(10000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;
    @Scheduled(initialDelay = 1000, fixedDelay = 100000)
    public void process(){
        Spider.create(new JobProcessor())
            .addUrl(url)
            .addPipeline(new FilePipeline("/Users/kaizhuguo/Desktop/text/"))
            .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
            .thread(5)
            .run();
    }

}
