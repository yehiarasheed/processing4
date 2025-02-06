package processing.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import java.io.InputStream
import java.util.*

class Locale : Properties() {
    init {
        val locale = java.util.Locale.getDefault()
        load(ClassLoader.getSystemResourceAsStream("PDE.properties"))
        load(ClassLoader.getSystemResourceAsStream("PDE_${locale.language}.properties") ?: InputStream.nullInputStream())
        load(ClassLoader.getSystemResourceAsStream("PDE_${locale.toLanguageTag()}.properties") ?: InputStream.nullInputStream())
    }

    @Deprecated("Use get instead", ReplaceWith("get(key)"))
    override fun getProperty(key: String?): String {
        return super.getProperty(key)
    }
    operator fun get(key: String): String = getProperty(key)
}
val LocalLocale = compositionLocalOf { Locale() }
@Composable
fun LocaleProvider(content: @Composable () -> Unit) {
    val locale = Locale()
    // TODO: Listen for languages changes
    CompositionLocalProvider(LocalLocale provides locale) {
        content()
    }
}