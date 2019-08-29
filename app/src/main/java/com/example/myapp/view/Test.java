package com.example.myapp.view;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Test {

    /*
    buildscript {
    repositories {
        maven { url 'https://maven.fabric.io/public' }
    }

    dependencies {
        classpath 'io.fabric.tools:gradle:1.25.4'
    }
}
apply plugin: 'com.android.application'
apply plugin: 'io.fabric'
apply plugin: 'realm-android'

repositories {
    maven { url 'https://maven.fabric.io/public' }
}

android {
    compileSdkVersion 28
    buildToolsVersion '28.0.3'

    defaultConfig {
        applicationId "com.supremegolf.app"
        minSdkVersion 16
        targetSdkVersion 28

        versionCode 174
        versionName "4.0.1"

        multiDexEnabled true
        vectorDrawables.useSupportLibrary = true
    }
    aaptOptions {
        cruncherEnabled = false
    }

    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.2'
    }

    lintOptions {
        disable 'MissingTranslation'
        checkReleaseBuilds false
    }
    signingConfigs {
        debug {
            storeFile file('../keystore.jks')
            storePassword ';w<taUF+3fL;nuW$r&;JgyDw'
            keyAlias 'supremedebug'
            keyPassword '2Qn_mqZ?%wFA`PQGBzRZr_dx'
        }
        release {
            storeFile file('../keystore.jks')
            storePassword ';w<taUF+3fL;nuW$r&;JgyDw'
            keyAlias 'supremerelease'
            keyPassword '2>~/&74FdF`aTSu<ycMf4NA='
        }
    }
    buildTypes {
        stage {
            debuggable true
            signingConfig signingConfigs.debug
            ext.enableCrashlytics = true
            buildConfigField 'String', 'ENVIRONMENT', '"STAGE"'
            buildConfigField 'String', 'API_URL', '"https://staging.supremegolf.com:443/api/v4/"'
            buildConfigField "String", "SPREEDLY_KEY", "\"XRKxLW22ggvKprrf16FdntrvtHv\""
            buildConfigField "String", "ENCRYPTION_KEY", '"JFV8PlIzVzNHMHxfZl9BfFx8RFIwIUQ="'
            buildConfigField "boolean", "GOLF_PRO_ENABLE", "false"
        }
        preprod {
            debuggable true
            minifyEnabled false
            shrinkResources false
            ext.enableCrashlytics = true
            signingConfig signingConfigs.debug
            buildConfigField 'String', 'ENVIRONMENT', '"PREPROD"'
            buildConfigField 'String', 'API_URL', '"https://preprod.supremegolf.com:443/api/v4/"'
            buildConfigField "String", "SPREEDLY_KEY", "\"Vf76lUtJvgr4UM1fu2JgLKGxsJP\""
            buildConfigField "String", "ENCRYPTION_KEY", '"JFV8PlIzVzNHMHxfZl9BfFx8RFIwIUQ="'
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "boolean", "GOLF_PRO_ENABLE", "false"
        }
        prod {
            debuggable true
            minifyEnabled false
            shrinkResources false
            ext.enableCrashlytics = true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.debug
            buildConfigField 'String', 'ENVIRONMENT', '"PROD"'
            buildConfigField 'String', 'API_URL', '"https://supremegolf.com:443/api/v4/"'
            buildConfigField "String", "SPREEDLY_KEY", "\"Vf76lUtJvgr4UM1fu2JgLKGxsJP\""
            buildConfigField "String", "ENCRYPTION_KEY", '"JFV8PlIzVzNHMHxfZl9BfFx8RFIwIUQ="'
            buildConfigField "boolean", "GOLF_PRO_ENABLE", "false"

        }
        release {
            debuggable false
            minifyEnabled false
            shrinkResources false
            ext.enableCrashlytics = true
            signingConfig signingConfigs.release
            buildConfigField 'String', 'ENVIRONMENT', '"RELEASE"'
            buildConfigField 'String', 'API_URL', '"https://supremegolf.com:443/api/v4/"'
            buildConfigField "String", "SPREEDLY_KEY", "\"Vf76lUtJvgr4UM1fu2JgLKGxsJP\""
            buildConfigField "String", "ENCRYPTION_KEY", '"JFV8PlIzVzNHMHxfZl9BfFx8RFIwIUQ="'
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            buildConfigField "boolean", "GOLF_PRO_ENABLE", "false"

        }
    }
}

ext {
    supportLibVersion = '28.1.0'
    playServiceVersion = '11.6.0'
    supportLibVersion = '28.0.0'
    playServiceVersion = '11.4.2'
    junitLibVersion = '4.12'
    espressoLibVersion = '2.2.2'
    retrofitLibVersion = '2.1.0'
    butterknifeLibVersion = '8.5.1'
    leakCanaryLibVersion = '1.5'
    rxJavaVersion = '2.0.1'
    rxBindingVersion = '2.0.0'
    rxRetrofitAdapter = '1.0.0'
    daggerVersion = '2.5'
}

dependencies {
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    compile fileTree(dir: 'libs', include: ['*.jar'])
//    compile project(path: ':rangeseekbar', configuration: 'default')
//    compile project(path: ':calendarLibrary', configuration: 'default')
    implementation project(path: ':rangeseekbar')
    implementation project(path: ':calendarLibrary')

    androidTestCompile("com.android.support.test.espresso:espresso-core:${espressoLibVersion}", {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile "com.android.support:appcompat-v7:${supportLibVersion}"
    compile "com.android.support:support-v4:${supportLibVersion}"
    compile "com.android.support:cardview-v7:${supportLibVersion}"
    compile "com.android.support:design:${supportLibVersion}"
    compile "com.android.support:support-annotations:${supportLibVersion}"
    compile "com.jakewharton:butterknife:${butterknifeLibVersion}"
    annotationProcessor "com.jakewharton:butterknife-compiler:${butterknifeLibVersion}"
    implementation "android.arch.lifecycle:extensions:1.1.1"
    annotationProcessor "android.arch.lifecycle:compiler:1.1.1"
    compile "com.google.android.gms:play-services-location:${playServiceVersion}"
    compile "com.google.android.gms:play-services-maps:${playServiceVersion}"
    compile "com.google.android.gms:play-services-identity:${playServiceVersion}"
    compile "com.google.firebase:firebase-messaging:${playServiceVersion}"
    compile "com.google.firebase:firebase-core:${playServiceVersion}"
    compile "com.google.android.gms:play-services-analytics:${playServiceVersion}"
    compile "com.squareup.retrofit2:retrofit:${retrofitLibVersion}"
    compile "com.squareup.retrofit2:converter-gson:${retrofitLibVersion}"

    implementation "io.reactivex.rxjava2:rxjava:1.3.8"
    compile "io.reactivex.rxjava2:rxjava:${rxJavaVersion}"
    compile "io.reactivex.rxjava2:rxandroid:${rxJavaVersion}"
    compile "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${rxRetrofitAdapter}"
    compile "com.jakewharton.rxbinding2:rxbinding:${rxBindingVersion}"
    testCompile "junit:junit:${junitLibVersion}"
    compile('com.crashlytics.sdk.android:crashlytics:2.7.1@aar') {
        transitive = true
    }
    implementation 'com.android.support:multidex:1.0.2'
    implementation 'com.jakewharton.rxrelay2:rxrelay:2.0.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.0'
    implementation 'com.mikhaellopez:circularimageview:3.0.2'
    implementation 'com.android.volley:volley:1.1.0'
    implementation 'com.facebook.android:facebook-android-sdk:4.26.0'
    implementation 'com.github.bumptech.glide:glide:3.7.0'
    implementation 'com.google.code.gson:gson:2.8.1'
    implementation 'uk.co.chrisjenx:calligraphy:2.3.0'
    implementation 'com.daimajia.swipelayout:library:1.2.0@aar'
    implementation 'org.jetbrains:annotations-java5:15.0'
    implementation 'at.blogc:expandabletextview:1.0.3'
//    compile 'com.github.jrvansuita:PickImage:2.1.2'
    annotationProcessor 'com.google.auto.value:auto-value:1.2'
    annotationProcessor 'com.ryanharter.auto.value:auto-value-parcel:0.2.0'
    provided 'com.google.auto.value:auto-value:1.2'
    provided 'javax.annotation:jsr250-api:1.0'
    implementation 'com.google.maps.android:android-maps-utils:0.5+'
    implementation 'net.cachapa.expandablelayout:expandablelayout:2.9.2'
    implementation 'org.greenrobot:eventbus:3.1.1'
    implementation 'com.evrencoskun.library:tableview:0.8.5.1'
    implementation 'com.prolificinteractive:material-calendarview:1.4.3'
    implementation 'com.github.badoualy:stepper-indicator:1.0.7'
    implementation 'com.github.mihirsane:CompactCalendarView:ae125cd605'

    implementation 'android.arch.lifecycle:runtime:1.1.0'
    implementation 'android.arch.lifecycle:extensions:1.1.0'
    annotationProcessor "android.arch.lifecycle:compiler:1.1.0"
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0"

    //Image crop
//    compile 'com.isseiaoki:simplecropview:1.1.8'

    api 'com.google.guava:guava:27.0.1-android'
}

apply plugin: 'com.google.gms.google-services'

     */


    //.......................................................................
    //........................................................................

    /*
    package com.supremegolf.app.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.supremegolf.app.BuildConfig;
import com.supremegolf.app.utils.SharedPreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rakesh on 04/05/17.
 */
// api docs for supremegolf...............................
// https://staging.supremegolf.com/api/docs/#!/golfpro-lessons

   /* public class SupremeApiClient {
        private static final String BASE_URL = BuildConfig.API_URL;
        private static final String HOME_URL = "https://quarkbackend.com/";
        public static final int CONNECTION_TIMEOUT = 10;
        public static final int READ_TIMEOUT = 30;
        //TODO: remove this once tee time validation is done properly
        private static Retrofit retrofitClient = null;
        private static final String SPREEDLY_BASE_URL = "https://core.spreedly.com";
        private static final String SPREEDLY_KEY = BuildConfig.SPREEDLY_KEY;

        private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

        private static File cacheDir = null;

        private static OkHttpClient.Builder httpClientBuilder = null;
        private static Retrofit retrofitWithHeader = null;
        private static Retrofit retrofitWithHeader1 = null;
        private static Retrofit retrofitWithRx = null;
        private static  OnConnectionTimeoutListener listener;

        public static OnConnectionTimeoutListener getListener() {
            return listener;
        }

        public static void setListener(OnConnectionTimeoutListener listener) {
            SupremeApiClient.listener = listener;
        }


        private static void disableCertificateForOkHttpConnection(OkHttpClient.Builder httpClientBuilder) {
            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[] {
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                httpClientBuilder.sslSocketFactory(sslSocketFactory);
                httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }catch (Exception e) {
                Log.d("", "disableCertificateForOkHttpConnection", e);
            }
        }

        private static OkHttpClient.Builder getOkHttpClient(Context context) {
            return getOkHttpClient(context, false);
        }

        private static OkHttpClient.Builder getOkHttpClient(final Context context, boolean isToDisableCert) {
        /*if(cacheDir == null){
            cacheDir = context.getCacheDir();
        }*/
       //     if(httpClientBuilder == null) {
        //        httpClientBuilder = new OkHttpClient.Builder();

//                httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
//                httpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
//                if (BuildConfig.DEBUG) {
//                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                    httpClientBuilder.addNetworkInterceptor(interceptor);
//                }
//                // Install an HTTP cache in the application cache directory.
//                httpClientBuilder.retryOnConnectionFailure(true);
        /*File httpCacheDir = new File(cacheDir, "http");
        Cache cache = new Cache(httpCacheDir, DISK_CACHE_SIZE);
        httpClientBuilder.cache(cache);
        httpClientBuilder.networkInterceptors().add(new CacheingControlInterceptor());

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return onTimeOutIntercept(chain);
            }
        });*/
//            if(isToDisableCert) {
//                disableCertificateForOkHttpConnection(httpClientBuilder);
//            }
                //           disableCertificateForOkHttpConnection(httpClientBuilder);

//                httpClientBuilder.addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Interceptor.Chain chain) throws IOException {
//                        Request original = chain.request();
//
//                        Request request = original.newBuilder()
//                                //.addHeader("Connection","close")
//                                .header("X-Api-Key", "d30ca395-5653-4221-9522-7d4031d83b89")
//                                .header("X-Api-User-Token",
//                                        (SharedPreferenceHelper
//                                                .getInstance(context).getToken() != null ?
//                                                SharedPreferenceHelper.getInstance(context).getToken() : ""))
//                                .method(original.method(), original.body())
//                                .build();
//
//                        return chain.proceed(request);
//                    }
//                });

            /*httpClientBuilder.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    // try the request
                    Response response = chain.proceed(request);

                    int tryCount = 0;
                    int maxLimit = 1; //Set your max limit here

                    while (!response.isSuccessful() && tryCount < maxLimit) {

                        Log.d("intercept", "Request failed - " + tryCount);

                        tryCount++;

                        // retry the request
                        //response = chain.proceed(request);
                    }

                    // otherwise just pass the original response on
                    return response;
                }
            });*/
//            }
//            return httpClientBuilder;
//        }
//
//
//        public static Retrofit getRetrofitClientWithHeader(final Context mContext){
//
//            return getRetrofitBuild(mContext);
//        }
//
//        public static Retrofit getGolfProRetrofitClientWithHeader(final Context mContext){
//
//            return getGolfProRetrofitBuild(mContext);
//        }
//
//        @NonNull
//        private static Retrofit getRetrofitBuild(Context mContext) {
//
//            if(retrofitWithHeader == null){
//                retrofitWithHeader =  new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(getOkHttpClient(mContext).build())
//                        .build();
//            }
//
//            return retrofitWithHeader;
//        }
//
//        @NonNull
//        private static Retrofit getGolfProRetrofitBuild(Context mContext) {
//
//            if(retrofitWithHeader1 == null){
//                retrofitWithHeader1 =  new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(getOkHttpClient(mContext).build())
//                        .build();
//            }
//
//            return retrofitWithHeader1;
//        }
//
//        public static Retrofit getSpreedlyRetrofitClient(){
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//            if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                builder.addNetworkInterceptor(interceptor);
//            }

        /*builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return onTimeOutIntercept(chain);
            }
        });*/

//            builder.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//                    HttpUrl originalUrl = original.url();
//
//                    HttpUrl url = originalUrl.newBuilder()
//                            .addQueryParameter("environment_key", SPREEDLY_KEY)
//                            .build();
//
//                    Request request = original.newBuilder()
//                            .url(url).build();
//                    return chain.proceed(request);
//                }
//            });
//
//            OkHttpClient client = builder.build();
//            return new Retrofit.Builder()
//                    .baseUrl(SPREEDLY_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(client)
//                    .build();
//        }


//        public static Retrofit getSpreedlyForPaymentMethodRetrofitClient(){
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//            if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                builder.addNetworkInterceptor(interceptor);
//            }
//
//        /*builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                return onTimeOutIntercept(chain);
//            }
//        });*/
//
//            builder.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//                    HttpUrl originalUrl = original.url();
//
//                    HttpUrl url = originalUrl.newBuilder()
//                            .addQueryParameter("environment_key", SPREEDLY_KEY)
//                            .build();
//
//                    Request request = original.newBuilder()
//                            .url(url).build();
//                    return chain.proceed(request);
//                }
//            });
//
//            OkHttpClient client = builder.build();
//            return new Retrofit.Builder()
//                    .baseUrl(SPREEDLY_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(client)
//                    .build();
//        }
//
//
//
//
//
//        public static Retrofit getDisabledCertRxRetrofitClient(final Context mContext){
//
//            if(retrofitWithRx == null){
//                retrofitWithRx = new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .client(getOkHttpClient(mContext, true).build())
//                        .build();
//            }
//
//            return retrofitWithRx;
//        }
//        public static Retrofit getRxRetrofitClient(final Context mContext){
//
//            if(retrofitWithRx == null){
//                retrofitWithRx = new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .client(getOkHttpClient(mContext).build())
//                        .build();
//            }
//
//            return retrofitWithRx;
//        }
//
//
//        private static Response onTimeOutIntercept(Interceptor.Chain chain) throws IOException {
//            try {
//                Response response = chain.proceed(chain.request());
//                JSONObject jObjError = new JSONObject(response.body().string());
//                String content = jObjError.getString("error");
//                return response.newBuilder().body(ResponseBody.create(response.body().contentType(), content)).build();
//            }
//            catch (SocketTimeoutException exception) {
//                exception.printStackTrace();
//                if(listener != null)
//                    listener.onConnectionTimeout();
//            } catch (JSONException e) {
//                //e.printStackTrace();
//            }
//
//            return chain.proceed(chain.request());
//        }
//
//        public interface OnConnectionTimeoutListener {
//            void onConnectionTimeout();
//        }
//
//    }


}
