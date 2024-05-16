# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/ic_plus_puple_24dp.zhuchinskiy/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-repackageclasses
-allowaccessmodification
-flattenpackagehierarchy

-keepclassmembers class * implements android.os.Parcelable {
 public <fields>;
}
-keep class * implements android.os.Parcelable
-keep enum *

-keepattributes Exceptions

-ignorewarnings
-keepattributes InnerClasses
-keepattributes SourceFile,LineNumberTable

# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}

# Keep `serializer()` on companion objects (both default and named) of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# @Serializable and @Polymorphic are used at runtime for polymorphic serialization.
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

# Rule to save runtime annotations on serializable class.
# If the R8 full mode is used, annotations are removed from classes-files.
#
# For the annotation serializer, it is necessary to read the `Serializable` annotation inside the serializer<T>() function - if it is present,
# then `SealedClassSerializer` is used, if absent, then `PolymorphicSerializer'.
#
# When using R8 full mode, all interfaces will be serialized using `PolymorphicSerializer`.
#
# see https://github.com/Kotlin/kotlinx.serialization/issues/2050

-if @kotlinx.serialization.Serializable class **
-keep, allowshrinking, allowoptimization, allowobfuscation, allowaccessmodification class <1>

-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile

-keepnames class kotlinx.coroutines.JobCancellationException {}
-keepnames class ua.prom.b2c.core.exception.** { *; }
