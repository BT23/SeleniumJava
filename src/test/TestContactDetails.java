package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import pages.ContactDetails;
import pages.ControlFiles;
import pages.GlobalFunctions;
import pages.Catalogue;
import pages.CatalogueDetails;

import pages.DatabaseConnector;

public class TestContactDetails {
	WebDriver driver;
	
	ContactDetails objContactDetails;
	ControlFiles objControlFiles;
	GlobalFunctions objGlobalFunc;
	Catalogue objCatalogue;
	CatalogueDetails objCatalogueDetails;
	DatabaseConnector objDBConnector;
	
	private String baseUrl = "http://localhost/mexdata"; 
	//private String baseUrl = "http://localhost/build68";
	//private String baseUrl = "http://192.168.0.96/mexdata";
	
//	@BeforeTest(groups = {"General", "Regression"})
	@BeforeMethod(alwaysRun = true)
	public void setup() {
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");

        // Disable Chrome Developer Mode Extension
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");

		driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        driver.get(baseUrl);

		objGlobalFunc = new GlobalFunctions(driver);
		objContactDetails = new ContactDetails(driver);
		objControlFiles = new ControlFiles(driver);
		objCatalogue = new Catalogue(driver);
		objCatalogueDetails = new CatalogueDetails(driver);
		
		objDBConnector = new DatabaseConnector();
		// Turn OFF Security Switch
		objDBConnector.executeSQLQuery("update systemoption set IsSecurityOn = '0'");
		
		objGlobalFunc.selectEnglishLanguage();
	}

