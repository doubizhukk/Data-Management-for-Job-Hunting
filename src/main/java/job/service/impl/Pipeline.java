package job.service.impl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

public interface Pipeline {
    public void process(ResultItems resultItems, Task task);
}
