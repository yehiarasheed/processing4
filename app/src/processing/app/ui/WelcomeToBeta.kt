package processing.app.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import javax.swing.JFrame
import javax.swing.SwingUtilities


class WelcomeToBeta {
    companion object{
        @JvmStatic
        fun showWelcomeToBeta() {
            SwingUtilities.invokeLater {
                JFrame("New Window Title").apply {
                    defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
                    contentPane.add(ComposePanel().apply {
                        setContent {
                            welcomeToBeta()
                        }
                    })
                    setSize(400, 300)
                    setLocationRelativeTo(null)
                    isVisible = true
                }
            }
        }

        @Composable
        fun welcomeToBeta() {
            Text("Welcome to the Beta version of Processing!")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            application {
                Window(onCloseRequest = ::exitApplication) {
                    welcomeToBeta()
                }
            }
        }
    }
}

