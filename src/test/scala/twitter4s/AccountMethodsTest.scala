package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import java.util.Date
import twitter4j.User

@RunWith(classOf[JUnitRunner])
class AccountMethodsTest extends Specification {

  "verifyCredentials" should {
    "get authorized user's credentials information" in {
      val original = twitter1.verifyCredentials
      rawJSON(original) must not equalTo(null)
      original must equalTo(DataObjectFactory.createUser(rawJSON(original)))
    }
  }
  
  "updateProfile" should {
    "update user profile(name, url, location, description)" in {
      // make precondition
      val original = twitter3.updateProfile(
          "twt4s_id3",
          "https://github.com/Shinsuke-Abe/twitter4s",
          ":Location",
          "Hi there, I do test a lot!new")
      
      val newName = original.getName + "new"
      val newURL = original.getURL() + "new"
      val newLocation = new Date().toString()
      val newDescription = original.getDescription() + "new"
      
      // test
      val altered = twitter3.updateProfile(newName, newURL, newLocation, newDescription)
      rawJSON(altered) must not equalTo(null)
      original must equalTo(DataObjectFactory.createUser(rawJSON(original)))
      altered must equalTo(DataObjectFactory.createUser(rawJSON(altered)))
      altered.getName() must equalTo(newName)
      altered.getURL().toString() must equalTo(newURL)
      altered.getLocation() must equalTo(newLocation)
      altered.getDescription() must equalTo(newDescription)
    }
  }
  
  "updateProfileColors" should {
    def testProfileColors(
        target: User,
        profileBackgroundColor: String,
        profileTextColor: String,
        profileLinkColor: String,
        profileSidebarFillColor: String,
        profileSidebarBorderColor: String) = {
      target.getProfileBackgroundColor() must equalTo(profileBackgroundColor)
      target.getProfileTextColor() must equalTo(profileTextColor)
      target.getProfileLinkColor() must equalTo(profileLinkColor)
      target.getProfileSidebarFillColor() must equalTo(profileSidebarFillColor)
      target.getProfileSidebarBorderColor() must equalTo(profileSidebarBorderColor)
    }
    
    "change colors on user status page with three characters" in {
      val updatedUser = twitter3.updateProfileColors("f00", "f0f", "0ff", "0f0", "f0f")
      rawJSON(updatedUser) must not equalTo(null)
      updatedUser must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser)))
      testProfileColors(updatedUser, "f00", "f0f", "0ff", "0f0", "f0f")
      updatedUser.isProfileUseBackgroundImage() must beTrue
      updatedUser.isShowAllInlineMedia() must beFalse
      updatedUser.getListedCount() must be_<=(0)
    }
    
    "change colors on user status page with six characters" in {
      val updatedUser = twitter3.updateProfileColors("87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
      rawJSON(updatedUser) must not equalTo(null)
      updatedUser must equalTo(DataObjectFactory.createUser(rawJSON(updatedUser)))
      testProfileColors(updatedUser, "87bc44", "9ae4e8", "000000", "0000ff", "e0ff92")
    }
  }
  
  "getAccountTotals" should {
    "get user's (favorite, followers, friends, udpates) total number" in {
      val totals = twitter1.getAccountTotals
      totals.getFavorites must be_>(0)
      totals.getFollowers() must be_>(0)
      totals.getFriends() must be_>(0)
      totals.getUpdates() must be_>(0)
      totals must equalTo(DataObjectFactory.createAccountTotals(rawJSON(totals)))
    }
  }
  
//  "getAccountSettings" should {
//    "get authorized user's account settings" in {
//      val settings = twitter1.getAccountSettings
//      settings.isSleepTimeEnabled must beTrue // is default false?
//    }
//  }
}