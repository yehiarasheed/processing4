package processing.app.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import processing.app.Language
import processing.app.Platform
import java.awt.BorderLayout
import java.awt.Desktop
import java.io.File
import java.net.URI
import javax.swing.SwingUtilities


fun addJDKManger(parent: javax.swing.Box){
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
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun JDKManager(){
    val home = Platform.getJavaHome()
    val valid = File(home, "bin/java").exists()
    if(valid) return

    val color = Theme.getColor("status.notice.bgcolor")
    val colorText = Theme.getColor("status.notice.fgcolor")
    val buttonColor =   Theme.getColor("toolbar.button.enabled.field")
    val buttonTextColor = Theme.getColor("toolbar.button.enabled.glyph")

    val fontFamily = FontFamily(
        Font(
            resource = "font/ProcessingSansPro-Regular.ttf",
            weight = FontWeight.W400,
            style = FontStyle.Normal
        )
    )

    val os = System.getProperty("os.name")
    val arch = System.getProperty("os.arch")
    val platform = when {
        os.contains("Windows") -> "windows"
        os.contains("Mac") -> "mac"
        else -> "linux"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(color.rgb)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                "JDK not found. Please download the JDK to run Processing.",
                fontFamily = fontFamily,
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Color(colorText.rgb)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(

                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable{
                        openWebpage(URI("https://api.adoptium.net/v3/installer/latest/17/ga/${platform}/${arch}/jre/hotspot/normal/eclipse?project=jdk"))
                    }
                    .background(color = Color(buttonColor.rgb))
                    .padding(16.dp, 8.dp)

                ,
            ) {
                Text(
                    text = "Download JDK",
                    color = Color(buttonTextColor.rgb),
                    fontFamily = fontFamily
                )
            }
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
