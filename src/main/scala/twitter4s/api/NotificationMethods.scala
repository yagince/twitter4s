package twitter4s.api
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
import twitter4s.User

trait NotificationMethods {
  /**
   * Enables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br />This method calls twitter4j.Twitter.enableNotification.
   * <br />enableNotification calls http://api.twitter.com/1/notifications/follow.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser(requied) data specific user to follow with device updates. This data is screen name or user id.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when user specified data is not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/follow">POST notifications/follow | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def enableNotification(specificUser: User.SpecificInfo): User

  /**
   * Disables notifications for updates from the specified user to the authenticating user. Returns the specified user when successful.
   * <br />This method calls twitter4j.Twitter.disableNotification
   * <br />disableNotification calls http://api.twitter.com/1/notifications/leave.json
   * <br />Note1: You must set screenName or userId at least.
   * <br />Note2: Parameter userId is taken priority over screenName.
   *
   * @param specificUser(requied) data specific user to disable device notifications. This data is screen name or user id.
   * @return User
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when user specified data is not set.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/notifications/leave">POST notifications/leave | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def disableNotification(specificUser: User.SpecificInfo): User
}