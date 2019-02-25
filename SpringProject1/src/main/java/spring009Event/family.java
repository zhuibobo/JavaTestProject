package spring009Event;

import org.springframework.context.ApplicationContext;

/**
 * Created by zhuangrb on 2017/12/5.
 */
public class family  {
    public String getName(){
        return "alex4D";
    }

    ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void AddMenber(){
        this.applicationContext.publishEvent(new familyAddMemberEvent(this));
    }
}
