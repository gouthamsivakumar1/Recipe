package com.example.recipenote

import android.os.Build
import androidx.multidex.MultiDexApplication
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import com.example.recipenote.injection.appModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.io.File

/**
 * The application level class
 */
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
        setupCoil()
    }

    /**
     *  for dependency injection
     */
    private fun setUpKoin() {
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule())
        }
    }

    /**
     * for image loading
     */
    private fun setupCoil() {
        val cacheSize = Int.MAX_VALUE.toLong()
        val file = File(
            getExternalFilesDir(
                getString(R.string.app_name)
            ), "cache-image"
        )
        if (!file.exists()) {
            file.mkdirs()
        }
        coilImageLoader = ImageLoader.Builder(this@App)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(SvgDecoder(this@App))
                    add(ImageDecoderDecoder())
                } else {
                    add(GifDecoder())
                }
            }
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(Cache(file, cacheSize))
                    .build()
            }.build()
        Coil.setImageLoader(object : ImageLoaderFactory {
            override fun newImageLoader(): ImageLoader = coilImageLoader

        })

    }

    companion object {
        lateinit var coilImageLoader: ImageLoader
    }

}
