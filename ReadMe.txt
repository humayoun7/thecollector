This project is using repository pattern for the most part.
https://developer.android.com/jetpack/guide

Since there were few required screens and none of them required any kind of live updates, so I didn't use livedata and ViewModel that seem like an overkill at this point in the app. But if the app scales livedata and viewModels can be easily incorporated.

Repository pattern is used, but at this stage I just created the sharedReposity as the app scales there can be multiple repositories corrospoding to different entities. For now repositories are getting data directly from Room DB but they can also get data from remote source if needed.

Navigation: Navigation component from jetpack is used and all the navigation is handled for the app in the nav_graph

Persistence: Room DB is used with data access objects (Dao), I added some delete and update methods as well in Daos as I was initially planning to do that bonus part.

Unit tests: Just to fullfill the requirement I added couple of unit tests in sharedUnitTest file, Mockito can be used to mock the stuff as well in order to get better coverage and add more unit tests and dependency injectection can be used inorder to make things more testable.

PLEASE NOTE: My foucus was not on aesthetics and UI for the purpose of this assignment. I could have spend more time making it delightful but I assumed that was not the purpose of the assignment.

