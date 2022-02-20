package suites;

import org.testng.annotations.Test;
import base.Base;

public class TestSuite extends Base {

	
	@Test
	public void goToUrl() throws Exception{		
			
		mp.checkMainPage();
	}
	
	@Test
	public void goToCareerPage() throws Exception{		
			
		mp.checkMainPage();
		mp.checkCareerPage();	
	}
	
	@Test
	public void chooseQAJobs() throws Exception{		
			
		mp.checkMainPage();
		mp.checkCareerPage();	
		mp.checkPresenceOfJobsList();
	}
	
	@Test
	public void checkQAJobs() throws Exception{		
		mp.checkMainPage();
		mp.checkCareerPage();	
		mp.checkPresenceOfJobsList();	
		mp.checkOpenQAJobs();
	}
	
	@Test
	public void checkApplyJobPage() throws Exception{		
		mp.checkMainPage();
		mp.checkCareerPage();	
		mp.checkPresenceOfJobsList();	
		mp.checkApplyButton();
	}
	
}
