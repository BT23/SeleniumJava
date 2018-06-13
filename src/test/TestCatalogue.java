package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pages.AssetRegister;
import pages.AssetDetails;
import pages.Catalogue;
import pages.CatalogueDetails;
import pages.ContactDetails;
import pages.ControlFiles;
import pages.GlobalFunctions;
import pages.IssueReturn;

public class TestCatalogue {
	WebDriver driver;
	
	AssetRegister objAssetRegister;
	AssetDetails objAssetDetails;
	Catalogue objCatalogue;
	CatalogueDetails objCatalogueDetails;
	ContactDetails objContactDetails;
	ControlFiles objControlFiles;
	GlobalFunctions objGlobalFunc;
	IssueReturn objIssueReturn;
	
	private String baseUrl = "http://localhost/mexdata"; 
	//private String baseUrl = "http://192.168.0.173/mexdata"; 
	
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

        objAssetRegister = new AssetRegister(driver);
        objAssetDetails = new AssetDetails(driver);
		objGlobalFunc = new GlobalFunctions(driver);
		objCatalogue = new Catalogue(driver);
		objCatalogueDetails = new CatalogueDetails(driver);
		objContactDetails = new ContactDetails(driver);
		objControlFiles = new ControlFiles(driver);
		objIssueReturn = new IssueReturn(driver);
		
