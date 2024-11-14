package processing.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import processing.app.Language
import processing.app.Platform
import java.awt.BorderLayout
import java.awt.Desktop
import java.io.File
import java.net.URI
import javax.swing.Box
import javax.swing.SwingUtilities


fun addJDKManger(parent: Box){
    SwingUtilities.invokeLater {
        val composePanel = ComposePanel().apply {
            setContent {
                JDKManager()
            }
        }
        parent.apply {
            add(composePanel, BorderLayout.EAST)
        }
    }
}


// If the user does not have a valid JDK installed on their system, this function will display a message and a button to download the JDK
// Temporary workaround until we ship Processing with a JDK again
@Composable
fun JDKManager(){
    val home = Platform.getJavaHome()
    val valid = File(home, "bin/java").exists()
    if(valid) return

    val color = Theme.getColor("editor.gradient.bottom")

    val os = System.getProperty("os.name")
    val arch = System.getProperty("os.arch")
    val platform = when {
        os.contains("Windows") -> "windows"
        os.contains("Mac") -> "mac"
        else -> "linux"
    }

    Row(modifier = Modifier
        .background(color = Color(color.rgb))
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Text(Language.text("editor.status.missing.jvm"))
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { openWebpage(URI("https://api.adoptium.net/v3/installer/latest/17/ga/${platform}/${arch}/jre/hotspot/normal/eclipse?project=jdk")) }) {
            Text(
                text = "Open a link",
                color = Color.Black
            )
        }
    }
}

fun openWebpage(uri: URI?): Boolean {
    val desktop = Desktop.getDesktop() ?: return false
    if (desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return false
}
