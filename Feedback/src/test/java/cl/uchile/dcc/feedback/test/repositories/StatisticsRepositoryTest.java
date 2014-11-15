package cl.uchile.dcc.feedback.test.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.uchile.dcc.feedback.model.StatisticsDataVO;
import cl.uchile.dcc.feedback.repositories.StatisticsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
public class StatisticsRepositoryTest {

	@Autowired
	StatisticsRepository statisticsRepo;
	
	@Test
	public void test() {
		List<StatisticsDataVO> r=statisticsRepo.getFreqTag(20);
		for(StatisticsDataVO s:r)
			System.out.println(s.getData()+" "+s.getValue()+" "+s.getControl());
	}

}
