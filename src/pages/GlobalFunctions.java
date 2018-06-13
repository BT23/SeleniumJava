package pages;

import java.util.Iterator;
import java.util.List;

//import javax.lang.model.util.Elements;

import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class GlobalFunctions {
	final WebDriver driver;
	
	WebDriverWait wait;
	
	JavascriptExecutor jse;
	
	/*
	 * Page Factory - All WebElements
	 */
	
	@FindBy(how = How.ID, using = "btnCloseForm")
	static WebElement btnCloseForm;

	@FindBy(how = How.ID, using = "menuButton")
	static WebElement menuButton;
	
	@FindBy(how = How.ID, using = "UsrCtlGlobalSearchMenuItem")
	static WebElement UsrCtlGlobalSearchMenuItem;	
	
	@FindBy(how = How.ID, using = "btnAssets")
	static WebElement btnAssets;
	
	@FindBy(how = How.ID, using = "btnWorkOrders")
	static WebElement btnWorkOrders;
	
	@FindBy(how = How.ID, using = "btnPMs")
	static WebElement btnPMs;	
	
	@FindBy(how = How.ID, using = "btnStores")
	static WebElement btnStores;	

	@FindBy(how = How.ID, using = "btnCatalogue")
	static WebElement btnCatalogue;

	@FindBy(how = How.ID, using = "btnPurchasing")
	static WebElement btnPurchasing;	
	
	@FindBy(how = How.ID, using = "btnControlFiles")
	static WebElement btnControlFiles;	

	@FindBy(how = How.ID, using = "btnAccidents")
	static WebElement btnAccidents;	
	
	@FindBy(how = How.ID, using = "btnDowntime")
	static WebElement btnDowntime;		
	
	@FindBy(how = How.ID, using = "btnKeys")
	static WebElement btnKeys;
	
	@FindBy(how = How.ID, using = "btnDrawings")
	static WebElement btnDrawings;	
	
	@FindBy(how = How.ID, using = "btnRequests")
	static WebElement btnRequests;
	
	@FindBy(how = How.ID, using = "btnCloseBreadcrumb")
	static WebElement btnCloseBreadcrumb;		
	
	@FindBy(how = How.XPATH, using ="//div/div/a/span[@class = 'k-icon k-i-close']")
	static WebElement btnCloseModelForm;
	
	@FindBy(how = How.XPATH, using ="//div/a[@href='#/MainMenu']")
	static WebElement btnHomeBreadcrumb;
	
	@FindBy(how = How.ID, using = "message")
	static WebElement message;
	
	@FindBy(how = How.ID, using = "MEXMessageBox")
	static WebElement MEXMessageBox;
	
	@FindBy(how = How.ID, using = "mnuOptions")
	static WebElement mnuOptions;
	
	@FindBy(how = How.ID, using = "tbpSideModules")
	static WebElement tbpSideModules;
	
	@FindBy(how = How.ID, using = "hplLogOffFromHeader")
	static WebElement hplLogOffFromHeader;
	
	/**
	 * Login Form
	 */
	@FindBy(how = How.ID, using = "txtUserName")
	static WebElement txtUserName;
	
	@FindBy(how = How.ID, using = "txtPassword")
	static WebElement txtPassword;
	
	@FindBy(how = How.ID, using = "txtPasswordConfirm")
	static WebElement txtPasswordConfirm;
	
	@FindBy(how = How.ID, using = "bOk")
	static WebElement bOk;
	
	public GlobalFunctions (WebDriver driver){
		this.driver = driver;
		
		wait = new WebDriverWait(driver,30);
		
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		
		jse = (JavascriptExecutor)driver;
	}	

	// Select Language
	public void selectEnglishLanguage(){
		for(int i=0; i<2; i++){
			try{
		        // find the language table
		        WebElement table = driver.findElement(By.xpath("//table[@class='k-selectable']"));

		        // select language = English (Australia) -- the following line was working on B63 or earlier
		        WebElement english = table.findElement(By.xpath("//tr/td/div[contains(text(), 'English (Australia)')]"));
		        
		        // the following line is working on B64
		        //WebElement english = table.findElement(By.xpath("//tr/td[contains(text(), 'English (Australia)')]"));
						
		        // Select English on language table
		        english.click();
		        
		        // Click OK button on language table
		        driver.findElement(By.id("btnOK")).click();
				break;
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				System.out.println("Trying to recover from a stale element: " + ex.getMessage());
			}
			catch(org.openqa.selenium.NoSuchElementException ex){
				System.out.println("No such item on listing!");
				break;
			}	
		}	
	}
	
	/*
	 * Wait Functions
	 */
	
	// Wait For Element
	
	public void waitForElement(String strElementID){
		try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strElementID)));
		}
		catch(NullPointerException ex){
			System.out.println(ex.toString());
		}
	}

	public void waitForClickable(String strElementID){
		wait.until(ExpectedConditions.elementToBeClickable(By.id(strElementID)));
	}
	
	public void waitForLoadingMask(String strElementID){
		By LoadingImage = By.id(strElementID);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingImage));
	}
	
	public void forceElementToBeClicked(String strElementID){
		WebElement element = driver.findElement(By.id(strElementID));
		Actions act = new Actions (driver);
		act.click(element).build().perform();
	}
	
	public void scrollDownUntilElementDisplayed(WebElement element){
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}	
	
	public void waitForPresenceOfElementLocated(String strElementID){
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strElementID)));
	}

	 public void waitForPageLoaded() {
		 ExpectedCondition<Boolean> expectation = new
	     ExpectedCondition<Boolean>() {
	     public Boolean apply(WebDriver driver) {
	    	 return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	     }
	     };
	     try {
	    	 Thread.sleep(1000);
	         WebDriverWait wait = new WebDriverWait(driver, 30);
	         wait.until(expectation);
	     } catch (Throwable error) {
	         Assert.fail("Timeout waiting for Page Load Request to complete.");
	     }   
	}
	 
	/*
	 * Main Menu
	 */		
	// Open Asset Register
	public void openAssetRegister(){
		// Wait until Assets icon is shown
		waitForElement("btnAssets");
		
	    // Click Asset icon
	    btnAssets.click();
	}	

	// Open Work Orders
	public void openWorkOrders(){
		// Wait until Work Orders icon is shown
		waitForElement("btnWorkOrders");
		
	    // Click Work Orders icon
		btnWorkOrders.click();
	}	
	
	public void openPreventativeMaintenance(){
		// Wait until PM icon is shown
		waitForElement("btnPMs");
		
	    // Click Asset icon
		btnPMs.click();
	}	
	
	public void openStoresMenu(){
		// Wait until Stores icon is shown
		waitForElement("btnStores");
		
	    // Click Stores icon
		btnStores.click();
	}	

	public void openCatalogue(){
		// Wait until Catalogue icon is shown
		waitForElement("btnCatalogue");
		
	    // Click Catalogue icon
		btnCatalogue.click();
	}	
	
	public void openPurchasing(){
		// Wait until Purchasing icon is shown
		waitForElement("btnPurchasing");
		
	    // Click Purchasing icon
		btnPurchasing.click();
	}	
	
	public void openControlFiles(){
		// Wait until Control Files icon is shown
		waitForElement("btnControlFiles");
		
	    // Click Control Files icon
		btnControlFiles.click();
	}		

	public void openAccidents(){
		// Wait until Accidents icon is shown
		waitForElement("btnAccidents");
		
	    // Click Accidents icon
		btnAccidents.click();
	}		
	
	public void openDowntime(){
		// Wait until Downtime icon is shown
		waitForElement("btnDowntime");
		
	    // Click Downtime icon
		btnDowntime.click();
	}	
		
	/*

	 * Search Listings and Grid Column Listings
	 */
	public void selectItemOnListing(String strSelectItem)
	{
		waitForElement("grdListing");
		for(int i=0; i<2; i++){
			try{
				WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'gridLabel') and text()='" + strSelectItem + "']"));
				scrollDownUntilElementDisplayed(element);
				element.click();
				break;
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				System.out.println("Trying to recover from a stale element: " + ex.getMessage());
			}
			catch(org.openqa.selenium.NoSuchElementException ex){
				System.out.println("No such item on listing!");
				break;
			}
		}
	}
	
	public void selectItemOnMainListing(String strSelectItem)
	{
		for(int i=0; i<2; i++){
			try{
				WebElement element = driver.findElement(By.xpath("//tr/td/div/span[contains(text(),'" + strSelectItem + "')]"));
				scrollDownUntilElementDisplayed(element);
				element.click();
				break;										
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				System.out.println("Trying to recover from a stale element: " + ex.getMessage());
			}
		}
	}	
	
	
	/*
	 * Verification
	 */
	
	// Verify grid column table is empty
	public void verifyGridColumnTableIsEmpty(){
		int count = 0;
		int maxtries = 2;
		while (true){
			try{
				List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
				System.out.println("Number of rows on grid columns listing: " + rows.size());
				Assert.assertEquals(rows.size(), 0);
				break;
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				if (++count == maxtries){
					System.out.println("Trying to recover from a stale element: " + ex.getMessage());
				}
			}
		}
	}

	// Verify correct values are populated on the first column in Grid Column Listing
	public void verifyFirstColumnValueGridColumnListing(String strVerifiedItem1, String strVerifiedItem2){
		for(int i=0; i<2; i++){
			try{
				List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
		        int rowCount = 1;
		        
		        for(WebElement row : rows){
		            List<WebElement> columns = row.findElements(By.xpath("td[1]"));
		            for(WebElement column : columns){
		            	switch(rowCount){
		            	case 1:
		            		//System.out.println("Row Count: 1");
		            		//System.out.println(column.getText());
		            		Assert.assertEquals(column.getText(), strVerifiedItem1);
		            		break;
		            	case 2:
		            		//System.out.println("Row Count: 2");
		            		//System.out.println(column.getText());
		            		Assert.assertEquals(column.getText(), strVerifiedItem2);
		            		break;
		            	}
		            }
		           rowCount++;
		        }
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
					System.out.println("Trying to recover from a stale element: " + ex.getMessage());
			}
		}
	}		
	
	// Verify text field is editable
	public boolean verifyTextFieldEditable(WebElement element){
		return element.isEnabled();
	}

	// Verify element is found
	public boolean verifyElementIsFound(WebElement element){
		return element.isDisplayed();
	}	

	// Verify checkbox is ticked
	public boolean verifyCheckboxIsTicked(WebElement element){
		return element.isSelected();
	}	
	
	// Verify Requests is displayed on Main Menu - Side Modules and User Options - Side Modules tab
	public void verifyRequestsModuleIsDisplayed(String strCallingModule){
		if (strCallingModule.equals("MainMenu"))
			Assert.assertTrue(verifyElementIsFound(btnRequests), "Wrong!! Requests module SHOULD BE SHOWN on Side Module Menu by default");
		else if(strCallingModule.equals("UserOption")){
		
			WebElement table = driver.findElement(By.xpath("//div[@id='grpUserSideModules']//div/table[@class='k-selectable']/tbody"));
			
		    WebElement sideModule = table.findElement(By.xpath("//tr[@class='k-alt']/td/div[contains(text(), 'Requests')]"));
		     
			Assert.assertTrue(verifyElementIsFound(sideModule), "Wrong!! Requests module SHOULD BE SHOWN on Side Module Menu by default");
		}
	}
	
	// Verify Keys is displayed on Main Menu - Side Modules and User Options - Side Modules tab
	public void verifyKeysModuleIsDisplayed(String strCallingModule){
		if (strCallingModule.equals("MainMenu"))
			Assert.assertTrue(verifyElementIsFound(btnKeys), "Wrong!! Keys module SHOULD BE SHOWN on Side Module Menu by default");
		else if(strCallingModule.equals("UserOption")){
			WebElement table = driver.findElement(By.xpath("//div[@id='grpUserSideModules']//div/table[@class='k-selectable']/tbody"));
			
		    WebElement sideModule = table.findElement(By.xpath("//tr[@class='k-state-selected']/td/div[contains(text(), 'Keys')]"));
		     
		    Assert.assertTrue(verifyElementIsFound(sideModule), "Wrong!! Keys module SHOULD BE SHOWN on Side Module Menu by default");
		}
	}
	
	// Verify Drawings is displayed on Main Menu - Side Modules and User Options - Side Modules tab
	public void verifyDrawingsModuleIsDisplayed(String strCallingModule){
		if (strCallingModule.equals("MainMenu"))
			Assert.assertTrue(verifyElementIsFound(btnDrawings), "Wrong!! Drawings module SHOULD BE SHOWN on Side Module Menu by default");
		else if(strCallingModule.equals("UserOption")){
			WebElement table = driver.findElement(By.xpath("//div[@id='grpUserSideModules']//div/table[@class='k-selectable']/tbody"));
			
		    WebElement sideModule = table.findElement(By.xpath("//tr/td/div[contains(text(), 'Drawings')]"));
		     
		    Assert.assertTrue(verifyElementIsFound(sideModule), "Wrong!! Drawings module SHOULD BE SHOWN on Side Module Menu by default");
		}
	}	

	// Verify Downtime is displayed on Main Menu - Side Modules and User Options - Side Modules tab
	public void verifyDowntimeModuleIsDisplayed(String strCallingModule){
		if (strCallingModule.equals("MainMenu"))
			Assert.assertTrue(verifyElementIsFound(btnDowntime), "Wrong!! Downtime module SHOULD BE SHOWN on Side Module Menu by default");
		else if(strCallingModule.equals("UserOption")){
			WebElement table = driver.findElement(By.xpath("//div[@id='grpUserSideModules']//div/table[@class='k-selectable']/tbody"));
			
		    WebElement sideModule = table.findElement(By.xpath("//tr[@class='k-alt']/td/div[contains(text(), 'Downtime')]"));
		     
		    Assert.assertTrue(verifyElementIsFound(sideModule), "Wrong!! Downtime module SHOULD BE SHOWN on Side Module Menu by default");
		}
	}
	
	// Verify Accidents is displayed on Main Menu - Side Modules and User Options - Side Modules tab
	public void verifyAccidentsModuleIsDisplayed(String strCallingModule){
		if (strCallingModule.equals("MainMenu"))
			Assert.assertTrue(verifyElementIsFound(btnAccidents), "Wrong!! Accidents module SHOULD BE SHOWN on Side Module Menu by default");
		else if(strCallingModule.equals("UserOption")){
			WebElement table = driver.findElement(By.xpath("//div[@id='grpUserSideModules']//div/table[@class='k-selectable']/tbody"));
			
		    WebElement sideModule = table.findElement(By.xpath("//tr[@class='k-alt']/td/div[contains(text(), 'Accidents')]"));
		     
		    Assert.assertTrue(verifyElementIsFound(sideModule), "Wrong!! Accidents module SHOULD BE SHOWN on Side Module Menu by default");
		}
	}		

	// Verify details form is opened
	public void verifyDetailsFormIsOpen()
	{
		String strFormTitle = driver.findElement(By.id("lblHeader")).getText();
		
		switch(strFormTitle){
		case "Work Order":
			System.out.println("Work Order Details form is opened");
		case "Preventative Maintenance":
			System.out.println("Preventative Maintenance Details form is opened");
		case "Purchase Order":
			System.out.println("Purchase Order Details form is opened");
		}
		
	}	
	
	/*
	 * Global Buttons
	 */	
	// Red X button
	public void clickCloseButton(){
		waitForClickable("btnCloseForm");
		btnCloseForm.click();
	}	
	
	public void clickCloseButtonOnModelForm(String strFormID){
		waitForElement(strFormID);
		btnCloseModelForm.click();
	}
	
	// Global Search button
	public void clickGlobalSearchButton(){
		// Wait for any icons on main menu to load. Otherwise, Global Search icon cannot be clicked.
		waitForElement("btnControlFiles");
		UsrCtlGlobalSearchMenuItem.click();
	}	
	
	// Hamburger Menu button
	public void clickHamburgerMenuButton(){
		// Wait for any icons on main menu to load. Otherwise, Hamburger menu cannot be clicked.
		waitForElement("btnControlFiles");
		menuButton.click();
	}	

	// User Options - Side Modules tab
	public void clickUserOptionsSideModulesTab(){
		waitForClickable("tbpSideModules");
		tbpSideModules.click();
	}	
	
	public void closeStoreMenu(){
		this.clickCloseButtonOnModelForm("StoresMenuTemplate_wnd_title");
	}
	
	// Log off hyperlink
	public void clickLogOffHyperlink(){
		waitForClickable("hplLogOffFromHeader");
		hplLogOffFromHeader.click();
	}
	
	// Click OK button Login form
	public void clickOKBtnOnMEXLoginForm(){
		waitForClickable("bOk");
		bOk.click();
	}
	
	/*
	 * Hamburger Menu Options
	 */	
	// Options
	public void clickUserOptions(){
		waitForClickable("mnuOptions");
		mnuOptions.click();
	}	
	
	/*
	 * Close button Breadcrumb
	 */	
	// X button on tab
	public void clickCloseBreadcrumbButton(){
		waitForClickable("btnCloseBreadcrumb");
		btnCloseBreadcrumb.click();
	}		

	
	// Home button on tab
	public void clickHomeBreadcrumbButton(){
		int count = 0;
		int maxtries = 2;

		waitForElement("grdListing");
		while (true){
			try{
				waitForClickable("navBar");
				btnHomeBreadcrumb.click();
				}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				if (++count == maxtries){
				System.out.println("Trying to recover from a stale element: " + ex.getMessage());}
			}
			catch(org.openqa.selenium.NoSuchElementException ex){
				if (++count == maxtries){
				System.out.println(ex.getMessage());}
			}
		}
	}	
	
	/*
	 * Set methods
	 */
	// Set value in Textfield
	public void setTextFieldValue(WebElement element, String strValue){
		element.click();
		element.sendKeys(strValue);
		element.sendKeys(Keys.TAB);
	}

	// Set Kendo Drop Down Value
	public void setKendoDropDownValue(String strElementID, String strSelectDropDownValue){
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(strElementID)));
	
		List<WebElement> options = driver.findElements(By.cssSelector(strElementID));
		for (Iterator<WebElement> iterator = options.iterator(); iterator.hasNext();){
			WebElement option = (WebElement) iterator.next();
			
			if (option.getText().contains(strSelectDropDownValue))
				option.click();
		}
	}	
	
	// Set Date
	public String setDate(int Number){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Number);
		return dateFormat.format(cal.getTime());
	}

	// Set value in Username textfield
	public void setUserNameValue(String strValue){
		txtUserName.click();
		txtUserName.sendKeys(strValue);
		txtUserName.sendKeys(Keys.TAB);
	}
	
	// Set value in Password textfield
	public void setPasswordValue(String strValue){
		txtPassword.click();
		txtPassword.sendKeys(strValue);
		txtPassword.sendKeys(Keys.TAB);
	}

	// Set value in Password textfield
	public void setConfirmPasswordValue(String strValue){
		txtPasswordConfirm.click();
		txtPasswordConfirm.sendKeys(strValue);
		txtPasswordConfirm.sendKeys(Keys.TAB);
	}	
	
	// Calculate time difference
	public long calculateTimeDifferenceInHours(String strStartTime, String strFinishTime){
		long difference = 0;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
			Date dteStart = dateFormat.parse(strStartTime);
			Date dteFinish = dateFormat.parse(strFinishTime);
			
			// difference is in milliseconds
			difference = dteFinish.getTime() - dteStart.getTime();			
		}
		catch(ParseException ex){
			System.out.println(ex.toString());
		}
		return difference;	
	}
	
	// Tab off the field
	public void tabOffTheField(WebElement element){
		element.sendKeys(Keys.TAB);
	}

	// Tab off the field in Grid Column Listing
	public void tabOffGridColumn(WebElement element, int times){
		waitForElement("grdListing");
		for(int i=1; i<=times; i++)
		{
			element.sendKeys(Keys.TAB);
			i++;
		}
	}	
	
	/*
	 * Get methods
	 */
	// Get value from Textfield
	public String getTextFieldValue(WebElement element){
		return (String) jse.executeScript("return arguments[0].value", element);
	}			
	
	// Get Time
	public String getTime(WebElement element){
		String strTime = (String) jse.executeScript("return arguments[0].value", element);
		return strTime;
	}	
	
	public String getHyperlinkValue(WebElement element){
		return element.getText();
	}
	
	/*
	 * Message Box
	 */
	// Get message box message
	public String getMessageBoxContent(){
		return message.getText();
	}

	// Get message box message
	public String getMEXMessageBoxContent(){
		return MEXMessageBox.getText();
	}	
	
	// Get click Yes to Message Box
	public void clickYestoMessageBox(){
		this.waitForElement("MEXMessageBox");
		MEXMessageBox.findElement(By.xpath("//button[starts-with(@class, 'yesButton')]")).click();
	}
	
	// Get click No to Message Box
	public void clickNotoMessageBox(){
		MEXMessageBox.findElement(By.xpath("//button[starts-with(@class, 'noButton')]")).click();
	}

	// Get Grid Column textfield value
	public String getGridColumnGridLabelValue(String strItem, int columnNumber){
		WebElement gridColumnTable = driver.findElement(By.xpath("//table[@class='k-selectable']"));
		WebElement gridLabelCell = gridColumnTable.findElement(By.xpath("//td[.='" + strItem +"']/following-sibling::td[" + columnNumber + "]"));
		return gridLabelCell.getText();
	}	
	
	// Get Grid Column Checkbox value
	public boolean getGridColumnCheckboxValue(String strItem, int columnNumber){
		WebElement gridColumnTable = driver.findElement(By.xpath("//table[@class='k-selectable']"));
		WebElement checkboxCell = gridColumnTable.findElement(By.xpath("//td[.='" + strItem +"']/following-sibling::td[" + columnNumber + "]/input"));
		return checkboxCell.isSelected();
	}
	
	// Get number of records on the searched item
	public int getNoOfRecordsOnSearchedItem(String strSearchItem){
		waitForElement("grdListing");
		List<WebElement> records = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[1]/div[contains(text(), '" + strSearchItem + "')]"));				
		return records.size();

	}
	
	// Get value from listing
	public String getValueFromListing(String strSearchItem, int columnNumber)
	{
		int count = 0;
		int maxtries = 2;
		waitForElement("grdListing");
		while (true){
			try{
				WebElement baseTable = driver.findElement(By.xpath("//table[@class='k-selectable']/tbody/tr"));
				WebElement cellValue = baseTable.findElement(By.xpath("//td[.='" + strSearchItem +"']/following-sibling::td[" + columnNumber + "]"));
				return cellValue.getText();
				}
			catch(org.openqa.selenium.StaleElementReferenceException ex){
				if (++count == maxtries){
				System.out.println("Error!! Trying to recover from a stale element: " + ex.getMessage());}
			}
			catch(org.openqa.selenium.NoSuchElementException ex){
				if (++count == maxtries){
				System.out.println("No such item on listing!");}
			}
		}
	}
	
	/*
	 * Print  System Message
	 */
	public void printRegressionTestExpectedResultMessage(String strMessage){
		System.out.println("Expected Result: " + strMessage);
	}
}
