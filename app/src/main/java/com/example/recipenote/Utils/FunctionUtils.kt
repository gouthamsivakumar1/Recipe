package com.example.recipenote.Utils

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit




    fun View.setRxOnClickListener(function: () -> Unit): Disposable {
        return RxView.clicks(this)
            .throttleFirst(2000L, TimeUnit.MILLISECONDS)
            .subscribe {
                function()
            }
    }
