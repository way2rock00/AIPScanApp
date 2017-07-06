package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.SpringboardItems;

import java.util.ArrayList;
import java.util.List;

public class SpringboardDC {
    public SpringboardDC() {
        super();
    }
    
    public SpringboardItems[] getSpringBoard(){
        SpringboardItems springboardArray[]= null;
        List<SpringboardItems> springboardList= new ArrayList<SpringboardItems>();
        SpringboardItems item = new SpringboardItems("deloitte.retail.MainMenu","Home","fa fa-home");
        springboardList.add(item);
        item = new SpringboardItems("deloitte.retail.Preference","Preference","fa fa-cog");
        springboardList.add(item);
        item = new SpringboardItems("deloitte.retail.Preference","Logout","fa fa-power-off");
        springboardList.add(item);
        
        springboardArray = springboardList.toArray(new SpringboardItems[springboardList.size()]);
        return springboardArray ;
        
    }
}
