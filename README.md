<h1 align="center">FilterRecycle</h1></br>

<p align="center">
:balloon: A horizontal list of filters, customizable on Android.
</p>
</br>

<p align="center">
<img src="https://github.com/koohyar7342/FilterRecycle/blob/9be9ab7d05bded613324ea887ec3a4b59de14f13/filterrecycle_gif.gif" width="250"/>
</p>

## Including in your project
[![JitPack](https://img.shields.io/jitpack/version/com.github.koohyar7342/FilterRecycle)](https://jitpack.io/#koohyar7342/FilterRecycle/Tag)

### Gradle
Step 1. Add the **JitPack** repository to your build file 
```kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```
Step 2.Add the dependency below to your **module**'s `build.gradle` file:

```kotlin
dependencies {
  implementation 'com.github.koohyar7342:FilterRecycle:1.0.2'
}
```
## How to Use
creating FilterRecycle in **xml**
```xml
    <com.koohyar.filterRecycle.FilterRecycle
        android:id="@+id/testing_recycle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:fr_card_content_padding="@dimen/default_content_padding"
        app:fr_selected_background_color="@android:color/holo_orange_light"
        app:fr_selected_text_color="@android:color/holo_red_light"
        app:fr_unselected_Background_color="@android:color/white"
        app:fr_unselected_text_color="@android:color/black"
        />
```
create a list of filters
```kotlin
    private var filtersList = listOf<FilterModel>(
        FilterModel(0, "فیلتر 0", "فیلتر 0", false),
        FilterModel(1, "فیلتر 1", "فیلتر 1", false),
        FilterModel(2, "فیلتر 2", "فیلتر 2", false),
        FilterModel(3, "فیلتر 3", "فیلتر 3", false),
        FilterModel(4, "فیلتر 4", "فیلتر 4", false),
        FilterModel(5, "فیلتر 5", "فیلتر 5", false),
        FilterModel(6, "فیلتر 6", "فیلتر 6", false),
        FilterModel(7, "فیلتر 7", "فیلتر 7", false),
        FilterModel(8, "فیلتر 8", "فیلتر 8", false),
        FilterModel(9, "فیلتر 9", "فیلتر 9", false),
    )
```
initilize the recyclerview with  created filtersList and lifecycleScope
```kotlin
  binding.testingRecycle.initializeRecycleView(filtersList, lifecycleScope)
```
set clickListener on filters
```kotlin
  binding.testingRecycle.setClickListener(object : FilterClickListener {
    override fun onAddFilter(position: Int, id: Int): Boolean {
      // Apply adding filter
      return true // or return false if you want cancel adding
    }

     override fun onRemoveFilter(position: Int, id: Int): Boolean {
       // Apply removing filter
       return true //or return false if you want cancel removing
    }
})
```
selecting filter with it's id manually
```kotlin
  binding.testingRecycle.setSelectedFilter(1) // just set filter with id=1 as selected
```
removing filter with it's id manually
```kotlin
  binding.testingRecycle.removeSelectedFilter(1) // just set filter with id=1 as Unselected
```
## Find this library useful? :heart:
Support me here __[https://koohyar7342.github.io](https://koohyar7342.github.io)__ <br>
Also, __[follow me](https://github.com/koohyar7342)__ on GitHub for my next creations!

