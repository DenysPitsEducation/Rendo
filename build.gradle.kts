plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library) apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}
