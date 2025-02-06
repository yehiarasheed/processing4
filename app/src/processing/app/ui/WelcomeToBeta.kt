package processing.app.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.formdev.flatlaf.util.SystemInfo
import java.awt.Cursor
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.SwingUtilities


class WelcomeToBeta {
    companion object{
        val windowSize = Dimension(400, 200)
        val windowTitle = "Welcome to Beta"
        val title = "Welcome to the Processing Beta"
        val message = """Thank you for trying out the new version of Processing. We’re very grateful!

Please report any bugs on the forums."""
        val buttonText = "Thank you"

        @JvmStatic
        fun showWelcomeToBeta() {
            val mac = SystemInfo.isMacFullWindowContentSupported
            SwingUtilities.invokeLater {
                JFrame(windowTitle).apply {
                    val close = { dispose() }
                    rootPane.putClientProperty("apple.awt.transparentTitleBar", mac)
                    rootPane.putClientProperty("apple.awt.fullWindowContent", mac)
                    defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
                    contentPane.add(ComposePanel().apply {
                        size = windowSize
                        setContent {
                            Box(modifier = Modifier.padding(top = if(mac) 22.dp else 0.dp)) {
                                welcomeToBeta(close)
                            }
                        }
                    })
                    pack()
                    background = java.awt.Color.white
                    setLocationRelativeTo(null)
                    addKeyListener(object : KeyAdapter() {
                        override fun keyPressed(e: KeyEvent) {
                            if (e.keyCode == KeyEvent.VK_ESCAPE) close()
                        }
                    })
                    isResizable = false
                    isVisible = true
                    requestFocus()
                }
            }
        }

        @Composable
        fun welcomeToBeta(close: () -> Unit = {}) {
            // TODO: Add fonts and colors

            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .size(windowSize.width.dp, windowSize.height.dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Image(
                    painter = painterResource("logo.svg"),
                    contentDescription = "Processing Logo",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp, 100.dp)
                )
                Column(
                    modifier = Modifier
                            .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
                ) {
                    Text(
                        title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        message,
                        fontSize = 13.sp
                    )
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        PDEButton(onClick = {
                            close()
                        }) {
                            Text(buttonText, color = Color.White)
                        }
                    }
                }
            }
        }
        @OptIn(ExperimentalComposeUiApi::class)
        @Composable
        fun PDEButton(onClick: () -> Unit, content: @Composable BoxScope.() -> Unit) {
            var hover by remember { mutableStateOf(false) }
            var clicked by remember { mutableStateOf(false) }
            val offset by animateFloatAsState(if (hover) -5f else 5f)
            val color by animateColorAsState(if(clicked) Color.Black else Color.Blue)

            Box(modifier = Modifier.padding(end = 5.dp, top = 5.dp)) {
                Box(
                    modifier = Modifier
                        .offset((-offset).dp, (offset).dp)
                        .background(Color.Gray)
                        .matchParentSize()
                )
                Box(
                    modifier = Modifier
                        .onPointerEvent(PointerEventType.Press) {
                            clicked = true
                        }
                        .onPointerEvent(PointerEventType.Release) {
                            clicked = false
                            onClick()
                        }
                        .onPointerEvent(PointerEventType.Enter) {
                            hover = true
                        }
                        .onPointerEvent(PointerEventType.Exit) {
                            hover = false
                        }
                        .pointerHoverIcon(PointerIcon(Cursor(Cursor.HAND_CURSOR)))
                        .background(color)
                        .padding(10.dp)
                        .sizeIn(minWidth = 100.dp),
                    contentAlignment = Alignment.Center,
                    content = content
                )
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
                        welcomeToBeta{
                            exitApplication()
                        }
                    }

                }
            }
        }
    }
}