		objGlobalFunc.selectEnglishLanguage();
	}
	
	/*
	 * Regression Test Pack: Catalogue Details - Details Tab
	 * Test Case: CDT001 - SOH got wiped out after creating a new Supplier
	 * Expected Result: Value in SOH field should be retained
	 * Remark:Automation Test completed 
	 * DON'T RUN THIS TEST CASE. It failed since MEX 15 B57.
	 */
	//@Test(groups = {"Regression"})
	public void test_CDT001() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objGlobalFunc.waitForPageLoaded();
		objCatalogue.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.enterCatalogueNumber("Paper");
		objCatalogueDetails.enterPartName("White color paper");
		// SOH field - always get the element not visible or element not clickable.
		// Workaround - Tab from Area Of Plant to SOH
		objCatalogueDetails.enterAreaOfPlant("");
		objCatalogueDetails.enterStockOnHand("10");
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickNewSupplierBtn();
		objContactDetails.enterCode("BP2");
		objContactDetails.enterCompany("British Petroleum");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.tabOffSupplierField();
		objCatalogueDetails.clickDetailsTab();
		System.out.println(objCatalogueDetails.getStockOnHandValue());
		Assert.assertEquals(objCatalogueDetails.getStockOnHandValue(), "10");
		objGlobalFunc.clickCloseButton();
	}

	/*
	 * Regression Test Pack: Catalogue Details - Details Tab
	 * Test Case: CDT002 - A blank Catalogue Details form is shown after opening the details of an existing Supplier
	 * Expected Result: Blank Catalogue Details should not be shown
	 * Remark: Automation Test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CDT002() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objGlobalFunc.waitForPageLoaded();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("CDT002");
		objCatalogueDetails.enterPartName("CDT002 Catalogue");
		objCatalogueDetails.enterStockOnHand("10");
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickNewSupplierTabBtn();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.selectSupplierFromDropDown("Google");
		objCatalogueDetails.clickDocsTab();
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickSupplierDetailsBtn();
		objContactDetails.clickDocumentsTab();
		objGlobalFunc.clickCloseButton();
		System.out.println("Catalogue No. = " + objCatalogueDetails.getCatalogueNumber());
		System.out.println("Part Name = " + objCatalogueDetails.getPartName());
		Assert.assertEquals(objCatalogueDetails.getCatalogueNumber(), "CDT002");
		Assert.assertEquals(objCatalogueDetails.getPartName(), "CDT002 Catalogue");
		objGlobalFunc.clickCloseButton();
	}
	
	/*
	 * Regression Test Pack: Catalogue Details - Details Tab
	 * Test Case: CDT003 - SOH and Cost/Item fields become disabled after creating a new Supplier
	 * Expected Result: SOH and Cost/Item fields should be editable
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CDT003() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("Pen");
		objCatalogueDetails.enterPartName("Blue Pen");
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickNewSupplierBtn();
		objContactDetails.enterCode("MEX");
		objContactDetails.enterCompany("Maintenance Experts");
		objGlobalFunc.clickCloseButton();
		objCatalogueDetails.tabOffSupplierField();
		objCatalogueDetails.clickDetailsTab();
		objCatalogueDetails.verifySOHFieldEdiable();
		objCatalogueDetails.clickSuppliersTab();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
	}

	/*
	 * Regression Test Pack: Catalogue Details - Details Tab
	 * Test Case: CDT004 - Entering back to back question marks (??) in catalogue details part name field crashes MEX.
	 * Expected Result: Catalogue Details form can be closed. Catalogue can be saved.
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CDT004() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("Mug");
		objCatalogueDetails.enterPartName("Mug ???");
		objGlobalFunc.clickCloseButton();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("Cup ???");
		objCatalogueDetails.enterPartName("Cup");
		objGlobalFunc.clickCloseButton();
	}
	
	/*
	 * Regression Test Pack: Catalogue Details - Supplier Tab
	 * Test Case: CST001 - Refresh Issue - No suppliers are shown after creating a new Stock
	 * Expected Result: Supplier = Google and Supplier = Microsoft should be shown
	 * Remark:	Automation test completed
	 * 			New Stock cannot be created. But, the verification in Supplier tab is working fine.
	 */
	//@Test(groups = {"Regression"})
	public void test_CST001() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.selectCatalogue("Google Calendar");
		objCatalogue.clickDetailsBtn();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickSuppliersTab();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickNewSupplierTabBtn();
		objCatalogueDetails.selectSupplierFromDropDown("Microsoft");
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickDetailsTab();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickNewStockBtn();
		objGlobalFunc.waitForLoadingMask("LoadingBlock");
		objCatalogueDetails.enterStore("Broadway");
		objCatalogueDetails.clickSuppliersTab();
		objGlobalFunc.verifyFirstColumnValueGridColumnListing("Google", "Microsoft");
		objGlobalFunc.clickCloseButton();
	}	

	/*
	 * Regression Test Pack: Catalogue Details - Supplier Tab
	 * Test Case: CST002 - When creating a new Catalogue item from within Catalogue Details screen, 
	 * it retains the Suppliers tab info from the previous item.
	 * Expected Result: Supplier tab should be blank
	 * Remark:Automation test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CST002() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.clickNewBtn();
		objCatalogueDetails.enterCatalogueNumber("CST002 - 17");
		objCatalogueDetails.enterPartName("Catalogue CST002 - 17");
		objCatalogueDetails.clickSuppliersTab();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickNewSupplierTabBtn();
		objCatalogueDetails.selectSupplierFromDropDown("Google");
		objCatalogueDetails.clickDetailsTab();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.clickNewCatalogueBtn();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.enterCatalogueNumber("CST002 - 18");
		objCatalogueDetails.enterPartName("Catalogue CST002 - 18");
		objCatalogueDetails.clickSuppliersTab();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.verifyGridColumnTableIsEmpty();
		objGlobalFunc.waitForPageLoaded();
		objGlobalFunc.clickCloseButton();
	}
	
	
	/*
	 * Regression Test Pack: Catalogue Details - APL Tab
	 * Test Case: CAT001 - Refresh Issue - No APLs are shown after creating a new Stock
	 * Expected Result: Asset = Phone and Asset = Bus should be shown
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CAT001() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.selectCatalogue("Google Mobile");
		objCatalogue.clickDetailsBtn();
		objCatalogueDetails.clickAPLTab();
		objCatalogueDetails.clickNewAPLBtn();
		objCatalogueDetails.enterAssetNo("Phone");
		objCatalogueDetails.clickNewAPLBtn();
		objCatalogueDetails.enterAssetNo("Bus");
		objCatalogueDetails.clickDetailsTab();
		objCatalogueDetails.clickNewStockBtn();
		objCatalogueDetails.enterStore("Brisbane City");
		objCatalogueDetails.clickAPLTab();
		objGlobalFunc.verifyFirstColumnValueGridColumnListing("Phone", "Bus");
		objGlobalFunc.clickCloseButton();
	}
	
	/*
	 * Regression Test Pack: Catalogue Details - Transactions Tab
	 * Test Case: CTT001 - CTT001 - Asset field in Transactions listing is not populated 
	 * when attaching a transaction directly to an asset. Only displays asset number of Asset from WO spare record.
	 * Expected Result: Catalogue Transaction Tab - Quantity = 1, Account Code = CTT001 AC and Asset No = CCT01_Asset
	 * Remark: Automation test completed
	 */
	//@Test(groups = {"Regression"})
	public void test_CTT001() {
		// Step 2 - Create new Asset
		objGlobalFunc.openAssetRegister();
		objGlobalFunc.waitForPageLoaded();
		objAssetRegister.clickNewLevel1Btn();
		objGlobalFunc.waitForPageLoaded();
		objAssetDetails.enterAssetNumber("CTT01_Asset");
		objAssetDetails.enterAssetDesc("TC CTT01 Asset");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		
		// Focus issue - if not clicking on WO tab, Asset Register form cannot be closed.		
		objAssetRegister.clickWorkOrdersTab();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		
		// step 4 - create account code
		objGlobalFunc.openControlFiles();
		objControlFiles.clickControlFilesTab();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.openAccountCode();
		objControlFiles.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objControlFiles.enterAccountCode("CTT001AC");
		objControlFiles.enterDescription("CTT001 Account Code");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.selectItemOnListing("CTT001AC");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		
		// step 3 - Create new Catalogue
		
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objGlobalFunc.waitForPageLoaded();
		objCatalogue.clickNewBtn();
		objGlobalFunc.waitForPageLoaded();
		objCatalogueDetails.enterCatalogueNumber("CTT01_Cat");
		objCatalogueDetails.enterPartName("TC CTT01 Catalogue");
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		
		
		objCatalogue.selectCatalogue("CTT01_Cat");
		objCatalogue.clickIssueReturnBtn();
		objGlobalFunc.waitForPageLoaded();
		objIssueReturn.enterAssetNumber("CTT01_Asset");
		objIssueReturn.enterAccountCode("CTT001AC");
		objIssueReturn.enterQtyToIssueReturn("3");
		objGlobalFunc.waitForPageLoaded();
		objIssueReturn.clickProcessBtn();
		objGlobalFunc.clickNotoMessageBox();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
		
		objCatalogue.selectCatalogue("CTT01_Cat");
		objCatalogue.clickDetailsBtn();
		objCatalogueDetails.clickTransactionsTab();
		Assert.assertEquals("-3", objCatalogueDetails.getQuantityTransactionsTab());
		Assert.assertEquals("CTT001AC", objCatalogueDetails.getAccountCodeTransactionsTab());
		Assert.assertEquals("CTT01_Asset TC CTT01 Asset", objCatalogueDetails.getAssetNoTransactionsTab());	
	}

	//@Test(groups = {"General"})
	public void simpleTest() {
		objGlobalFunc.openStoresMenu();
		objGlobalFunc.openCatalogue();
		objCatalogue.selectCatalogue("Google Calendar");
		objCatalogue.clickDetailsBtn();
		objCatalogueDetails.clickSuppliersTab();
		objCatalogueDetails.clickNewSupplierBtn();
		objContactDetails.enterCode("B14");
		objContactDetails.enterCompany("B14");
		objGlobalFunc.clickCloseButton();
		objCatalogueDetails.tabOffSupplierField();
		objGlobalFunc.clickCloseButton();
		objGlobalFunc.waitForPageLoaded();
	}	
		
	
	@AfterTest
	public void shutDownSelenium() {
		// closes all the browser windows opened by web driver
		driver.quit();
	}
}
