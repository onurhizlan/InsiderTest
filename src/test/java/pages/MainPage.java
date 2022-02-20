package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Base;



public class MainPage extends Base {

	private WebDriver driver;
	private BaseFunctions bf;
	private String environment;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 180);
		
	}

	public void init(String environment) {
		bf = new BaseFunctions(driver);
		this.environment = environment;
	}
	
	
	
	
	
	@FindBy(xpath = "//div[contains(@id, 'main-head-desktop')]")
	private WebElement checkMainPage;
	
	@FindBy(xpath = "//span[text()='More']")
	private WebElement moreButton;
	
	@FindBy(xpath = "//h5[text()='Careers']")
	private WebElement careerButton;
	
	@FindBy(xpath = "//a[text()='See all teams']")
	private WebElement seeAllTeamsButton;
	
	@FindBy(xpath = "//h2[text()='Life at Insider']")
	private WebElement lifeAtInsiderTitle;
	
	@FindBy(xpath = "//h2[text()='Life at Insider']//following::p[1]")
	private WebElement lifeAtInsiderContent;
	
	@FindBy(xpath = "//h3[text()='Quality Assurance']")
	private WebElement qaButton;
	
	@FindBy(xpath = "//a[text()='See all QA jobs']")
	private WebElement seeAllQAJobs;
	
	@FindBy(xpath = "//label[text()='Filter by Location']//following::span[1]")
	private WebElement filterByLocationDropDown;
	
	@FindBy(xpath = "//li[text()='Istanbul, Turkey']")
	private WebElement locationOfIstanbul;
	
	@FindBy(xpath = "//label[text()='Filter by Department']//following::span[1]")
	private WebElement filterByDepartmentDropDown;
	
	@FindBy(xpath = "//li[text()='Quality Assurance']")
	private WebElement departmentOfIstanbul;
	
	@FindBy(xpath = "(//a[contains(.,'Accept All')])")
	private WebElement acceptAllPopUp;
	
	@FindBy(xpath = "(//a[contains(.,'Apply for this job')])[1]")
	private WebElement applyButtonText;
	
	public void checkMainPage() throws Exception{
		
		driver.navigate().to(environment);
		bf.waitElement(checkMainPage);
		if (acceptAllPopUp.isEnabled()) {
			bf.click(acceptAllPopUp);
		}
	}
	
	public void checkCareerPage() throws Exception{
		
		bf.click(moreButton);
		bf.click(careerButton);
		Assert.assertEquals(true, seeAllTeamsButton.isDisplayed());
		Assert.assertEquals(true, lifeAtInsiderTitle.isDisplayed());
		Assert.assertEquals(true, lifeAtInsiderContent.isDisplayed());
		checkLocation();
	}
	
	public void  checkPresenceOfJobsList() throws Exception{
		bf.waitElement(seeAllTeamsButton);
		bf.scrollToElement(seeAllTeamsButton);
		bf.clickWithJavaScript(seeAllTeamsButton);
		bf.waitElement(qaButton);
		bf.scrollToElement(qaButton);
		Thread.sleep(5000);
		bf.click(qaButton);
		bf.waitElement(seeAllQAJobs);
		bf.click(seeAllQAJobs);
		bf.click(filterByLocationDropDown);
		bf.click(locationOfIstanbul);
		bf.click(filterByDepartmentDropDown);
		bf.click(departmentOfIstanbul);
	}
	
	public void checkLocation() throws Exception{
		
		List<WebElement> element = driver.findElements(By.xpath("//ul[@class = 'glide__slides']//following::li[contains(@class, 'glide__slide')]"));
		for (int i = 1; i <= element.size(); i++) {
			try {
				String xpath = "//ul[@class = 'glide__slides']//following::li[contains(@class, 'glide__slide')][%s]";			
				WebElement element2 = driver.findElement(By.xpath(String.format(xpath, i)));
				boolean locationCheck = element2.isEnabled();
				System.out.println("Location displayed is :" + locationCheck);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkOpenQAJobs() throws Exception{
		
		List<WebElement> element = driver.findElements(By.xpath("//div[@class = 'position-list-item-wrapper bg-light']"));
		for (int i = 1; i <= element.size(); i++) {
			try {
				String xpath = "(//div[@class = 'position-list-item-wrapper bg-light']//following::span[contains(.,'Quality Assurance')])[%s]";	
				WebElement jobCheckQAText = driver.findElement(By.xpath(String.format(xpath, i)));
				String xpath2 = "(//div[@class = 'position-list-item-wrapper bg-light']//following::a[contains(.,'Apply Now')])[%s]";	
				WebElement jobCheckAppyButtonText = driver.findElement(By.xpath(String.format(xpath2, i)));
				boolean title = jobCheckQAText.isEnabled();
				System.out.println("Title displayed is :" + title);
				boolean apply = jobCheckAppyButtonText.isEnabled();
				System.out.println("Apply displayed is :" + apply);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkApplyButton() throws Exception{
		
		List<WebElement> element = driver.findElements(By.xpath("(//div[@class = 'position-list-item-wrapper bg-light']//following::a[contains(.,'Apply Now')])"));
		for (int i = 1; i <= element.size(); i++) {
			try {
				String xpath = "(//div[@class = 'position-list-item-wrapper bg-light']//following::a[contains(.,'Apply Now')])[%s]";	
				WebElement applyButton = driver.findElement(By.xpath(String.format(xpath, i)));
				bf.clickWithJavaScript(applyButton);
				Thread.sleep(5000);
				bf.changeToLastTab();
				Thread.sleep(5000);
				boolean checkApplyButton = applyButtonText.isEnabled();
				System.out.println("Apply Button Text displayed is :" + checkApplyButton);
				driver.close();
				bf.changeToLastTab();
				Thread.sleep(5000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
