package twitter4s

import conf.PropertyConfiguration
import conf.ConfigurationBuilder
import java.util.Properties
import twitter4j.json.DataObjectFactory

object Twitter4sTestHelper {
  val prop = new Properties()
  val is = Twitter4sTestHelper.getClass().getResourceAsStream("test.properties")
  
  prop.load(is)
  is.close()
  
  class TestUserInfo(propName: String) {
    val screenName = prop.getProperty(propName + ".user")
    val password = prop.getProperty(propName + ".password")
    val id = prop.getProperty(propName + ".id").toLong
    val accessToken = prop.getProperty(propName + ".oauth.accessToken")
    val accessTokenSecret = prop.getProperty(propName + ".oauth.accessTokenSecret")
  }
  
  val id1 = new TestUserInfo("id1")
  val id2 = new TestUserInfo("id2")
  val id3 = new TestUserInfo("id3")
  val bestFriend1 = new TestUserInfo("bestFriend1")
  val bestFriend2 = new TestUserInfo("bestFriend2")
  
  /**
   * Get Twitter4S object from test.propreties with prefix id1
   */
  def twitter1 = {
    Twitter(
        Configuration.isSpecifiedBy(PropertyConfiguration(prop, "/id1")))
  }
  
  /**
   * Get Twitter4S object from test.properties with prefix id2
   */
  def twitter2 = {
    Twitter(
        Configuration.isSpecifiedBy(PropertyConfiguration(prop, "/id2")))
  }
  
  /**
   * Get Twitter4S object from test.propreties with prefix id3
   */
  def twitter3 = {
    Twitter(
        Configuration.isSpecifiedBy(PropertyConfiguration(prop, "/id3")))
  }
  
  /**
   * Get Twitter4s object from test.properties with perfix r-w-private
   * with access level Read, write, and direct messages
   */
  def rwPrivateMessage = {
    Twitter(
        Configuration.isSpecifiedBy(PropertyConfiguration(prop, "/r-w-private")))
  }
  
  /**
   * Get Twitter4S object not authorized
   */
  def unauthenticated = {
    Twitter(Configuration.isSpecifiedBy(ConfigurationBuilder().setJSONStoreEnabled(true).build()))
  }
  
  /**
   * Get raw JSON string from Twitter4J object
   */
  def rawJSON(target: Any) = DataObjectFactory.getRawJSON(target)
  
  /**
   * Get id1 blocking user screen name
   */
  def blockingScreenName = prop.getProperty("id1.block.user")
  
  /**
   * Get id1 blocking user id
   */
  def blockingUserId = prop.getProperty("id1.block.id").toLong
  
  /**
   * Get id1 blocking users list size
   */
  def blockingUsersSize = prop.getProperty("id1.block.length").toInt
  
  /**
   * Get numberId screenName
   */
  def numberId = prop.getProperty("numberid.user")

  /**
   * Get numberId id
   */
  def numberIdId = prop.getProperty("numberid.id").toLong
  
  def followsOneWay = prop.getProperty("followsOneWay")
  
}