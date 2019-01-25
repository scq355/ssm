package com.sangame.datafilter.service.pandect;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FilterDataShowServiceTest {

//    @Autowired
//    private FilterDataShowService filterDataShowService;
//
//    @Test
//    public void getDataCount(){
//        System.out.println("start");
//        List<DataCount> result = filterDataShowService.getDataCount();
//        for (int i = 0; i < result.size(); i++){
//            System.out.println(result.get(i).getProjectName());
//            System.out.println(result.get(i).getCheckCount());
//            System.out.println(result.get(i).getPassManualCount());
//            System.out.println(result.get(i).getPassSysCount());
//            System.out.println(result.get(i).getParticipationCount());
//            System.out.println(result.get(i).getTotalCount());
//        }
//    }
//
//    @Test
//    public void getDataCountStatistic(){
//        System.out.println("start");
//        List<DataCountStatistic> result = filterDataShowService.getDataCountStatistic(0,0);
//        for (int i = 0; i < result.size(); i++){
//            System.out.println(result.get(i).getProjectType());
//            System.out.println(result.get(i).getDateTime());
//            System.out.println(result.get(i).getDataStatus());
//            System.out.println(result.get(i).getCount());
//
//        }
//    }
}
