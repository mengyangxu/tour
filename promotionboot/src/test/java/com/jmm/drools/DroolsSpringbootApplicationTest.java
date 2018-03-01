package com.jmm.drools;

import com.jmm.drools.bean.ApproveSubPromotion;
import com.jmm.drools.bean.Promotion;
import com.jmm.drools.bean.SubPromotion;
import com.jmm.drools.common.DroolsConst;
import com.jmm.drools.common.exception.JsonResponse;
import com.jmm.drools.dao.RulesDao;
import com.jmm.drools.dao.SupplierInventoryDao;
import com.jmm.drools.service.PromotionService;
import com.jmm.drools.vo.ApprovePromotionVo;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;


public class DroolsSpringbootApplicationTest {


	@Test
	public void contextLoads() {
		/*String millis = String.valueOf(System.currentTimeMillis());
		String nano = String.valueOf(System.nanoTime());
		Calendar Cld = Calendar.getInstance();

		int YY = Cld.get(Calendar.YEAR) ;
		int MM = Cld.get(Calendar.MONTH)+1;
		int DD = Cld.get(Calendar.DATE);
		int HH = Cld.get(Calendar.HOUR_OF_DAY);
		int mm = Cld.get(Calendar.MINUTE);
		int SS = Cld.get(Calendar.SECOND);
		int MI = Cld.get(Calendar.MILLISECOND);
		String curTime = YY + MM + DD + HH + mm + SS + MI+"";
*/
		String a = "6972924963674http-nio-8080-exec-3";
		String name = a;
		String name1 = name.substring(0,name.length()-1);
		String name2 = name.substring(name.length()-1);


	}

}
