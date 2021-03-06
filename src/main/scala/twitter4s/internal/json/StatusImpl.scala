package twitter4s.internal.json
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import twitter4s._
import java.util.Date

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
case class StatusImpl(twt4jStatus: twitter4j.Status) extends Status {
  
  type Tw4jResponse = twitter4j.Status

  def isTruncated = twt4jStatus.isTruncated()

  def inReplyToUserId = twt4jStatus.getInReplyToUserId()

  def inReplyToScreenName = twt4jStatus.getInReplyToScreenName()

  def isFavorited = twt4jStatus.isFavorited()

  def user = UserImpl(twt4jStatus.getUser())

  def isRetweet = twt4jStatus.isRetweet()

  def retweetedStatus = StatusImpl(twt4jStatus.getRetweetedStatus())

  def contributors = twt4jStatus.getContributors()

  def retweetCount = twt4jStatus.getRetweetCount()

  def isRetweetedByMe = twt4jStatus.isRetweetedByMe()

  def annotations = twt4jStatus.getAnnotations()

  def urlEntities = twt4jStatus.getURLEntities()

  def hashtagEntities = twt4jStatus.getHashtagEntities()

  def mediaEntities = twt4jStatus.getMediaEntities()

  def createdAt = twt4jStatus.getCreatedAt()

  def id = twt4jStatus.getId()

  def text = twt4jStatus.getText()

  def source = twt4jStatus.getSource()

  def inReplyToStatusId = twt4jStatus.getInReplyToStatusId()

  def place = PlaceImpl(twt4jStatus.getPlace())

  def geoLocation = twt4jStatus.getGeoLocation()

  def rateLimitStatus = RateLimitStatusImpl(twt4jStatus.getRateLimitStatus())

  def accessLevel = AccessLevel(twt4jStatus.getAccessLevel())

  def tw4jObj = twt4jStatus

}