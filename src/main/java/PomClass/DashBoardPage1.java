package PomClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class DashBoardPage1 
{
	WebDriver driver;


    public DashBoardPage1 (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }    

	
	@FindBy(how = How.XPATH, using = "//a[@class='fw-bold text-dark fs-14 stretched-link text-decoration-none']")
	private WebElement COMPANY;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[1]")
	private WebElement TOTAL_CASES;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[2]")
	private WebElement PEOPLE;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[3]")
	private WebElement DOCUMENT;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[4]")
	private WebElement MATTERS;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[5]")
	private WebElement TASK;

	@FindBy(how = How.XPATH, using = "(//span[@class='fs-14 fw-semibold text-primary'])[6]")
	private WebElement INVOICES;

	public void clickOncompany(String CompanyName) {
		List<WebElement> elements = driver
				.findElements(By.xpath("//a[@class='fw-bold text-dark fs-14 stretched-link text-decoration-none']"));
		System.err.println("Total company " + elements.size());

		String searchQuery = CompanyName;
		for (WebElement element : elements) {
			try {
				String elementTxt = element.getText();

				if (elementTxt.equals(searchQuery)) {
					element.click();
					 // Set Company Name in Library Class
					System.out.println("Selected Company: "+CompanyName );
					break;
				}
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	
	
		
}
