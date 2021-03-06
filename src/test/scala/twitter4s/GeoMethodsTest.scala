package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._
import twitter4j.json.DataObjectFactory
import twitter4j.TwitterException
import java.util.Date

@RunWith(classOf[JUnitRunner])
class GeoMethodsTest extends Specification {
  
  private def testPlaces(target: ResponseList[twitter4j.Place]) = {
    rawJSON(target.tw4jObj) must not equalTo(null)
    target(0) must equalTo(DataObjectFactory.createPlace(rawJSON(target(0))))
    target.size must be_>(0)
  }
  
  "reverseGeoCode" should {
    "get no places if location is (0,0)" in {
      val query = GeoQuery(GeoLocation(0, 0))
      val places = twitter1.reverseGeoCode(query)
      places.size must equalTo(0)
    }
    
    "get place list if location is exists" in {
      val query = GeoQuery(GeoLocation(37.78215, -122.40060))
      testPlaces(twitter1.reverseGeoCode(query))
    }
    
    "get place list if location is exists on latitude and longitude" in {
      val query = GeoQuery(37.78215, -122.40060)
      testPlaces(twitter1.reverseGeoCode(query))
    }
  }
  
  "searchPlaces" should {
    "get place list with location" in {
      val query = GeoQuery(GeoLocation(37.78215, -122.40060))
      testPlaces(twitter1.searchPlaces(query))
    }
  }
  
  "getSimilarPlaces" should {
    "get similar place list with parameter location" in {
      val places = twitter1.getSimilarPlaces(GeoLocation(37.78215, -122.40060), "SoMa", null, null)
      rawJSON(places.tw4jObj) must not equalTo(null)
      places(0) must equalTo(DataObjectFactory.createPlace(rawJSON(places(0))))
      places.size must be_>(0)
    }
  }
  
  "getGeoDetails" should {
    "get detail location information" in {
      try {
        val place = unauthenticated.getGeoDetails("5a110d312052166f")
        rawJSON(place.tw4jObj) must not equalTo(null)
        place.tw4jObj must equalTo(DataObjectFactory.createPlace(rawJSON(place.tw4jObj)))
        place.fullName must equalTo("San Francisco, CA")
        place.containedWithIn(0).fullName must equalTo("California, US")
      } catch {
        // is being rate limited
        case te: TwitterException => te.getStatusCode must equalTo(404)
      }
    }
  }
  
  "updateStatus(StatusMethods)" should {
    "update user status with place information" in {
      val sanFrancisco = "5a110d312052166f"
      val latestStatus = StatusUpdate(new Date() + " status with place")
      val status = twitter3.updateStatus(Status.isSetBy(latestStatus.placeId(sanFrancisco)))
      rawJSON(status.tw4jObj) must not equalTo(null)
      status.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(status.tw4jObj)))
      status.place.id must equalTo(sanFrancisco)
      status.contributors must equalTo(null)
    }
    
    "update user status with geo information" in {
      val latitude = 12.3456d
      val longitude = -34.5678d
      val latestStatus = StatusUpdate(new Date() + " status with geo")
      
      val statusWithGeo = twitter3.updateStatus(Status.isSetBy(latestStatus.location(GeoLocation(latitude, longitude))))
      rawJSON(statusWithGeo.tw4jObj) must not equalTo(null)
      statusWithGeo.tw4jObj must equalTo(DataObjectFactory.createStatus(rawJSON(statusWithGeo.tw4jObj)))
      statusWithGeo.user.isGeoEnabled must beTrue
      statusWithGeo.geoLocation.getLatitude() must equalTo(latitude)
      statusWithGeo.geoLocation.getLongitude() must equalTo(longitude)
      twitter1.verifyCredentials.isGeoEnabled must beFalse
    }
  }
}