	/*
	 * Regression Test Pack: Contact - All contacts - Supplier
	 * Test Case: CAC001 - No way to make inactive child contact become active again
	 * Expected Result: Active checkbox against child contact is unticked. Then, the checkbox should be ticked again
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC001_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objGlobalFunc.selectItemOnListing("Bryan Byrt");
		objControlFiles.clickDetailsBtn();
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001S");
		objContactDetails.enterAddContactFormLastName("Supplier");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickChildrenTab();
		objGlobalFunc.selectItemOnListing("CAC001S");
		objContactDetails.clickMakeContactInactive();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.selectItemOnListing("CAC001S");
		System.out.println("Verifying Active checkbox 1:" + objGlobalFunc.getGridColumnCheckboxValue("CAC001S", 10));
		Assert.assertEquals(objGlobalFunc.getGridColumnCheckboxValue("CAC001S", 10), false);
		System.out.println("Verifying Make Contact Active 1:" + objContactDetails.verifyMakeContactActiveBtn());
		Assert.assertEquals(objContactDetails.verifyMakeContactActiveBtn(), true);
		objGlobalFunc.selectItemOnListing("CAC001S");
		objContactDetails.clickMakeContactActive();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.selectItemOnListing("CAC001S");
		System.out.println("Verifying Active checkbox 2:" + objGlobalFunc.getGridColumnCheckboxValue("CAC001S", 10));
		Assert.assertEquals(objGlobalFunc.getGridColumnCheckboxValue("CAC001S", 10), true);
	}

	/*
	 * Regression Test Pack: Contact - All contacts - Customer
	 * Test Case: CAC001 - No way to make inactive child contact become active again
	 * Expected Result: Active checkbox against child contact is unticked. Then, the checkbox should be ticked again
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC001_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objGlobalFunc.selectItemOnListing("BTC");
		objControlFiles.clickDetailsBtn();
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001C");
		objContactDetails.enterAddContactFormLastName("Customer");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickChildrenTab();
		objGlobalFunc.selectItemOnListing("CAC001C");
		objContactDetails.clickMakeContactInactive();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.selectItemOnListing("CAC001C");
		System.out.println("Verifying Active checkbox 1:" + objGlobalFunc.getGridColumnCheckboxValue("CAC001C", 10));
		Assert.assertEquals(objGlobalFunc.getGridColumnCheckboxValue("CAC001C", 10), false);
		System.out.println("Verifying Make Contact Active 1:" + objContactDetails.verifyMakeContactActiveBtn());
		Assert.assertEquals(objContactDetails.verifyMakeContactActiveBtn(), true);
		objGlobalFunc.selectItemOnListing("CAC001C");
		objContactDetails.clickMakeContactActive();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.selectItemOnListing("CAC001C");
		System.out.println("Verifying Active checkbox 2:" + objGlobalFunc.getGridColumnCheckboxValue("CAC001C", 10));
		Assert.assertEquals(objGlobalFunc.getGridColumnCheckboxValue("CAC001C", 10), true);
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - SUPPLIER
	 * Test Case: CAC002 - Can't set trade codes for child contacts of contractors (Supplier)
	 * Expected Result: Trade Code = Contractor should be attached to child contact not parent company
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC002_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CAC002S");
		objContactDetails.enterCompany("CAC002 Parent Supplier");
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001_C1S");
		objContactDetails.enterAddContactFormLastName("CAC001 Child 1S");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001_C2S");
		objContactDetails.enterAddContactFormLastName("CAC001 Child 2S");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickTradesTab();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");
		objGlobalFunc.clickCloseButton();
		objContactDetails.clickChildrenTab();
		Assert.assertEquals(objGlobalFunc.getGridColumnGridLabelValue("CAC001_C2S", 2), "BonTrade");
	}

	/*
	 * Regression Test Pack: Contact - All contacts - CUSTOMER
	 * Test Case: CAC002 - Can't set trade codes for child contacts of contractors (Supplier)
	 * Expected Result: Trade Code = Contractor should be attached to child contact not parent company
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC002_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CAC002C");
		objContactDetails.enterCompany("CAC002 Parent Customer");
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001_C1C");
		objContactDetails.enterAddContactFormLastName("CAC001 Child 1 C");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickChildrenTab();
		objContactDetails.clickAddPersonToCompanyBtnChildrenTab();
		objContactDetails.enterAddContactFormFirstName("CAC001_C2C");
		objContactDetails.enterAddContactFormLastName("CAC001 Child 2C");
		objContactDetails.clickAddContactBtn();
		objContactDetails.clickTradesTab();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");
		objGlobalFunc.clickCloseButton();
		objContactDetails.clickChildrenTab();
		Assert.assertEquals(objGlobalFunc.getGridColumnGridLabelValue("CAC001_C2C", 2), "BonTrade");
	}	
	
	
	/*
	 * Regression Test Pack: Contact - All contacts - SUPPLIER
	 * Test Case: CAC003 - supplier details not saving completely if first name entered on initial record creation.
	 * Expected Result: All entered values are retained
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC003_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CAC003S");
		objContactDetails.enterFirstName("Supplier", "Contacts");
		objContactDetails.enterWorkPhone("07 3392 4777");
		objContactDetails.enterFax("07 3392 4888");
		objContactDetails.enterEmail("supplier@mex.com.au");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CAC003S");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		System.out.println(objContactDetails.getCode());
		Assert.assertEquals(objContactDetails.getCode(), "CAC003S");
		System.out.println(objContactDetails.getFirstName());
		Assert.assertEquals(objContactDetails.getFirstName(), "Supplier");
		Assert.assertEquals(objContactDetails.getWorkPhone(), "07 3392 4777");
		Assert.assertEquals(objContactDetails.getFax(), "07 3392 4888");
		Assert.assertEquals(objContactDetails.getEmail(), "supplier@mex.com.au");
		objGlobalFunc.clickCloseButton();
	}	

	/*
	 * Regression Test Pack: Contact - All contacts - CUSTOMER
	 * Test Case: CAC003 - supplier details not saving completely if first name entered on initial record creation.
	 * Expected Result: All entered values are retained
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC003_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CAC003C");
		objContactDetails.enterFirstName("Customer", "Contacts");
		objContactDetails.enterWorkPhone("07 3392 4777");
		objContactDetails.enterFax("07 3392 4888");
		objContactDetails.enterEmail("customer@mex.com.au");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("CAC003C");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		System.out.println(objContactDetails.getCode());
		Assert.assertEquals(objContactDetails.getCode(), "CAC003C");
		System.out.println(objContactDetails.getFirstName());
		Assert.assertEquals(objContactDetails.getFirstName(), "Customer");
		Assert.assertEquals(objContactDetails.getWorkPhone(), "07 3392 4777");
		Assert.assertEquals(objContactDetails.getFax(), "07 3392 4888");
		Assert.assertEquals(objContactDetails.getEmail(), "customer@mex.com.au");
		objGlobalFunc.clickCloseButton();
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - FREIGHT
	 * Test Case: CAC003 - supplier details not saving completely if first name entered on initial record creation.
	 * Expected Result: All entered values are retained
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC003_Freight() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openFreightListing();
		objControlFiles.clickNewBtn();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterCode("CAC003F");
		objContactDetails.enterFirstName("Freight", "Contacts");
		objContactDetails.enterWorkPhone("07 3392 4777");
		objContactDetails.enterFax("07 3392 4888");
		objContactDetails.enterEmail("freight@mex.com.au");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("CAC003F");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		System.out.println(objContactDetails.getCode());
		Assert.assertEquals(objContactDetails.getCode(), "CAC003F");
		System.out.println(objContactDetails.getFirstName());
		Assert.assertEquals(objContactDetails.getFirstName(), "Freight");
		Assert.assertEquals(objContactDetails.getWorkPhone(), "07 3392 4777");
		Assert.assertEquals(objContactDetails.getFax(), "07 3392 4888");
		Assert.assertEquals(objContactDetails.getEmail(), "freight@mex.com.au");
		objGlobalFunc.clickCloseButton();
	}

	/*
	 * Regression Test Pack: Contact - All contacts - UNASSIGNED
	 * Test Case: CAC003 - supplier details not saving completely if first name entered on initial record creation.
	 * Expected Result: All entered values are retained
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC003_Unassigned() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openContactListing();
		objControlFiles.clickUnassignedRadioBtn();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterFirstName("CAC003U", "Contacts");
		objContactDetails.enterLastName("Unassigned", "Contacts");
		objContactDetails.enterWorkPhone("07 3392 4777");
		objContactDetails.enterFax("07 3392 4888");
		objContactDetails.enterEmail("unassigned@mex.com.au");
		objContactDetails.clickMEXUserTab();
		objContactDetails.enterLoginName("CAC003U");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("CAC003U");
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		System.out.println(objContactDetails.getFirstName());
		Assert.assertEquals(objContactDetails.getFirstName(), "CAC003U");
		System.out.println(objContactDetails.getLastName());
		Assert.assertEquals(objContactDetails.getLastName(), "Unassigned");
		Assert.assertEquals(objContactDetails.getWorkPhone(), "07 3392 4777");
		Assert.assertEquals(objContactDetails.getFax(), "07 3392 4888");
		Assert.assertEquals(objContactDetails.getEmail(), "unassigned@mex.com.au");
		objGlobalFunc.clickCloseButton();
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - EMPLOYEE
	 * Test Case: CAC003 - supplier details not saving completely if first name entered on initial record creation.
	 * Expected Result: All entered values are retained
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC003_Employee() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openEmployeeListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterFirstName("CAC003E", "Contacts");
		objContactDetails.enterLastName("Employee", "Contacts");
		objContactDetails.enterWorkPhone("07 3392 4777");
		objContactDetails.enterFax("07 3392 4888");
		objContactDetails.enterEmail("employee@mex.com.au");
		objContactDetails.clickTradesTab();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CAC003E Employee");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objGlobalFunc.waitForPageLoaded();
		System.out.println(objContactDetails.getFirstName());
		Assert.assertEquals(objContactDetails.getFirstName(), "CAC003E");
		System.out.println(objContactDetails.getLastName());
		Assert.assertEquals(objContactDetails.getLastName(), "Employee");
		Assert.assertEquals(objContactDetails.getWorkPhone(), "07 3392 4777");
		Assert.assertEquals(objContactDetails.getFax(), "07 3392 4888");
		Assert.assertEquals(objContactDetails.getEmail(), "employee@mex.com.au");
		objGlobalFunc.clickCloseButton();
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - SUPPLIER
	 * Test Case: CAC004 - Account Code ID is shown after clicking on another tabs
	 * Expected Result: Account Code name should be populated not Account Code ID
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC004_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objGlobalFunc.selectItemOnListing("Bryan Byrt");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterAccountCode("NAB");
		objContactDetails.clickDocumentsTab();
		objContactDetails.clickDetailsTab();
		Assert.assertEquals(objContactDetails.getAccountCode(), "NAB");
	}

	/*
	 * Regression Test Pack: Contact - All contacts - CUSTOMER
	 * Test Case: CAC004 - Account Code ID is shown after clicking on another tabs
	 * Expected Result: Account Code name should be populated not Account Code ID
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC004_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objGlobalFunc.selectItemOnListing("CSIRO");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterAccountCode("HSBC");
		objContactDetails.clickDocumentsTab();
		objContactDetails.clickDetailsTab();
		Assert.assertEquals(objContactDetails.getAccountCode(), "HSBC");
	}	

	/*
	 * Regression Test Pack: Contact - All contacts - Freight Methods
	 * Test Case: CAC004 - Account Code ID is shown after clicking on another tabs
	 * Expected Result: Account Code name should be populated not Account Code ID
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC004_Freight() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openFreightListing();
		objGlobalFunc.selectItemOnListing("DHL");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterAccountCode("Commonwealth");
		objContactDetails.clickDocumentsTab();
		objContactDetails.clickDetailsTab();
		Assert.assertEquals(objContactDetails.getAccountCode(), "Commonwealth");
	}		

	/*
	 * Regression Test Pack: Contact - All contacts - Employee
	 * Test Case: CAC004 - Account Code ID is shown after clicking on another tabs
	 * Expected Result: Account Code name should be populated not Account Code ID
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC004_Employee() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openEmployeeListing();
		objGlobalFunc.selectItemOnListing("Anthony Weaver");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterAccountCode("NAB");
		objContactDetails.clickDocumentsTab();
		objContactDetails.clickDetailsTab();
		Assert.assertEquals(objContactDetails.getAccountCode(), "NAB");
	}	

	/*
	 * Regression Test Pack: Contact - All contacts - Unassigned
	 * Test Case: CAC004 - Account Code ID is shown after clicking on another tabs
	 * Expected Result: Account Code name should be populated not Account Code ID
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC004_Unassigned() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openContactListing();
		objControlFiles.clickUnassignedRadioBtn();
		objGlobalFunc.selectItemOnListing("B");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterAccountCode("HSBC");
		objContactDetails.clickDocumentsTab();
		objContactDetails.clickDetailsTab();
		Assert.assertEquals(objContactDetails.getAccountCode(), "HSBC");
	}		

	/*
	 * Regression Test Pack: Contact - All contacts - SUPPLIER
	 * Test Case: CAC005 - Range search on supplier listing will load child contacts even if option is not checked. 
	 * makes listing appear to show duplicates of each line
	 * Expected Result: Only parent suppliers should be shown on the listing. Child contacts should not be shown.
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC005_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickRangeBtn();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickRangeClearBtn();
		objControlFiles.clickRangeApplyBtn();
		System.out.println(objGlobalFunc.getNoOfRecordsOnSearchedItem("Microsoft"));
		Assert.assertTrue(objGlobalFunc.getNoOfRecordsOnSearchedItem("Microsoft")==1, "Wrong!! Child Contacts are shown on the listing too!");
	}		

	/*
	 * Regression Test Pack: Contact - All contacts - CUSTOMER
	 * Test Case: CAC005 - Range search on supplier listing will load child contacts even if option is not checked. 
	 * makes listing appear to show duplicates of each line
	 * Expected Result: Only parent suppliers should be shown on the listing. Child contacts should not be shown.
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC005_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickRangeBtn();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickRangeClearBtn();
		objControlFiles.clickRangeApplyBtn();
		System.out.println(objGlobalFunc.getNoOfRecordsOnSearchedItem("BTC"));
		Assert.assertTrue(objGlobalFunc.getNoOfRecordsOnSearchedItem("BTC")==1, "Wrong!! Child Contacts are shown on the listing too!");
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - FREIGHT
	 * Test Case: CAC005 - Range search on supplier listing will load child contacts even if option is not checked. 
	 * makes listing appear to show duplicates of each line
	 * Expected Result: Only parent suppliers should be shown on the listing. Child contacts should not be shown.
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC005_Freight() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openFreightListing();
		objControlFiles.clickRangeBtn();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickRangeClearBtn();
		objControlFiles.clickRangeApplyBtn();
		System.out.println(objGlobalFunc.getNoOfRecordsOnSearchedItem("DHL"));
		Assert.assertTrue(objGlobalFunc.getNoOfRecordsOnSearchedItem("DHL")==1, "Wrong!! Child Contacts are shown on the listing too!");
	}		
	
	/*
	 * Regression Test Pack: Contact - All contacts - SUPPLIER
	 * Test Case: CAC006 - Cannot delete a new supplier from the supplier listing.
	 * Expected Result: Supplier should be deleted and removed from listing 
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC006_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("CAC007S");
		objContactDetails.enterCompany("Please Kill Me");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CAC007S");
		objControlFiles.clickDeleteBtn();
		objGlobalFunc.clickYestoMessageBox();
		System.out.println("TEST PASSED IF No Such Item on listing message is returned.");
		objGlobalFunc.selectItemOnListing("CAC007S");
	}	
	
	/*
	 * Regression Test Pack: Contact - All contacts - CUSTOMER
	 * Test Case: CAC006 - Cannot delete a new supplier from the supplier listing.
	 * Expected Result: Customer should be deleted and removed from listing 
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC006_Customer() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CAC007C");
		objContactDetails.enterCompany("Please Kill Me");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CAC007C");
		objControlFiles.clickDeleteBtn();
		objGlobalFunc.clickYestoMessageBox();
		System.out.println("TEST PASSED IF No Such Item on listing message is returned.");
		objGlobalFunc.selectItemOnListing("CAC007C");
	}
	
	/*
	 * Regression Test Pack: Contact - All contacts - Freight Methods
	 * Test Case: CAC006 - Cannot delete a new supplier from the supplier listing.
	 * Expected Result: Freight should be deleted and removed from listing 
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "All Contacts"})
	public void test_CAC006_Freight() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openFreightListing();
		objControlFiles.clickNewBtn();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterCode("CAC007F");
		objContactDetails.enterCompany("Please Kill Me");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CAC007F");
		objControlFiles.clickDeleteBtn();
		objGlobalFunc.clickYestoMessageBox();
		System.out.println("TEST PASSED IF No Such Item on listing message is returned.");
		objGlobalFunc.selectItemOnListing("CAC007F");
	}		
	
	/*
	 * Regression Test Pack: Contact - Suppliers
	 * Test Case: CS001 - When adding insurance to a contact, if you have a blank line it will not save the other lines
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Contacts", "Suppliers"})
	public void test_CS001_Supplier() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objGlobalFunc.selectItemOnListing("Microsoft");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickInsuranceTab();
		objContactDetails.clickNewInsuranceBtn();
		objGlobalFunc.clickCloseButton();
		System.out.println(objGlobalFunc.getMessageBoxContent());
		Assert.assertEquals(objGlobalFunc.getMessageBoxContent(), "Insurance Type required. Would you like to enter an Insurance Type?");		
		objGlobalFunc.clickNotoMessageBox();
		objGlobalFunc.clickCloseButton();
	}

	/*
	 * Regression Test Pack: Contact - Suppliers
	 * Test Case: CS002 - ‘Create User’ message box is shown after clicking on PO Approvals tab, 
	 * Request Approvals tab and Requisition Approvals tab
	 * Remark: Automation test completed
	 */	
	//@Test(groups = {"Contacts", "Suppliers"})
	public void test_CS002() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("CS002");
		objContactDetails.enterCompany("CS002 Company");
		objContactDetails.clickMEXUserTab();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickSetContactAsAUserBtn();
		objGlobalFunc.clickYestoMessageBox();
		objContactDetails.enterLoginName("CS002");
		objContactDetails.clickPurchaseOrderApprovalsTab();
		Assert.assertEquals(objContactDetails.verifyApprovalsTabOpened(), true);
		objContactDetails.clickRequestApprovalsTab();
		Assert.assertEquals(objContactDetails.verifyApprovalsTabOpened(), true);
		objContactDetails.clickRequisitionApprovalsTab();
		Assert.assertEquals(objContactDetails.verifyApprovalsTabOpened(), true);
	}
	
	/*
	 * Regression Test Pack: Contact - Suppliers
	 * Test Case: CS003 - Supplier cannot be turned into Contractor Portal UserApprovals tab
	 * Remark: Automation test completed
	 */	
	
	//@Test(groups = {"Contacts", "Suppliers"})
	public void test_CS003() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("CS003A");
		objContactDetails.enterCompany("CS003 Company");
		objContactDetails.clickMEXUserTab();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickSetContactAsAUserBtn();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterLoginName("CS003A");
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickContractorPortlUserChk();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CS003A");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickMEXUserTab();
		Assert.assertEquals(objContactDetails.verifyContractorUserPortalChk(), true);
	}
	
	/*
	 * Regression Test Pack: Contact - Suppliers
	 * Test Case: CS004 - Suppliers can be added with a duplicate Supplier Code
	 * Remark: Automation test completed
	 */	
	//@Test(groups = {"Contacts", "Suppliers"})
	public void test_CS004() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("Google");
		objContactDetails.enterCompany("CS004 Duplicate Supplier Code");
		objGlobalFunc.clickCloseButton();
		System.out.println(objGlobalFunc.getMessageBoxContent());
		Assert.assertEquals(objGlobalFunc.getMessageBoxContent(), "Unique Code required. Exit without saving?", "Uniquie Login message box is NOT shown.");
		objGlobalFunc.clickYestoMessageBox();
	}
	
	/*
	 * Regression Test Pack: Contact - Suppliers
	 * Test Case: CS005 - Making a supplier inactive and then active again removes any associated catalogue items from the catalogue tab of the supplier details. 
	 * Remark: Automation test completed
	 */	
	//@Test(groups = {"Contacts", "Suppliers"})
	public void test_CS005() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		
		objControlFiles.clickNewBtn();
		objContactDetails.enterCode("CS005C");
		objContactDetails.enterCompany("CS005 Company");		
		// Close Supplier Details form
		objGlobalFunc.clickCloseButton();
		
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
		
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("CS005 Item C");
		objCatalogueDetails.enterPartName("CS005 Catalogue Item");
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickNewSupplierTabBtn();
		objCatalogueDetails.enterMultipleSupplier("CS005C");
		objCatalogueDetails.clickDetailsTab();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseBreadcrumbButton();
		objGlobalFunc.waitForPageLoaded();
		
		// Close Store Menu
		objGlobalFunc.closeStoreMenu();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openSupplierListing();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.selectItemOnListing("CS005C");
		objControlFiles.clickDetailsBtn();
		
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickActiveChk();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickCatalogueTab();
		objGlobalFunc.verifyGridColumnTableIsEmpty();
	}
	
	/*
	 * Regression Test Pack: Contact - Customers
	 * Test Case: CC001 - Primary Contact checkbox is turned on automatically 
	 * after entering Catalogued Markup %
	 * Remark: Automation test completed	 * 
	 */	
	//@Test(groups = {"Contacts", "Customers"})
	public void test_CC001A() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("CC001A");
		objContactDetails.enterCompany("CC001A Company");
		Assert.assertEquals(objContactDetails.verifyPrimaryContactChk(), false);
		objContactDetails.clickStoresChargeOutTab();
		objContactDetails.enterCatalogueMarkup("10");
		Assert.assertEquals(objContactDetails.verifyPrimaryContactChk(), false);
		objGlobalFunc.clickCloseButton();
	}	

	/*
	 * Regression Test Pack: Contact - Customers
	 * Test Case: CC001 - Primary Contact checkbox is turned on automatically 
	 * after entering Non Catalogued Markup %
	 * Remark: Automation test completed	 * 
	 */	
	//@Test(groups = {"Contacts", "Customers"})
	public void test_CC001B() {
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openCustomerListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterCode("CC001B");
		objContactDetails.enterCompany("CC001B Company");
		Assert.assertEquals(objContactDetails.verifyPrimaryContactChk(), false);
		objContactDetails.clickStoresChargeOutTab();
		objContactDetails.enterNonCatalogueMarkup("15");
		Assert.assertEquals(objContactDetails.verifyPrimaryContactChk(), false);
		objGlobalFunc.clickCloseButton();
	}	
	
	/*
	 * Regression Test Pack: Contact - Security User
	 * Test Case: CSU001 - Cant save checkbox, Filter Request by department on user details. 
	 * ticking the checkbox then leaving the page causes the field to not save
	 * Expected Result: Filter Notification by my Department option should be ticked
	 * Remark: Automation test completed
	 */	

	//@Test(groups = {"Contacts", "Security User"})
	public void test_CSU001() {
		// Update DepartmentID in MexUser table (Select Department = AD in User Options)
		objDBConnector.executeSQLQuery("update mexuser set departmentid = 2 where MexUserID = 1");
		objGlobalFunc.openControlFiles();
		objControlFiles.clickSecurityUsersBtn();
		objGlobalFunc.selectItemOnListing("Administrator");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickRequestApprovalsTab();
		objContactDetails.clickFilterNotificationByMyDepartmentChk();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("Administrator");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickRequestApprovalsTab();
		objContactDetails.verifyFilterNotificationByMyDepartmentChk();
		objGlobalFunc.clickCloseButton();
	}	
	
	/*
	 * Regression Test Pack: Contact - Security User
	 * Test Case: CSU003 - When a user is selected only as a MEX Ops user, 
	 * when attempting to change notification settings, the tabs will be missing.
	 * Expected Result: Purchase Order Approvals tab, Request Approvals tab and Requisition Approvals tab should be shown
	 * Remark: Automation test completed
	 */
	
	//@Test(groups = {"Contacts", "Security User"})
	public void test_CSU003() {	
		objGlobalFunc.openControlFiles();
		objControlFiles.clickSecurityUsersBtn();
		objControlFiles.clickNewSecurityUserBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterFirstName("Test1", "SecurityUser");
		objContactDetails.clickMEXUserTab();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterLoginName("MEXOpsLad1");
		objContactDetails.clickMexOpsUserChk();
		objContactDetails.clickDetailsTab();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickAssignContactBtn();
		objContactDetails.clickMakeIntoNewEmployeeBtn();
		objGlobalFunc.clickYestoMessageBox();
		objContactDetails.clickTradesTab();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("Test1");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickMEXUserTab();
		objContactDetails.clickMexUserChk();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("Test1");
		objControlFiles.clickDetailsBtn();
		objContactDetails.verifyPOApprovalTabIsShown();
		objContactDetails.verifyRequestApprovalTabIsShown();
		objContactDetails.verifyRequisitionApprovalTabIsShown();
	}

	/*
	 * Regression Test Pack: Contact - Security User
	 * Test Case: CSU005 - WWrong side modules are assigned to a new created security user
	 * Expected Result:
	 * 1. Only  Email me when I am required to approve a xxx checkbox is ticked
	 * 2. Transactions, Logsheets and Hiring should not be shown
	 *	  Keys, Drawings, Requests and Accidents should be shown
	 * Remark:	Automation test completed.
	 * 			Side modules in User Options - Side Modules tab cannot be checked.
	 * 			Element cannot be found due to dynamic attributes
	 */
	
	//@Test(groups = {"Contacts", "Security User"})
	public void test_CSU005_SecurityUser() {
		// Create a new security user
		objGlobalFunc.openControlFiles();
		objControlFiles.clickSecurityUsersBtn();
		objControlFiles.clickNewSecurityUserBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterFirstName("CSU005", "Contacts");
		objContactDetails.enterLastName("Security User", "Contacts");
		objContactDetails.enterLoginName("CSU005");
		objGlobalFunc.clickCloseButton();
		
		// Reopen the details of the new created supplier
		objGlobalFunc.selectItemOnListing("CSU005");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		
		// Verify Email me when I am required to approve checkbox is ticked
		objContactDetails.clickPurchaseOrderApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objContactDetails.clickRequestApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objContactDetails.clickRequisitionApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objGlobalFunc.clickCloseButton();
		
		// Turn ON security system switch
		objControlFiles.clickAdministrationFunctionsTab();
		objControlFiles.clickGeneralTab();
		objControlFiles.clickSecuritySwitchBtn();
		objControlFiles.turnONSecuritySystemSwitch();
		objGlobalFunc.clickYestoMessageBox();
		
		// Login using new created username and password
		objGlobalFunc.setUserNameValue("CSU005");
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.setPasswordValue("CSU005");
		objGlobalFunc.setConfirmPasswordValue("CSU005");
		objGlobalFunc.clickOKBtnOnMEXLoginForm();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
		
		// Main Menu: Check Default side modules - Keys, Downtime, Drawings, Accidents and Requests
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.verifyKeysModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyDowntimeModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyDrawingsModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyRequestsModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyAccidentsModuleIsDisplayed("MainMenu");
	}

	/*
	 * Regression Test Pack: Contact - Option Management
	 * Test Case: CSU005 - WWrong side modules are assigned to a new created security user
	 * Expected Result:
	 * 1. Only  Email me when I am required to approve a xxx checkbox is ticked
	 * 2. Transactions, Logsheets and Hiring should not be shown
	 *	  Keys, Drawings, Requests and Accidents should be shown
	 * Remark:	Automation test completed.
	 * 			Side modules in User Options - Side Modules tab cannot be checked.
	 * 			Element cannot be found due to dynamic attributes
	 */
	//@Test(groups = {"Contacts", "Security User", "Option Management"})
	public void test_CSU005_OptionManagement() {
		
		// Create a new Security User in Option Management
		objGlobalFunc.openControlFiles();
		objControlFiles.clickOptionManagementTab();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.enterFirstName("CSU005-OPM6", "OptionManagement");
		objContactDetails.enterLastName("Option Management", "OptionManagement");
		objContactDetails.enterLoginNameOptionManagement("CSU005-OPM6");
		objContactDetails.clickCreateUserBtn();
		objGlobalFunc.waitForPageLoaded();
		
		// Open the new created security user in Option Management
		objControlFiles.clickGeneralTab();
		objControlFiles.clickSecurityUsersBtn();
		objGlobalFunc.selectItemOnListing("CSU005-OPM6");
		objControlFiles.clickDetailsBtn();
		
		objGlobalFunc.waitForPageLoaded();
		
		// Verify Email me when I am required to approve checkbox is ticked
		objContactDetails.clickPurchaseOrderApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objContactDetails.clickRequestApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objContactDetails.clickRequisitionApprovalsTab();
		objContactDetails.verifyEmailMeWhenIAmRequiredToApproveChk();
		objGlobalFunc.clickCloseButton();
		
		// Turn ON security system switch
		objControlFiles.clickAdministrationFunctionsTab();
		objControlFiles.clickGeneralTab();
		objControlFiles.clickSecuritySwitchBtn();
		objControlFiles.turnONSecuritySystemSwitch();
		objGlobalFunc.clickYestoMessageBox();		
		
		// Login using new created username and password
		objGlobalFunc.setUserNameValue("CSU005-OPM6");
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.setPasswordValue("CSU005-OPM6");
		objGlobalFunc.setConfirmPasswordValue("CSU005-OPM6");
		objGlobalFunc.clickOKBtnOnMEXLoginForm();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
		
		// Main Menu: Check Default side modules - Keys, Downtime, Drawings, Accidents and Requests
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.verifyKeysModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyDowntimeModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyDrawingsModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyRequestsModuleIsDisplayed("MainMenu");
		objGlobalFunc.verifyAccidentsModuleIsDisplayed("MainMenu");
		
		
		// User Options: Dynamic attributes cause trouble.
		/*
		objGlobalFunc.clickHamburgerMenuButton();
		objGlobalFunc.clickUserOptions();
		objGlobalFunc.clickUserOptionsSideModulesTab();
		objGlobalFunc.verifyKeysModuleIsDisplayed("UserOption");
		objGlobalFunc.verifyDowntimeModuleIsDisplayed("UserOption");
		objGlobalFunc.verifyDrawingsModuleIsDisplayed("UserOption");
		objGlobalFunc.verifyRequestsModuleIsDisplayed("UserOption");
		objGlobalFunc.verifyAccidentsModuleIsDisplayed("UserOption");
		*/
	}
	
	/*
	 * Regression Test Pack: Contact - Employee
	 * Test Case: CE001 - Setting an Employee as a user allows you to exit without assigning a username. 
	 * But still creates them as a user with a not null, blank username in the database.
	 * Expected Result: Record Validation - Unique login required. Exit without saving? Message box is shown
	 * Remark: Automation test completed
	 */
	
	//@Test(groups = {"Contacts", "Employee"})
	public void test_CE001() {	
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openEmployeeListing();
		objControlFiles.clickNewBtn();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterFirstName("CE001", "Contacts");
		objContactDetails.clickTradesTab();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");
		objContactDetails.clickMEXUserTab();
		objContactDetails.clickSetContactAsAUserBtn();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.clickCloseButton();
		System.out.println(objGlobalFunc.getMessageBoxContent());
		Assert.assertEquals(objGlobalFunc.getMessageBoxContent(), "Unique Logon required. Exit without saving?", "Uniquie Login message box is NOT shown.");		
	}	

	/*
	 * Regression Test Pack: Contact - Employee
	 * Test Case: CE002 - CE002 - Deactivating Employee doesn't give option to also deactivate Trade Codes 
	 * and Qualifications.
	 * Expected Result: Employee, Qualification and Trade Code should be inactive
	 * Remark: Automation test completed
	 */
	
	//@Test(groups = {"Contacts", "Employee"})
	public void test_CE002() {	
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objControlFiles.openEmployeeListing();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickDetailsTab();
		objContactDetails.enterFirstName("CE002-C", "Contacts");
		objContactDetails.clickQualificationTab();
		objContactDetails.clickQualificationNewLineBtn();
		objContactDetails.enterQualification("MYOB");
		objContactDetails.clickTradesTab();
		objContactDetails.clickTradeNewLineBtn();
		objContactDetails.enterTradeCode("BonTrade");		
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForClickable("grdListing");
		objControlFiles.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objContactDetails.clickActiveChk();
		objGlobalFunc.clickYestoMessageBox();
		objGlobalFunc.clickYestoMessageBox();
		//Verify Qualification and Trade Codes are inactive (i.e. no records are shown on the tab)
		System.out.println("TEST PASSED - Trade Codes tab if number of rows on grid columns listing = 0");
		objGlobalFunc.verifyGridColumnTableIsEmpty();
		objContactDetails.clickQualificationTab();
		System.out.println("TEST PASSED - Qualification tab if number of rows on grid columns listing = 0");
		objGlobalFunc.verifyGridColumnTableIsEmpty();
		objGlobalFunc.clickCloseButton();		
	}		
	
	
	//@Test(groups = {"Contacts", "Security User"})
	public void simpleTest() {	
		objGlobalFunc.openWorkOrders();
		//objControlFiles.clickControlFilesTab();
		//objControlFiles.openSupplierListing();
		//objGlobalFunc.clickCloseButton();
		//objGlobalFunc.clickHomeBreadcrumbButton();
	}
	
	@AfterTest
	public void shutDownSelenium() {
		// closes all the browser windows opened by web driver
		driver.quit();
		
		//driver.close();
	}
}
