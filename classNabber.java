import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;

public class classNabber
{
    private static WebDriver page;
    private static GUI frame;
    private static int classesInCart;

    public static void main(String[] args)
    {
    	//user controlled variables
    	int term = 1;
    	
    	
    	//site variables
    	classesInCart = 20;			//set to a large number, but in checkAvailability function
    	
    	
    	//set up GUI frame
        frame = new GUI();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 300, 300 );
        frame.setVisible( true );
        frame.setResizable( false );
        
        //attempt to open save file, otherwise create one
        try {
            File save = new File("save.txt");

            if(save.createNewFile()) {
                frame.setStatus("Save file created in current directory");
            }
            else {
                frame.setStatus("Loading save file");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //opens up the driver and goes to home page
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\lizja\\Desktop\\geckodriver.exe");
        page = new FirefoxDriver();
        page.get("https://my.fsu.edu");
        
        navigateToCart(term);
        
        if(checkAvailability()) {   
        	
        	frame.setStatus("Class Available!");
        	
        	if(enrollInClass()) {
        		frame.setStatus("Successfully enrolled!");
        	}
        	else {
        		frame.setStatus("Enrollment unsuccessful");
        	}
        	
        	//clicks "Add Another Class"
        	waitAndClick("DERIVED_REGFRM1_SSR_LINK_STARTOVER");
        }
        else {
        	frame.setStatus("All classes are full... retrying in 5 minutes");
        }

        return;
    }
    
    private static void navigateToCart(int term) {
    	frame.setStatus("Navigating to your cart");
    	
    	//inputs username and password
        page.findElement(By.id("username")).sendKeys("");
        page.findElement(By.id("password")).sendKeys("");
        
        //clicks through to term selection
        page.findElement(By.id("fsu-login-button")).click();
        page.get("https://cas.fsu.edu/cas/login?service=https%3A%2F%2Fcampus.omni.fsu.edu%2Fpsc%2Fsprdcs%2FEMPLOYEE%2FSA%2Fc%2FNUI_FRAMEWORK.PT_AGSTARTPAGE_NUI.GBL%3FCONTEXTIDPARAMS%3DTEMPLATE_ID%3APTPPNAVCOL%26scname%3DADMN_FSU_SR_ST_MY_CLASSES_CL_N%26PanelCollapsible%3DY%26PTPPB_GROUPLET_ID%3DFSU_SR_ST_MY_CLASSES_CL_TL%26CRefName%3DADMN_NAVCOLL_25%26ICAJAXTrf%3Dtrue%26ptgpid%3DADMN_S201807141525557333111812");
        page.get("https://campus.omni.fsu.edu/psc/sprdcs/EMPLOYEE/SA/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL?Page=SSR_SSENRL_CART&Action=A&ACAD_CAREER=UGRD&EMPLID=200532646&INSTITUTION=FSU01&STRM=2191");
        
        //term selection
        if(term == 1) {
        	page.findElement(By.xpath("/html[1]/body[1]/form[1]/div[5]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/input[1]")).click();
        }
        else if(term == 2) {
        	page.findElement(By.xpath("/html[1]/body[1]/form[1]/div[5]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[3]/td[1]/div[1]/input[1]")).click();
        }
        else {
        	page.findElement(By.xpath("/html[1]/body[1]/form[1]/div[5]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[4]/td[1]/div[1]/input[1]")).click();
        }
        
        page.findElement(By.id("DERIVED_SSS_SCT_SSR_PB_GO")).click();
        
        return;
    }
    
    private static Boolean checkAvailability() {
    	//searches for the green status image in the page
		if(page.findElements(By.xpath("//img[contains(@src,'PS_CS_STATUS_OPEN_ICN_1.gif')]")).size() > 1) {
			return true;
		}
		else
			return false;
    }
    
    
    private static Boolean enrollInClass() {    	
    	//click proceed
    	waitAndClick("DERIVED_REGFRM1_LINK_ADD_ENRL$82$");
    	
    	//click finish enrolling
    	waitAndClick("DERIVED_REGFRM1_SSR_PB_SUBMIT");
    	
    	
    	if(page.findElements(By.xpath("//img[contains(@src,'PS_CS_STATUS_ERROR_ICN_1.gif')]")).size() > 1) {
    		return true;
    	}
    	
    	return false;
    }
    
    private static void waitAndClick(String elementName) {
    	WebDriverWait wait = new WebDriverWait(page, 5);
    	wait.until(ExpectedConditions.elementToBeClickable(By.name(elementName)));
    
    	page.findElement(By.name(elementName)).click();
    }
}