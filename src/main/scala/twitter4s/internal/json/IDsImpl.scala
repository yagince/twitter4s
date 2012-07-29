package twitter4s.internal.json

import twitter4s.IDs
import twitter4j.RateLimitStatus

case class IDsImpl(twt4jIDs: twitter4j.IDs) extends IDs {
  type Tw4jResponse = twitter4j.IDs

  def ids = twt4jIDs.getIDs()

  def hasPrevious = twt4jIDs.hasPrevious()

  def previousCursor = twt4jIDs.getPreviousCursor()

  def hasNext = twt4jIDs.hasNext()

  def nextCursor = twt4jIDs.getNextCursor()
  
  def rateLimitStatus = twt4jIDs.getRateLimitStatus()
  
  def accessLevel = twt4jIDs.getAccessLevel()
  
  def tw4jObj = twt4jIDs

  def apply(idx: Int) = ids(idx)
}