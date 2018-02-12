package za.co.riggaroo.datecountdown.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

object ViewModelUtil {
    fun <T : ViewModel> createFor(model: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class " + modelClass)
            }
        }
    }
}
