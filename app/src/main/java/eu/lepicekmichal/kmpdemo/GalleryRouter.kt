package eu.lepicekmichal.kmpdemo

import android.content.Intent
import eu.lepicekmichal.gallery.navigation.GalleryNavigation
import eu.lepicekmichal.navigation.NavCommand
import eu.lepicekmichal.navigation.navCommand

class GalleryRouter : GalleryNavigation {

    override fun shareImageCaption(): NavCommand = navCommand { context ->
        val intent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Shared caption")
        }, "Share")

        context.startActivity(intent)
    }

}