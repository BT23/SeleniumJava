package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import pages.GlobalFunctions;

public class CatalogueDetails {

	final WebDriver driver;

	GlobalFunctions objGlobalFunc;
	
	JavascriptExecutor jse;
	
	public CatalogueDetails (WebDriver driver){
		this.driver = driver;
		
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		
		objGlobalFunc = new GlobalFunctions(driver);
		
		jse = (JavascriptExecutor)driver;
	}
	
	/*
	 * Page Factory - All WebElements
	 */

	@FindBy(how = How.ID, using = "txtComments")
	static WebElement txtComments;		
	
	@FindBy(how = How.ID, using = "ddStore")
	static WebElement ddStore;		

	@FindBy(how = How.ID, using = "EllipseSite")
	static WebElement EllipseSite;
	
	@FindBy(how = How.ID, using = "btnNewLevel1")
	static WebElement btnNewLevel1;
	
	@FindBy(how = How.ID, using = "txtCatalogueNo")
	static WebElement txtCatalogueNo;

	@FindBy(how = How.ID, using = "txtPartName")
	static WebElement txtPartName;	

	@FindBy(how = How.ID, using = "txtStockOnHand")
	static WebElement txtStockOnHand;
	
	@FindBy(how = How.ID, using = "txtCostPerItem")
	static WebElement txtCostPerItem;
	
	@FindBy(how = How.ID, using = "txtAreaOfPlant")
	static WebElement txtAreaOfPlant;	

	@FindBy(how = How.ID, using = "tbpDetails")
	static WebElement tbpDetails;	
	
	@FindBy(how = How.ID, using = "tbpDocs")
	static WebElement tbpDocs;	
	
	@FindBy(how = How.ID, using = "tbpSuppliers")
	static WebElement tbpSuppliers;				
	
	@FindBy(how = How.ID, using = "tbpTransactions")
	static WebElement tbpTransactions;	
	
	@FindBy(how = How.ID, using = "tbpAPL")
	static WebElement tbpAPL;	
	
	@FindBy(how = How.ID, using = "bNewSupplier")
	static WebElement bNewSupplier;		

	@FindBy(how = How.ID, using = "bSupplierDetails")
	static WebElement bSupplierDetails;	
	
	@FindBy(how = How.ID, using = "bNewSupplierTab")
	static WebElement bNewSupplierTab;
	
	@FindBy(how = How.ID, using = "bNewStock")
	static WebElement bNewStock;	

	@FindBy(how = How.ID, using = "bNewAPL")
	static WebElement bNewAPL;	

	@FindBy(how = How.ID, using = "bNewCatalogue")
	static WebElement bNewCatalogue;
	
	@FindBy(how = How.ID, using = "ddSupplierContactID")
	static WebElement ddSupplierContactID;		
	
	/*
	 * Tabs click
	 */
	// Click on Details tab
	public void clickDetailsTab(){
		objGlobalFunc.waitForClickable("tbpDetails");
		tbpDetails.click();
	}
	
	// Click on Suppliers tab
	public void clickSuppliersTab(){
		objGlobalFunc.waitForClickable("tbpSuppliers");
		tbpSuppliers.click();
	}	
	
	// Click on Docs tab
	public void clickDocsTab(){
		objGlobalFunc.waitForClickable("tbpDocs");
		tbpDocs.click();
	}	
	
	// Click on APL tab
	public void clickAPLTab(){
		objGlobalFunc.waitForClickable("tbpAPL");
		tbpAPL.click();
	}	

	// Click on Transactions tab
	public void clickTransactionsTab(){
		objGlobalFunc.waitForClickable("tbpTransactions");
		tbpTransactions.click();
	}	
	
	/*
	 * Grid Column Listing
	 */
	public void tabOffSupplierField(){
		objGlobalFunc.tabOffTheField(ddSupplierContactID);
	}
	
	/*
	 * Buttons click
	 */
	/* Details tab */
	// Click on New Catalogue button
	public void clickNewCatalogueBtn(){
		objGlobalFunc.waitForClickable("bNewCatalogue");
		bNewCatalogue.click();
	}
	
	// Click on New Stock button
	public void clickNewStockBtn(){
		objGlobalFunc.waitForClickable("bNewStock");
		bNewStock.click();
	}
	
	/* Supplier tab */
	// Click on New line
	public void clickNewSupplierTabBtn(){
		objGlobalFunc.waitForClickable("bNewSupplierTab");
		bNewSupplierTab.click();
	}	
	
	// Click on Supplier Details button
	public void clickSupplierDetailsBtn(){
		objGlobalFunc.waitForClickable("bSupplierDetails");
		bSupplierDetails.click();
	}		

	// Click on New Supplier button
	public void clickNewSupplierBtn(){
		objGlobalFunc.waitForClickable("bNewSupplier");
		bNewSupplier.click();
	}	

	/* APL tab */
	// Click on New APL button
	public void clickNewAPLBtn(){
		objGlobalFunc.waitForClickable("bNewAPL");
		bNewAPL.click();
	}	
	
	/*
	 * Set values
	 */	
	
	// Enter Comment
	public void enterComments(String strComments)
	{
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		objGlobalFunc.setTextFieldValue(txtComments, strComments);
	}	
	
	// Enter Store
	public void enterStore(String strDropDownValue)
	{
		objGlobalFunc.waitForElement("CatalogueDetailsDetailTab_StockDetailsSection");
		
		WebElement kendoDropDown = driver.findElement(By.xpath("//div[@id='CatalogueDetailsDetailTab_StockDetailsSection']"));
		
		WebElement kendoDropDownArrow = kendoDropDown.findElement(By.cssSelector("span.k-select"));
		kendoDropDownArrow.click();
		
		objGlobalFunc.waitForPresenceOfElementLocated("//div[@id='ddStore-list']");
		objGlobalFunc.setKendoDropDownValue("div#ddStore-list ul#ddStore_listbox li.k-item", strDropDownValue);
	}
	
