package twitter4s.api

import twitter4j.Paging
import twitter4j.ResponseList
import twitter4j.Status

trait FavoriteMethods {
	def getFavorites(id: Option[String] = None, page: Option[Int] = None, paging: Option[Paging] = None): ResponseList[Status]
	
	def createFavorite(id: Long): Status
	
	def destroyFavorite(id: Long): Status
}