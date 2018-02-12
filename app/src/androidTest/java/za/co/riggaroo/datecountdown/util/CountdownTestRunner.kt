package za.co.riggaroo.datecountdown.util

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

import za.co.riggaroo.datecountdown.CountdownApplication

class CountdownTestRunner : AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, CountdownApplication::class.java.name, context)
    }
}
