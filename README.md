# viewbinding-delegate
 viewbinding-delegate is Library to simplify ViewBinding's delegation class and function using Kotlin Delegate feature

## Installation

### Step 1. Add the JitPack repository to your root build.gradle
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### or Add the JitPack repository to your setting.gradle
```gradle
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency to module build.gradle

```gradle
dependencies {
    ...
    implementation 'com.github.pengdst:viewbinding-delegate:v2.0'
}
```
## Usage/Examples

```kotlin
...
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by viewBindings()
    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ...
    }
}
```

