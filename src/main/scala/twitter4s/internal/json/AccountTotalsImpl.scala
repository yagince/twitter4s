package twitter4s.internal.json
import twitter4s.AccountTotals

case class AccountTotalsImpl(twt4jAccountTotals: twitter4j.AccountTotals) extends AccountTotals {
  type Tw4jResponse = twitter4j.AccountTotals
  
  def updates = twt4jAccountTotals.getUpdates()
  
  def followers = twt4jAccountTotals.getFollowers()
  
  def favorites = twt4jAccountTotals.getFavorites()
  
  def friends = twt4jAccountTotals.getFriends()
  
  def rateLimitStatus = twt4jAccountTotals.getRateLimitStatus()
  
  def accessLevel = twt4jAccountTotals.getAccessLevel()
  
  def tw4jObj = twt4jAccountTotals
}