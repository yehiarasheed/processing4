package processing.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

import javax.swing.JFrame
import javax.swing.SwingUtilities


class WelcomeToBeta {
    companion object{
        val windowSize = Pair(400, 200)
        val windowTitle = "Welcome to Beta"
        val title = "Welcome to the Processing Beta"
        val message = """Thank you for trying out the new version of Processing. We’re very grateful!

Please report any bugs on the forums."""
        val buttonText = "Thank you"


        @JvmStatic
        fun showWelcomeToBeta() {
            SwingUtilities.invokeLater {
                JFrame(windowTitle).apply {
                    defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
                    contentPane.add(ComposePanel().apply {
                        setContent {
                            welcomeToBeta()
                        }
                    })
//                    setSize(windowSize.first, windowSize.second)
                    setLocationRelativeTo(null)
                    isVisible = true
                }
            }
        }

        @Composable
        fun welcomeToBeta() {
            // TODO: Add fonts and colors

            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .size(windowSize.first.dp, windowSize.second.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            )
            {
                // TODO: Add the Processing logo svg here
                Box(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
                )
                Column(modifier = Modifier
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
                ) {
                    Text(title, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                    Text(message, fontSize = 13.sp)
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        // TODO Add button shadow and make interactive
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .padding(10.dp)
                                .sizeIn(minWidth = 100.dp)

                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Text(buttonText, color = Color.White)
                        }
                    }
                }
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            application {
                val windowState = rememberWindowState(
                    size = DpSize.Unspecified,
                    position = WindowPosition(Alignment.Center)
                )
                Window(onCloseRequest = ::exitApplication, state = windowState, title = windowTitle) {
                    Surface(color = Color.White) {
                        welcomeToBeta()
                    }

                }
            }
        }
    }
}

