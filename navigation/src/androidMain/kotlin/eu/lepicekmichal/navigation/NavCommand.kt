package eu.lepicekmichal.navigation

import androidx.fragment.app.FragmentActivity

actual interface NavCommand {
    fun execute(context: FragmentActivity)
}

inline fun navCommand(crossinline block: (FragmentActivity) -> Unit) = object : NavCommand {
    override fun execute(context: FragmentActivity) {
        block(context)
    }
}