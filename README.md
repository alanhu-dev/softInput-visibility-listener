# softInput-visibility-listener
This library allows you to easily register a listener for the visibility of the SoftInput (keyboard) in activity, fragment.

## Installation
### Requirements
The lib works on Android 5.0+ (API level 21+).

#### Groovy
```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.alanhu-dev:softInput-visibility-listener:1.0'
}
```

#### Kotlin DSL
```
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.alanhu-dev:softInput-visibility-listener:1.0")
}
```

## Usage
You can simply use the following extension function in an Activity or Fragment to listen for SoftInput visibility. The listener will automatically unregister during onDestroy:
```
registerSoftInputVisibilityListener { isVisible -> 
}
```

If you need to unregister earlier: 
```
val unregistrar = registerSoftInputVisibilityListener { ... }
...
unregistrar.unregister()
```

You can also directly check whether the keyboard is visible:
```
Log.d("SoftInput", "isSoftInputVisible: $isSoftInputVisible")
```