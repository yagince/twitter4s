package twitter4s

import twitter4s.api._

trait TwitterAPIs extends AnyRef
with AccountMethods
with LocalTrendsMethods
with SearchMethods
with StatusMethods
with TrendsMethods