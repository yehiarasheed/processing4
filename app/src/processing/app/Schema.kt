package processing.app

import processing.app.ui.Editor
import java.io.File
import java.net.URI
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


class Schema {
    companion object{
        private var base: Base? = null
        @JvmStatic
        fun handleSchema(input: String, base: Base): Editor?{
            this.base = base
            val uri = URI.create(input)
            return when (uri.host) {
                null -> handleLocalFile(uri.path)
                "sketch" -> handleSketch(uri)
                "preferences" -> handlePreferences(uri)
                else -> handleRemoteFile(uri)
            }
        }
        private fun handleLocalFile(input: String): Editor?{
            return base?.handleOpen(input)
        }
        private fun handleSketch(uri: URI): Editor?{
            val paths = uri.path.split("/")
            return when(paths.getOrNull(1)){
                "base64" -> handleSketchBase64(uri)
                else -> null
            }
        }
        private fun handleSketchBase64(uri: URI): Editor?{
            val tempSketchFolder = SketchName.nextFolder(Base.untitledFolder);
            tempSketchFolder.mkdirs()
            val tempSketchFile = File(tempSketchFolder, "${tempSketchFolder.name}.pde")
            val sketchB64 = uri.path.replace("/base64/", "")
            val sketch = java.util.Base64.getDecoder().decode(sketchB64)
            tempSketchFile.writeBytes(sketch)
            val editor = base?.handleOpenUntitled(tempSketchFile.absolutePath)
            return editor
        }

        private fun handlePreferences(uri: URI): Editor?{
            val options = uri.query?.split("&")
                ?.map { it.split("=") }
                ?.associate {
                    URLDecoder.decode(it[0], StandardCharsets.UTF_8) to
                    URLDecoder.decode(it[1], StandardCharsets.UTF_8)
                }
                ?: emptyMap()
            for ((key, value) in options){
                Preferences.set(key, value)
            }
            Preferences.save()

            return null
        }

        private fun handleRemoteFile(uri: URI): Editor?{
            return null
        }
    }
}