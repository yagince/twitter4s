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
import twitter4j.PagableResponseList
import twitter4s.User
import twitter4s.UserList

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait ListSubscribersMethods {
  /**
   * Returns the subscribers of the specified list.
   * <br />This method calls twitter4j.Twitter.getUserListSubscribers.
   * <br />getUserListSubscribers calls http://api.twitter.com/1/lists/subscribers.json
   *
   * @param listId The id of the list
   * @param cursor Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response    body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the members of the specified list.
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/subscribers">GET lists/subscribers | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListSubscribers(listId: Int, cursor: Long): PagableResponseList[twitter4j.User]

  /**
   * Make the authenticated user follow the specified list.
   * <br />This method calls twitter4j.Twitter.createUserListSubscription.
   * <br />createUserListSubscribers calls http://api.twitter.com/1/list/subscribers/create.json
   *
   * @param listId The id of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/subscribers/create">POST lists/subscribers/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createUserListSubscription(listId: Int): UserList

  /**
   * Unsubscribes the authenticated user form the specified list.
   * <br />This method calls twitter4j.Twitter.destroyUserListSubscription.
   * <br />destroyUserListSubscription calls http://api.twitter.com/1/subscribers/destroy.json
   *
   * @param listId The id of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/subscribers/destroy">POST lists/subscribers/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyUserListSubscription(listId: Int): UserList

  /**
   * Check if the specified user is a subscriber of the specified list.
   * <br />This method calls twitter4j.Twitter.showUserListSubscriptions.
   * <br />showUserListSubscriptions calls http://api.twitter.com/1/lists/subscribers/show.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user who you want to know is a member or not of the specified list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * , or the user is not a member of the specified list(TwitterException.getStatusCode() returns 404 in that case.)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/subscribers/show">GET lists/subscribers/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUserListSubscription(listId: Int, userId: Long): User
}