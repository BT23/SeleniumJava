package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pages.GlobalFunctions;

public class Catalogue {

	final WebDriver driver;
	GlobalFunctions objGlobalFunc;	

	/*
	 * Page Factory - All WebElements
	 */
	@FindBy(how = How.ID, using = "bNew")
	static WebElement bNew;
	
	@FindBy(how = How.ID, using = "bDetails")
	static WebElement bDetails;	
	
	@FindBy(how = How.ID, using = "bIssueReturn")
	static WebElement bIssueReturn;
	
	@FindBy(how = How.ID, using = "txtSearch")
	static WebElement txtSearch;	
	
	public Catalogue (WebDriver driver){
		this.driver = driver;
		
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		
		objGlobalFunc = new GlobalFunctions(driver);
	}

	/*
	 * Buttons click
	 */
	// Create New Catalogue
	public void clickNewBtn(){
		objGlobalFunc.waitForClickable("bNew");
		bNew.click();
	}

	// View Catalogue
	public void clickDetailsBtn(){
		objGlobalFunc.waitForClickable("bDetails");
		bDetails.click();
	}
	
	// Open Issue/Return
	public void clickIssueReturnBtn(){
		objGlobalFunc.waitForClickable("bIssueReturn");
		bIssueReturn.click();
	}
	
	// Click Search field
	public void clickSearchField(){
		objGlobalFunc.waitForClickable("txtSearch");
		txtSearch.click();
	}
	
	public void selectCatalogue(String strSelectCatalogue)
	{
		for(int i=0; i<2; i++){
			try{
				WebElement element = driver.findElement(By.xpath("//tr/td/div/span[contains(text(),'" + strSelectCatalogue + "')]"));
				objGlobalFunc.scrollDownUntilElementDisplayed(element);
				element.click();
				break;										
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				System.out.println("Trying to recover from a stale element: " + ex.getMessage());
			}
		}
	}
	
}