	// Enter Catalogue No.
	public void enterCatalogueNumber(String strCatalogueNumber){
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		objGlobalFunc.setTextFieldValue(txtCatalogueNo, strCatalogueNumber);
	}
	
	// Enter Part Name
	public void enterPartName(String strPartName){
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		objGlobalFunc.setTextFieldValue(txtPartName, strPartName);
	}

	// Enter Area of Plant
	public void enterAreaOfPlant(String strAreaOfPlant){
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		objGlobalFunc.setTextFieldValue(txtAreaOfPlant, strAreaOfPlant);
	}	
	
	// Enter Stock On Hand
	public void enterStockOnHand(String strStockOnHand){
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		jse.executeScript("$('#txtStockOnHand').siblings('input:visible').focus();");
		objGlobalFunc.setTextFieldValue(txtStockOnHand, strStockOnHand);
	}
	
	// Enter Cost Per Item
	public void enterCostPerItem(String strCostPerItem){
		objGlobalFunc.waitForElement("grpCatalogueDetails2");
		objGlobalFunc.setTextFieldValue(txtCostPerItem, strCostPerItem);
	}

	// Enter Supplier
	public void enterSupplier(String strSupplier){
		objGlobalFunc.waitForElement("ddSupplierContactID");
		objGlobalFunc.setTextFieldValue(ddSupplierContactID, strSupplier);
	}

	// Select Supplier from drop down
	public void selectSupplierFromDropDown(String strSupplier){
		ddSupplierContactID.sendKeys(strSupplier);
		objGlobalFunc.waitForPresenceOfElementLocated("//div/div[@id='ddSupplierContactID-list']");
		objGlobalFunc.setKendoDropDownValue("div.k-list-scroller ul#ddSupplierContactID_listbox li.k-item", strSupplier);	
	}
	
	// Enter multiple Supplier. in Supplier tab
	public void enterMultipleSupplier(String strSupplier){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsSuppliersTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
        WebElement desiredCell = rows.get(rows.size()-1).findElement(By.id("ddSupplierContactID"));
        
        desiredCell.sendKeys(strSupplier);

		objGlobalFunc.waitForPresenceOfElementLocated("//div[@id='ddSupplierContactID-list']");
		objGlobalFunc.setKendoDropDownValue("ul#ddSupplierContactID_listbox", strSupplier);
		
		desiredCell.sendKeys(Keys.TAB);
    }	
	
	// Enter Asset No. in APL tab
	public void enterAssetNo(String strAssetNo){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsAPLTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
        WebElement desiredCell = rows.get(rows.size()-1).findElement(By.id("ddAssetID"));
        
        desiredCell.sendKeys(strAssetNo);

		objGlobalFunc.waitForPresenceOfElementLocated("//div[@id='ddAssetID-list']");
		objGlobalFunc.setKendoDropDownValue("ul#ddAssetID_listbox", strAssetNo);
		
		desiredCell.sendKeys(Keys.TAB);
    }
	
	/*
	 * Get values
	 */
	
	public String getCatalogueNumber()
	{
		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
		return objGlobalFunc.getTextFieldValue(txtCatalogueNo);
	}
						
	public String getPartName()
	{
		objGlobalFunc.waitForElement("CatalogueDetails_ContentHeaderSection");
		return objGlobalFunc.getTextFieldValue(txtPartName);
	}	
	
	public String getStockOnHandValue()
	{
		return objGlobalFunc.getTextFieldValue(txtStockOnHand);
	}
	
	// Get Action in Transactions tab 
	public String getActionTransactionsTab(){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsTransactionsTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[2]/div"));
		WebElement desiredCell = rows.get(rows.size()-1);
		System.out.println("Action: " + desiredCell.getText());
		return	desiredCell.getText();	
	}
	
	// Get Quantity in Transactions tab 
	public String getQuantityTransactionsTab(){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsTransactionsTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[4]"));
		WebElement desiredCell = rows.get(rows.size()-1);
		System.out.println("Quantity: " + desiredCell.getText());
		return desiredCell.getText();
	}
	
	// Get Account Code in Transactions tab 
	public String getAccountCodeTransactionsTab(){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsTransactionsTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[15]/div"));
		WebElement desiredCell = rows.get(rows.size()-1);
		System.out.println("Account Code: " + desiredCell.getText().toString());
		return desiredCell.getText().toString();
	}
	
	// Get Asset No in Transactions tab 
	public String getAssetNoTransactionsTab(){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsTransactionsTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[16]/div"));
		WebElement desiredCell = rows.get(rows.size()-1);
		System.out.println("Asset Number: " + desiredCell.getText().toString());
		return desiredCell.getText().toString();
	}	

	// Get Issue To Return From in Transactions tab 
	public String getIssueToReturnFromTransactionsTab(){
		objGlobalFunc.waitForElement("UsrCtlCatalogueDetailsTransactionsTab");
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr/td[17]/div"));
		WebElement desiredCell = rows.get(rows.size()-1);
		System.out.println("Issue To Return To: " + desiredCell.getText().toString());
		return desiredCell.getText().toString();
	}	
	
	/*
	 * Verification
	 */
	public void verifySOHFieldEdiable()
	{
		if (objGlobalFunc.verifyTextFieldEditable(txtStockOnHand))
		{
			System.out.println("SOH field is ediable");
		}
		else
		{
			System.out.println("SOH field is locked up");
		}	
	}
	
}
