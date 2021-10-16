package eu.lepicekmichal.kmpdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.lepicekmichal.gallery.navigation.GalleryNavigation
import eu.lepicekmichal.navigation.NavCommand

/**
 * This DEMO shows problem with abstraction and KMP modules targeting jvm while used in android modules
 * Everything is possible to build and working perfectly fine, but AS is red underlining
 *
 * Module navigation with NavCommand interface is KMP targeting jvm and android
 * Module galleryNavigation (depending on navigation) is module with interface navigating to gallery part of an app, targeting only jvm
 *      - reasons be: shorter build time, no need for android specifics, less artifacts built, etc.
 * Module app is pure android module, depending on both navigation and galleryNavigation, implementing galleryNavigation interface for
 * navigation in an android app.
 *
 * But inside app module, depending on syntax used, AS wrongly assumes jvm implementation instead of android one. Despite NavCommand
 * having actual android interface and app depending on its navigation module.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val router: GalleryRouter = GalleryRouter() // specific type, implementation of navigation interface (not suitable for decoupling)
        val abstractRouter: GalleryNavigation = GalleryRouter() // abstract type, suitable for decoupling with DI

        router.shareImageCaption().execute(this) // perfectly working, but not abstract
        abstractRouter.shareImageCaption().execute(this) // perfectly working, abstract, but red underline

        val navCommand = abstractRouter.shareImageCaption()
        val navCommand2: NavCommand = abstractRouter.shareImageCaption()
        navCommand.execute(this) // perfectly working, but red underlined
        navCommand2.execute(this) // perfectly working, not underlined, but needed to explicitly specify type
    }
}