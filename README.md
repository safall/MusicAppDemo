# Music App Demo


## Followings components from Android Jetpack are implemented into this repository
   - [AndroidX][androidX] 
   - [ViewModel][viewmodel] 
   - [LiveData][livedata]
   - [Navigation][navigation]
  
   [androidX]: <https://developer.android.com/jetpack/androidx>
   [viewmodel]: <https://developer.android.com/topic/libraries/architecture/viewmodel>
   [livedata]: <https://developer.android.com/topic/libraries/architecture/livedata>
   [navigation]: <https://developer.android.com/guide/navigation>
  
## Other Libraries used
   - [Dagger2][dagger]
   - [Retrofit][retrofit] 
   - [RxJava][rxjava]

   [dagger]: <https://github.com/google/dagger>
   [retrofit]: <https://square.github.io/retrofit/>
   [rxjava]: <https://github.com/ReactiveX/RxJava> 

## App content details
App contains the initial screen with search bar, user can search the artist using a query, search is reactive, in other
words results will be shown as search on type basis. 

When the user clicks any one of the artist from search result list, screen containing the list of albums of the artist 
is displayed. 

When the user clicks the album from the list, the album detail is shown which contains the track list in the album. 

## Implementation

### Project architecture: 
MVVM architecture is followed in this project with LiveData and RxJava. 

