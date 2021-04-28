/**
 * Contains the Scholar class in which I've implemented the TFE assignment
 */
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Contains function which searches for and downloads papers from Google Scholar
 */
public class Scholar {
	/**
	 * Downloads papers from Google Scholar. Opens Google Scholar, searches for a
	 * topic then filters for papers published after 2020. Downloads the first paper
	 * available as a PDF and repeats the same for 4 more topics.
	 * 
	 * @param args Command-line arguments to main function
	 */
	public static void main(String[] args) {
		// Provide path to EdgeDriver executable
		System.setProperty("webdriver.edge.driver", "edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();

		// Change settings to download PDF files instead of opening them in Edge
		driver.get("edge://settings/content/pdfDocuments");
		driver.findElement(By.id("settings-toggle-input-31")).click();

		driver.get("https://scholar.google.com");

		// Search for the topic
		driver.findElement(By.name("q")).sendKeys("cloud security");
		driver.findElement(By.id("gs_hdr_tsb")).click();

		driver.findElement(By.linkText("Since 2020")).click();
		driver.findElement(By.className("gs_or_ggsm")).click();

		// Click the 'Download file' button to download the paper
		Actions acts = new Actions(driver);
		acts.sendKeys(Keys.TAB).build().perform();
		acts.sendKeys(Keys.RETURN).build().perform();

		driver.navigate().back();

		String topics[] = { "neural networks", "cognitive computing", "internet of things", "genetic algorithms" };
		for (String topic : topics) {
			driver.findElement(By.name("q")).clear();
			driver.findElement(By.name("q")).sendKeys(topic);
			driver.findElement(By.id("gs_hdr_tsb")).click();
			driver.findElement(By.className("gs_or_ggsm")).click();
			acts.sendKeys(Keys.TAB).build().perform();
			acts.sendKeys(Keys.RETURN).build().perform();
			driver.navigate().back();
		}
	}
}
