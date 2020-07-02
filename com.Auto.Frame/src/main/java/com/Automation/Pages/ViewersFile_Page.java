package com.Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Automation.Helper.MasterHelper;
import com.Automation.Util.TestBase;

public class ViewersFile_Page extends TestBase {

	MasterHelper mh = new MasterHelper(driver);
	
	@FindBy(xpath = "//div[@id='guiviewer3d-toolbar']/div[@id='navTools']")
	WebElement navTools;
	
	@FindBy(xpath = "//div[@id='guiviewer3d-toolbar']/div[@id='modelTools']/div")
	WebElement modelTools;
	
	@FindBy(xpath = "//div[@id='guiviewer3d-toolbar']/div[@id='settingsTools']/div")
	WebElement SettingTool;
	
	@FindBy(xpath = "//div[@id='toolbar-orbitTools']")
	WebElement OrbitIcon;
	
	@FindBy(xpath = "//div[@id='toolbar-panTool']")
	WebElement PanTool;
	
	@FindBy(xpath = "//div[@id='toolbar-zoomTool']")
	WebElement ZoomTool;
	
	@FindBy(xpath = "//div[@id='toolbar-bimWalkTool']")
	WebElement bimWalkTool;
	
	@FindBy(xpath = "//div[@id='toolbar-cameraSubmenuTool']")
	WebElement CameraTool;
	
	@FindBy(xpath = "//div[@id='toolbar-measurementSubmenuTool']")
	WebElement MeasureTool;
	
	@FindBy(xpath = "//div[@id='toolbar-sectionTool']")
	WebElement SecAnalysis;
	
	@FindBy(xpath = "//div[@id='toolbar-documentModels']")
	WebElement DocBrowser;
	
	@FindBy(xpath = "//div[@id='toolbar-explodeTool']")
	WebElement ExplodeTool;
	
	@FindBy(xpath = "//div[@id='toolbar-modelStructureTool']")
	WebElement ModelBrowser;
	
	@FindBy(xpath = "//div[@id='toolbar-propertiesTool']")
	WebElement Properties;
	
	@FindBy(xpath = "//div[@id='toolbar-fullscreenTool']")
	WebElement FullScreen;
	
	@FindBy(xpath = "//div[@id='toolbar-settingsTool']")
	WebElement Settings;
	
	@FindBy(xpath = "//a[@id='2D Viewer']")
	WebElement TwoDView_Tab;
	
	@FindBy(xpath = "//app-two-d-viewer/div/div")
	WebElement TwoDView_window;

	@FindBy(xpath = "//app-two-d-viewer//div[@id='toolbar-measurementSubmenuTool']")
	WebElement TD_MeasureTool;
	
	@FindBy(xpath = "//app-two-d-viewer//div[@id='toolbar-documentModels']")
	WebElement TD_DocBrowser;
	
	@FindBy(xpath = "//app-two-d-viewer//div[@id='toolbar-settingsTool']")
	WebElement TD_Settings;
	
	@FindBy(xpath = "//app-two-d-viewer//div[@id='toolbar-propertiesTool']")
	WebElement TD_Properties;
	
	@FindBy(xpath = "//app-two-d-viewer//div[@id='toolbar-fullscreenTool']")
	WebElement TD_FullScreen;
	
	@FindBy(xpath = "//app-two-d-viewer//div[@id='viewer-2D-container']/div[@class='adsk-viewing-viewer notouch dark-theme quality-text']/div[@id='lmv-document-extension_2']/div")
	WebElement DocBrowser_Window;
	
	@FindBy(xpath = "//div[@id='viewer-2D-container']//div[@class='treeview']/group/group/group/leaf[3]/lmvheader")
	WebElement SelectTD_file;
	
	public ViewersFile_Page() {

		PageFactory.initElements(driver, this);

		}
		@SuppressWarnings("static-access")
		public void user_check_the_GUI_Viewer_buttons() {
			mh.verifyElementPresent(navTools);
			mh.verifyElementPresent(modelTools);
			mh.verifyElementPresent(SettingTool);
			System.out.println("Nevigation 3D Tools");
			mh.verifyElementPresent(OrbitIcon);
			mh.verifyElementPresent(PanTool);
			mh.verifyElementPresent(ZoomTool);
			mh.verifyElementPresent(bimWalkTool);
			mh.verifyElementPresent(CameraTool);
			System.out.println("\nModel 3D Tools");
			mh.verifyElementPresent(MeasureTool);
			mh.verifyElementPresent(SecAnalysis);
			mh.verifyElementPresent(DocBrowser);
			mh.verifyElementPresent(ExplodeTool);
			System.out.println("\nSettings 3D Tools");
			mh.verifyElementPresent(ModelBrowser);
			mh.verifyElementPresent(Properties);
			mh.verifyElementPresent(FullScreen);
			mh.verifyElementPresent(Settings);

		}
		
		@SuppressWarnings("static-access")
		public void user_click_on_TwoD_view_tab() throws Throwable {
			mh.verifyElementPresent(TwoDView_Tab);
			mh.drawBorder(TwoDView_Tab, driver);
			mh.flash(TwoDView_Tab, driver);
		
			
			TwoDView_Tab.click();    
		}
		
		public void twod_view_window_gets_open() throws Throwable {			
			TwoDView_window.isDisplayed();	
			System.out.println("2D Window gets open");
		}
		
		@SuppressWarnings("static-access")
		public void user_verify_the_TwoD_view_tools() throws Throwable {
			mh.verifyElementPresent(TD_MeasureTool);
			mh.verifyElementPresent(TD_DocBrowser);
			mh.verifyElementPresent(TD_Settings);
			mh.verifyElementPresent(TD_Properties);
			mh.verifyElementPresent(TD_Properties);
		}
		
		public void user_click_on_Document_Browser_Files_icon() throws Throwable {
			TD_DocBrowser.click();		    
		}
		
		@SuppressWarnings("static-access")
		public void user_able_to_select_any_TwoD_view_files() throws Throwable {
			mh.verifyElementPresent(SelectTD_file);
			mh.flash(SelectTD_file, driver);
			mh.drawBorder(SelectTD_file, driver);
			SelectTD_file.click();
			Thread.sleep(2000);
		    
		}
		
	
}

