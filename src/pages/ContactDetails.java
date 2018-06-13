package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.GlobalFunctions;

public class ContactDetails {

	private final WebDriver driver;
	ContactDetails objContactDetails;
	GlobalFunctions objGlobalFunc;
	JavascriptExecutor jse;
	
	public ContactDetails (WebDriver driver){
		this.driver = driver;
		
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
		
		objGlobalFunc = new GlobalFunctions(driver);
		jse = (JavascriptExecutor)driver;
	}	

	/*
	 * Page Factory - All WebElements
	 */
	@FindBy(how = How.ID, using = "txtCode")
	WebElement txtCode;
	
	@FindBy(how = How.ID, using = "txtCompany")
	WebElement txtCompany;

	// Logon Name - Contacts Details
	@FindBy(how = How.ID, using = "txtUserLoginName")
	WebElement txtUserLoginName;
	
	@FindBy(how = How.ID, using = "txtFirstName")
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "txtLastName")
	WebElement txtLastName;	
	
	@FindBy(how = How.ID, using = "txtWorkPhone")
	WebElement txtWorkPhone;	
	
	@FindBy(how = How.ID, using = "txtFax")
	WebElement txtFax;		
	
	@FindBy(how = How.ID, using = "txtEmail")
	WebElement txtEmail;		
		
	// Add Contact form
	@FindBy(how = How.CSS, using = "#AddChildContact_ContentSection > div.formRow > div.width_auto > #txtFirstName")
	WebElement txtAddContactFormFirstName;	
	
	@FindBy(how = How.CSS, using = "#AddChildContact_ContentSection > div.formRow > div.width_auto > #txtLastName")
	WebElement txtAddContactFormLastName;	
	
	@FindBy(how = How.ID, using = "txtCataloguedMarkupPercent")
	WebElement txtCataloguedMarkupPercent;
	
	@FindBy(how = How.ID, using = "txtNonCataloguedMarkupPercent")
	WebElement txtNonCataloguedMarkupPercent;	
	
	@FindBy(how = How.ID, using = "rdbApproveAll")
	WebElement rdbApproveAll;
	
	@FindBy(how = How.ID, using = "tbpDetails")
	WebElement tbpDetails;	
	
	@FindBy(how = How.ID, using = "tbpDocuments")
	WebElement tbpDocuments;	
	
	//@FindBy(how = How.ID, using = "tbpContacts")
	@FindBy(how = How.CSS, using = "#tbpContacts > span.k-link")
	WebElement tbpContacts;	
	
	@FindBy(how = How.ID, using = "tbpInsurance")
	WebElement tbpInsurance;	
	
	//@FindBy(how = How.ID, using = "tbpMexUser")
	@FindBy(how = How.CSS, using = "#tbpMexUser > span.k-link")
	WebElement tbpMexUser;	
	
	@FindBy(how = How.ID, using = "tbpPurchaseOrderApprovals")
	WebElement tbpPurchaseOrderApprovals;	

	@FindBy(how = How.ID, using = "tbpRequestApprovals")
	WebElement tbpRequestApprovals;
	
	@FindBy(how = How.ID, using = "tbpRequisitionApprovals")
	WebElement tbpRequisitionApprovals;	
	
	@FindBy(how = How.ID, using = "tbpStoresChargeOut")
	WebElement tbpStoresChargeOut;	

	@FindBy(how = How.ID, using = "tbpQualification")
	WebElement tbpQualification;
	
	@FindBy(how = How.ID, using = "bNewQualification")
	WebElement bNewQualification;
	
	@FindBy(how = How.ID, using = "tbpTradeCode")
	WebElement tbpTradeCode;	
	
	@FindBy(how = How.ID, using = "bNewInsurance")
	WebElement bNewInsurance;	
	
	@FindBy(how = How.ID, using = "tbpCatalogue")
	WebElement tbpCatalogue;	
	
	@FindBy(how = How.ID, using = "ddInsuranceTypeID")
	WebElement ddInsuranceTypeID;		
	
	@FindBy(how = How.ID, using = "btnConvertToUser")
	WebElement btnConvertToUser;	
	
	@FindBy(how = How.ID, using = "bDetails")
	WebElement bDetails;			
	
	@FindBy(how = How.CSS, using = "#ContactContacts_ButtonSection > div.MEXbuttonBar_Section_Holder > #bAddPerson > img[alt='Duplicate']")
	WebElement bAddPersonChildrenTab;		
	
	@FindBy(how = How.ID, using = "bMakeContactInactive")
	WebElement bMakeContactInactive;
	
	@FindBy(how = How.ID, using = "bMakeContactActive")
	WebElement bMakeContactActive;	
	
	@FindBy(how = How.ID, using = "bAssignContact")
	WebElement bAssignContact;	

	@FindBy(how = How.ID, using = "btnEmployee")
	WebElement btnEmployee;
	
	@FindBy(how = How.ID, using = "bNewTradeCode")
	WebElement bNewTradeCode;
	
	// Add Contact form
	@FindBy(how = How.ID, using = "bAdd")
	WebElement bAdd;	

	@FindBy(how = How.ID, using = "chkUserMexUser")
	WebElement chkUserMexUser;	
	
	@FindBy(how = How.ID, using = "chkUserMexOpsUser")
	WebElement chkUserMexOpsUser;
	
	@FindBy(how = How.ID, using = "chkUserMexContractorPortalUser")
	WebElement chkUserMexContractorPortalUser;	

	@FindBy(how = How.ID, using = "chkPrimaryContact")
	WebElement chkPrimaryContact;
	
	@FindBy(how = How.ID, using = "chkActive")
	WebElement chkActive;
	
	@FindBy(how = How.ID, using = "chkDepartment")
	WebElement chkDepartment;
	
	@FindBy(how = How.ID, using = "chkEmailToApprove")
	WebElement chkEmailToApprove;
	
	@FindBy(how = How.ID, using = "btnCreateUser")
	WebElement btnCreateUser;
	
	// Option Management - Logon Name
	@FindBy(how = How.ID, using = "txtLogonName")
	WebElement txtLogonName;
	
	/*
	 * Tabs click
	 */
	// Click on Details tab
	public void clickDetailsTab(){
		objGlobalFunc.waitForClickable("tbpDetails");
		tbpDetails.click();
	}
	
	// Click on Documents tab
	public void clickDocumentsTab(){
		objGlobalFunc.waitForClickable("tbpDocuments");
		tbpDocuments.click();
	}	

	// Click on Children tab
	public void clickChildrenTab(){
			objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
			tbpContacts.click();				
	}	
	
	// Click on Insurance tab
	public void clickInsuranceTab(){
		objGlobalFunc.waitForClickable("tbpInsurance");
		tbpInsurance.click();
	}	

	// Click on Catalogue tab
	public void clickCatalogueTab(){
		objGlobalFunc.waitForClickable("tbpCatalogue");
		tbpCatalogue.click();
	}		
	
	// Click on MEX User tab
	public void clickMEXUserTab(){
		objGlobalFunc.waitForClickable("tbpMexUser");
		Actions action = new Actions(driver);
		action.moveToElement(tbpMexUser).click().perform();
	}	

	// Click on Purchase Order Approvals tab
	public void clickPurchaseOrderApprovalsTab(){
		objGlobalFunc.waitForClickable("tbpPurchaseOrderApprovals");
		tbpPurchaseOrderApprovals.click();
	}	
	
	// Click on Request Approvals tab
	public void clickRequestApprovalsTab(){
		objGlobalFunc.waitForClickable("tbpRequestApprovals");
		tbpRequestApprovals.click();
	}
	
	// Click on Requisition Approvals tab
	public void clickRequisitionApprovalsTab(){
		objGlobalFunc.waitForClickable("tbpRequisitionApprovals");
		tbpRequisitionApprovals.click();
	}
	
	// Click on Customer Stores Charge Out tab
	public void clickStoresChargeOutTab(){
		objGlobalFunc.waitForClickable("tbpStoresChargeOut");
		tbpStoresChargeOut.click();
	}	

	// Click on Qualification tab
	public void clickQualificationTab(){
		objGlobalFunc.waitForClickable("tbpQualification");
		tbpQualification.click();
	}		
	
	// Click on Trades tab
	public void clickTradesTab(){
		objGlobalFunc.waitForClickable("tbpTradeCode");
		tbpTradeCode.click();
	}
		
	/*
	 * Buttons click
	 */	
	// Click on New Insurance button
	public void clickNewInsuranceBtn(){
		objGlobalFunc.waitForClickable("bNewInsurance");
		bNewInsurance.click();
	}

	// Click on Set Contact as a User button
	public void clickSetContactAsAUserBtn(){
		objGlobalFunc.waitForClickable("btnConvertToUser");
		btnConvertToUser.click();
	}	

	// Click on Make Contact Inactive button
	public void clickMakeContactInactive(){
		objGlobalFunc.waitForElement("ContactContacts_ButtonSection");
		bMakeContactInactive.click();
	}	
	
	// Click on Make Contact Active button
	public void clickMakeContactActive(){
		objGlobalFunc.waitForElement("ContactContacts_ButtonSection");
		bMakeContactActive.click();
	}
	
	// Click on Add Person to Company button
	public void clickAddPersonToCompanyBtnChildrenTab(){
		objGlobalFunc.waitForElement("ContactContacts_ButtonSection");
		bAddPersonChildrenTab.click();
	}	
	
	// Click on Add Contact button
	public void clickAddContactBtn(){
		objGlobalFunc.waitForClickable("bAdd");
		bAdd.click();
		objGlobalFunc.waitForElement("ContactDetailsDetailsTab_ContentSection");
	}

	// Click on Add Assign button
	public void clickAssignContactBtn(){
		objGlobalFunc.waitForClickable("bAssignContact");
		bAssignContact.click();
	}
	
	/*
	// Click on Security Users button
	public void clickSecurityUsersBtn(){
		objGlobalFunc.waitForClickable("btnSecurityUsers");
		btnSecurityUsers.click();		
	}	
	*/
	
	// Click on Make into New Employee button
	public void clickMakeIntoNewEmployeeBtn(){
		objGlobalFunc.waitForClickable("btnEmployee");
		btnEmployee.click();		
	}	
	
	// Click on Trade Code - New Line button
	public void clickTradeNewLineBtn(){
		objGlobalFunc.waitForClickable("bNewTradeCode");
		bNewTradeCode.click();		
	}	
	
	// Click on Qualification - New Line button
	public void clickQualificationNewLineBtn(){
		objGlobalFunc.waitForClickable("bNewQualification");
		bNewQualification.click();		
	}	
	
	// Click on Create User button - Option Management
	public void clickCreateUserBtn(){
		objGlobalFunc.waitForClickable("btnCreateUser");
		btnCreateUser.click();		
	}	
	
	/*
	 * Checkbox click
	 */
	// Turn ON MEX Ops User checkbox
	public void clickMexUserChk(){
		objGlobalFunc.waitForClickable("chkUserMexUser");
		chkUserMexUser.click();
	}	
	
	// Turn ON MEX Ops User checkbox
	public void clickMexOpsUserChk(){
		objGlobalFunc.waitForClickable("chkUserMexOpsUser");
		chkUserMexOpsUser.click();
	}	
	
	// Turn ON Contractor Portal User checkbox
	public void clickContractorPortlUserChk(){
		objGlobalFunc.waitForClickable("chkUserMexContractorPortalUser");
		chkUserMexContractorPortalUser.click();
	}	

	// Turn OFF Active checkbox
	public void clickActiveChk(){
		objGlobalFunc.waitForClickable("chkActive");
		chkActive.click();
	}		
	
	// Turn ON Filter Notification by my department checkbox
	public void clickFilterNotificationByMyDepartmentChk(){
		objGlobalFunc.waitForClickable("chkDepartment");
		chkDepartment.click();
	}	
	
	/*
	 * Enter Value in textfields
	 */	
	// Enter Code
	public void enterCode(String strCode){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
		objGlobalFunc.setTextFieldValue(txtCode, strCode);
	}
	
	// Enter Company
	public void enterCompany(String strCompany){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
		objGlobalFunc.setTextFieldValue(txtCompany, strCompany);
	}

	// Enter First Name
	public void enterFirstName(String strFirstName, String strCallingModule){
		if(strCallingModule.equals("Contacts"))
			objGlobalFunc.waitForElement("ContactDetails_ContentHeaderSection");
		else if(strCallingModule.equals("OptionManagement")) 
			objGlobalFunc.waitForElement("UsrCtlUserOptionManagement_CreateUser_ContentSection");
			
		objGlobalFunc.setTextFieldValue(txtFirstName, strFirstName);
	}	

	// Enter Last Name
	public void enterLastName(String strLastName, String strCallingModule){
		if(strCallingModule.equals("Contacts"))
			objGlobalFunc.waitForElement("ContactDetails_ContentHeaderSection");
		else if(strCallingModule.equals("OptionManagement")) 
			objGlobalFunc.waitForElement("UsrCtlUserOptionManagement_CreateUser_ContentSection");
		objGlobalFunc.setTextFieldValue(txtLastName, strLastName);
		txtLastName.sendKeys(Keys.TAB);
	}	
	
	// Enter Work Phone
	public void enterWorkPhone(String strWorkPhone){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
		objGlobalFunc.setTextFieldValue(txtWorkPhone, strWorkPhone);
	}	

	// Enter Fax
	public void enterFax(String strFax){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
		objGlobalFunc.setTextFieldValue(txtFax, strFax);
	}		

	// Enter Email
	public void enterEmail(String strEmail){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsDetailsTab");
		objGlobalFunc.setTextFieldValue(txtEmail, strEmail);
	}		
	
	// Enter Login Name - Contacts Details
	public void enterLoginName(String strLoginName){

		objGlobalFunc.waitForElement("UsrCtlContactDetailsMEXUserTab");	
		objGlobalFunc.setTextFieldValue(txtUserLoginName, strLoginName);
	}

	// Enter Login Name - Option Management
	public void enterLoginNameOptionManagement(String strLoginName){
		objGlobalFunc.waitForElement("UsrCtlUserOptionManagement_CreateUser_ContentSection");	
		objGlobalFunc.setTextFieldValue(txtLogonName, strLoginName);
	}
	
	// Add Contact form
	// Enter First Name
	public void enterAddContactFormFirstName(String strAddContactFormFirstName){
		objGlobalFunc.waitForElement("UsrCtlContactAddChildContact");
		objGlobalFunc.setTextFieldValue(txtAddContactFormFirstName, strAddContactFormFirstName);
	}

	// Enter Last Name
	public void enterAddContactFormLastName(String strAddContactFormLastName){
		objGlobalFunc.waitForElement("UsrCtlContactAddChildContact");
		objGlobalFunc.setTextFieldValue(txtAddContactFormLastName, strAddContactFormLastName);
	}	
	// END Add Contact form
	
	// Enter Account Code
	public void enterAccountCode(String strDropDownValue){
		WebElement kendoDropDown = driver.findElement(By.xpath("//div[@id='grpMiscellaneous']"));
        
        WebElement kendoDropDownArrow = kendoDropDown.findElement(By.cssSelector("span.k-select"));
        kendoDropDownArrow.click();
        
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ddAccountCode-list']"))); 

        objGlobalFunc.setKendoDropDownValue("div#ddAccountCode-list ul#ddAccountCode_listbox li.k-item", strDropDownValue);
	}
	
	// Enter Catalogue Markup%
	public void enterCatalogueMarkup(String strFieldValue){
		objGlobalFunc.waitForElement("bulkAddSection");
		jse.executeScript("$('#txtCataloguedMarkupPercent').siblings('input:visible').focus();");
		objGlobalFunc.setTextFieldValue(txtCataloguedMarkupPercent, strFieldValue);
	}		

	// Enter Non-Catalogue Markup%
	public void enterNonCatalogueMarkup(String strFieldValue){
		objGlobalFunc.waitForElement("bulkAddSection");
		jse.executeScript("$('#txtNonCataloguedMarkupPercent').siblings('input:visible').focus();");
		objGlobalFunc.setTextFieldValue(txtNonCataloguedMarkupPercent, strFieldValue);
	}	
	
	// Enter Trade Code
	public void enterTradeCode(String strTradeCode){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsTradeCodesTab");
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
		WebElement desiredCell = rows.get(rows.size()-1).findElement(By.tagName("td"));
        
		WebElement kendoDropDown = desiredCell.findElement(By.cssSelector("span.k-dropdown-wrap"));
		WebElement kendoDropDownArrow = kendoDropDown.findElement(By.cssSelector("span.k-select"));
		kendoDropDownArrow.click();

		objGlobalFunc.waitForPresenceOfElementLocated("//div[@id='ddTradeCodeID-list']");
		objGlobalFunc.setKendoDropDownValue("div#ddTradeCodeID-list ul#ddTradeCodeID_listbox li.k-item", strTradeCode);
		
		desiredCell.sendKeys(Keys.TAB);
	}

	// Enter Qualification
	public void enterQualification(String strQualification){
		objGlobalFunc.waitForElement("UsrCtlContactDetailsQualificationTab");
		
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='k-selectable']/tbody/tr"));
		WebElement desiredCell = rows.get(rows.size()-1).findElement(By.tagName("td"));
		
		WebElement kendoDropDown = desiredCell.findElement(By.cssSelector("span.k-dropdown-wrap"));
		//WebElement kendoDropDown = desiredCell.findElement(By.xpath("//span[@class='k-widget k-combobox k-header']/span"));
		WebElement kendoDropDownArrow = kendoDropDown.findElement(By.cssSelector("span.k-select"));
		
		//kendoDropDown.click();
		kendoDropDownArrow.click();

		objGlobalFunc.waitForPresenceOfElementLocated("//div[@id='ddQualificationID-list']");
		objGlobalFunc.setKendoDropDownValue("div#ddQualificationID-list ul#ddQualificationID_listbox li.k-item", strQualification);
		
		desiredCell.sendKeys(Keys.TAB);
	}	
	
	/*
	 * Get Value from textfields
	 */	
	//Get Code
	public String getCode(){
		objGlobalFunc.waitForElement("grpDetails");
	    return objGlobalFunc.getTextFieldValue(txtCode);
	}	
	
	//Get First Name
	public String getFirstName(){
		objGlobalFunc.waitForElement("grpDetails");
		return objGlobalFunc.getTextFieldValue(txtFirstName);
	}
	
	//Get Last Name
	public String getLastName(){
		objGlobalFunc.waitForElement("grpDetails");
		return objGlobalFunc.getTextFieldValue(txtLastName);
	}	
	
	//Get Work Phone
	public String getWorkPhone(){
		objGlobalFunc.waitForElement("grpDetails");
		return objGlobalFunc.getTextFieldValue(txtWorkPhone);
	}

	//Get Fax
	public String getFax(){
		objGlobalFunc.waitForElement("grpDetails");
		return objGlobalFunc.getTextFieldValue(txtFax);
	}

	//Get Email
	public String getEmail(){
		objGlobalFunc.waitForElement("grpDetails");
		return objGlobalFunc.getTextFieldValue(txtEmail);
	}	

	//Get Account Code
	public String getAccountCode(){
		objGlobalFunc.waitForElement("grpMiscellaneous");
		
		WebElement kendoDropDown = driver.findElement(By.xpath("//div[@id='grpMiscellaneous']"));
		WebElement kendoDropDownInput = kendoDropDown.findElement(By.className("k-input"));
		
		String strSelectedAccountCode = (String)jse.executeScript("return arguments[0].value", kendoDropDownInput);
		return strSelectedAccountCode; 
	}
	
	/*
	 * Verification
	 */
	// Verify PO, Request and Requisitions Approvals Tab is opened
	public boolean verifyApprovalsTabOpened(){
		if (objGlobalFunc.verifyElementIsFound(rdbApproveAll)){
			System.out.println("I can approval all radio button is found ");
			return true;
		}
		else
		{
			System.out.println("I can approval all radio button is NOT found ");
			return false;
		}
	}

	// Verify Contractor User Portal checkbox is ticked
	public boolean verifyContractorUserPortalChk(){
		if (objGlobalFunc.verifyCheckboxIsTicked(chkUserMexContractorPortalUser)){
			System.out.println("Contractor User Portal checkbox is ticked");
			return true;
		}
		else
		{
			System.out.println("Contractor User Portal checkbox is NOT ticked");
			return false;
		}
	}
	
	// Verify primary contact checkbox is ticked
	public boolean verifyPrimaryContactChk(){
		if (objGlobalFunc.verifyCheckboxIsTicked(chkPrimaryContact)){
			System.out.println("Primary Contact checkbox is ticked");
			return true;
		}
		else
		{
			System.out.println("Primary Contact checkbox is NOT ticked");
			return false;
		}
	}
	
	// Verify Contractor User Portal checkbox is ticked
	public boolean verifyFilterNotificationByMyDepartmentChk(){
		if (objGlobalFunc.verifyCheckboxIsTicked(chkDepartment)){
			System.out.println("Filter Notification By My Department checkbox is ticked");
			return true;
		}
		else
		{
			System.out.println("Filter Notification By My Department checkbox is NOT ticked");
			return false;
		}
	}

	// Verify Make Contact Active is enabled
	public boolean verifyMakeContactActiveBtn(){
		//if (bMakeContactActive.isEnabled()){
		if (bMakeContactActive.isDisplayed()){
			System.out.println("Make Contact Active button is enabled.");
			return true;
		}
		else
		{
			System.out.println("Make Contact Active button is disabled.");
			return false;
		}
	}
	
	// Verify Email me when I am required to approve a xxx checkbox is ticked
	public boolean verifyEmailMeWhenIAmRequiredToApproveChk(){
		if (objGlobalFunc.verifyCheckboxIsTicked(chkEmailToApprove)){
			System.out.println("Email Me When I am Required to Approve checkbox is ticked");
			return true;
		}
		else
		{
			System.out.println("Email Me When I am Required to Approve checkbox is NOT ticked");
			return false;
		}
	}	
	
	// Verify Purchase Order Approval tab is shown
	public boolean verifyPOApprovalTabIsShown(){
		if (tbpPurchaseOrderApprovals.isDisplayed()){
			System.out.println("PO Approval tab is shown.");
			return true;
		}
		else
		{
			System.out.println("PO Approval tab is hidden.");
			return false;
		}
	}
	
	// Verify Request Approval tab is shown
	public boolean verifyRequestApprovalTabIsShown(){
		if (tbpRequestApprovals.isDisplayed()){
			System.out.println("Request Approval tab is shown.");
			return true;
		}
		else
		{
			System.out.println("Request Approval tab is hidden.");
			return false;
		}
	}
	
	// Verify Requisition Approval tab is shown
	public boolean verifyRequisitionApprovalTabIsShown(){
		if (tbpRequisitionApprovals.isDisplayed()){
			System.out.println("Requisition Approval tab is shown.");
			return true;
		}
		else
		{
			System.out.println("Requisition Approval tab is hidden.");
			return false;
		}
	}
	
	public void selectEmployee(String strSelectEmployee)
	{
		for(int i=0; i<2; i++){
			try{
				WebElement element = driver.findElement(By.xpath("//tr/td/div[contains(text(),'" + strSelectEmployee + "')]"));
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
