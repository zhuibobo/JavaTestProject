package spring014AppendBeanToContext;

import org.springframework.stereotype.Service;

@Service
public class AppendBean {
    public String getMyName(){
        return "AppendBean";
    }
}
