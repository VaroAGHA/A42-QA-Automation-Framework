import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.LoginPage;
import pages.PlaylistsPage;
import pages.SongsPage;

import java.util.List;

public class ActionsTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    PlaylistsPage playlistsPage = new PlaylistsPage();

    SongsPage songsPage = new SongsPage();


    @Test
    public void playSongTest() {
        PageFactory.initElements(getDriver(), loginPage);
        // hover over in clickPlayBtn
        loginPage.login("varjan80@mail.ru", "te$t$tudent");
        playlistsPage.clickPlayBtn();
        Assert.assertTrue(playlistsPage.pauseBtnExists());

        // Comparing numbers of elements example
        List<WebElement> songs = songsPage.getSongs();

        int songsNumberBefore = songs.size();
        System.out.println(songsNumberBefore);
        // Just an example: here we would add or delete an element but we didn't
        int songsNumberAfter = songs.size();
        System.out.println(songsNumberAfter);

        // Soft assert example
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(basePage.getDriver().getCurrentUrl(),
                "https://qa.koel.app/#!/queue");
        softAssert.assertTrue(songsNumberBefore == songsNumberAfter,
                "=== Songs number before should be equal songs number after ===");
        softAssert.assertAll();
    }

    @Test
    public void renamePlaylist() throws InterruptedException {
        PageFactory.initElements(getDriver(), loginPage);
        // double click
        String playlistName = "Summer songs";

        loginPage.login("varjan80@mail.ru", "te$t$tudent");
        String oldName = playlistsPage.getPlaylistName();
        if (oldName.equals(playlistName)) {
            playlistName = "Winter songs";
        }
        System.out.println(playlistName);
        playlistsPage.doubleClickChoosePlaylist();
        playlistsPage.enterPlaylistName(playlistName);
        Thread.sleep(1000);
        String newName = playlistsPage.getPlaylistName();
        System.out.println(newName);
        Assert.assertEquals(playlistName, newName);
    }

    @Test
    public void playSongFromListTest() throws InterruptedException {
        PageFactory.initElements(getDriver(), loginPage);
        loginPage.login("varjan80@mail.ru", "te$t$tudent");
        playlistsPage.goToAllSongs();
        songsPage.clickFirstSong();
        songsPage.clickPlayBtn();
        Thread.sleep(4000);
        Assert.assertTrue(songsPage.getSoundBar().isDisplayed());
    }
}



//    @Test
//    public void countSongsInPlaylist() {
//        PageFactory.initElements(getDriver(), loginPage);
//        loginPage.login("demo@class.com", "te$t$tudent");
//        WebElement playlist = basePage.waitUntilVisible(By.cssSelector(".playlist:nth-child(4)"));
//        playlist.click();
//        List<WebElement> songs = basePage.getDriver().findElements(By.cssSelector("#playlistWrapper .song-item"));
//        int number = songs.size();
//        //  Assert.assertEquals(number, 4); // can fail, depends on current number. This is just an example
//    }


