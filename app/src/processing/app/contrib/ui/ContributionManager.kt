package processing.app.contrib.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.Serializable
import processing.app.Platform
import java.net.URL
import java.util.*
import kotlin.io.path.*


fun main() = application {
    val active = remember { mutableStateOf(true) }
    if(!active.value){
        Window(onCloseRequest = ::exitApplication) {

        }
        return@application
    }
    Window(onCloseRequest = { active.value = false }) {
        contributionsManager()
    }
}

enum class Status {
    VALID,
    BROKEN,
    DEPRECATED
}
enum class Type {
    library,
    mode,
    tool,
    examples,
}

@Serializable
data class Author(
    val name: String,
    val url: String? = null,
)

@Serializable
data class Contribution(
    val id: Int,
    val status: Status,
    val source: String,
    val type: Type,
    val name: String? = null,
    val categories: List<String>? = emptyList(),
    val authors: String? = null,
    val authorList: List<Author>? = emptyList(),
    val url: String? = null,
    val sentence: String? = null,
    val paragraph: String? = null,
    val version: String? = null,
    val prettyVersion: String? = null,
    val minRevision: Int? = null,
    val maxRevision: Int? = null,
    val download: String? = null,
)

@Serializable
data class Contributions(
    val contributions: List<Contribution>
)

@Composable
fun contributionsManager(){
    var contributions by remember { mutableStateOf(arrayOf<Contribution>()) }
    var localContributions by remember { mutableStateOf(arrayOf<Contribution>()) }
    val error = remember { mutableStateOf<Exception?>(null) }

    val preferences = loadPreferences()
    val sketchBookPath = preferences.getProperty("sketchbook.path.four", Platform.getDefaultSketchbookFolder().path)

    LaunchedEffect(preferences){
        val sketchBook = Path(sketchBookPath)
        sketchBook.forEachDirectoryEntry{ contents ->
            val typeName = contents.fileName.toString()
            if(!contents.isDirectory()) return@forEachDirectoryEntry
            contents.forEachDirectoryEntry { folder ->
                if(!folder.isDirectory()) return@forEachDirectoryEntry
                folder.forEachDirectoryEntry("*.properties"){ entry ->
                    val props = Properties()
                    props.load(entry.inputStream())

                    val type: Type = when(typeName){
                        "libraries" -> Type.library
                        "modes" -> Type.mode
                        "tools" -> Type.tool
                        "examples" -> Type.examples
                        else -> return@forEachDirectoryEntry
                    }

                    val contribution = Contribution(
                        id = 0,
                        status = Status.VALID,
                        source = entry.toString(),
                        type = type,
                        name = props.getProperty("name"),
                        authors = props.getProperty("authors"),
                        url = props.getProperty("url"),
                        sentence = props.getProperty("sentence"),
                        paragraph = props.getProperty("paragraph"),
                        version = props.getProperty("version"),
                        prettyVersion = props.getProperty("prettyVersion"),
                        minRevision = props.getProperty("minRevision")?.toIntOrNull(),
                        maxRevision = props.getProperty("maxRevision")?.toIntOrNull(),
                        download = props.getProperty("download"),
                    )
                    localContributions += contribution
                }
            }
        }
    }


    LaunchedEffect(Unit){
        try {
            val url = URL("https://github.com/mingness/processing-contributions-new/raw/refs/heads/main/contributions.yaml")
            val connection = url.openConnection()
            val inputStream = connection.getInputStream()
            val yaml = inputStream.readAllBytes().decodeToString()
            // TODO cache in processing folder

            val parser = Yaml(
                configuration = YamlConfiguration(
                    strictMode = false
                )
            )
            val result = parser.decodeFromString(Contributions.serializer(), yaml)

            contributions = result.contributions
                .filter { it.status == Status.VALID }
                .map {
                    // TODO Parse better
                    val authorList = it.authors?.split(",")?.map { author ->
                        val parts = author.split("](")
                        val name = parts[0].removePrefix("[")
                        val url = parts.getOrNull(1)?.removeSuffix(")")
                        Author(name, url)
                    } ?: emptyList()
                    it.copy(authorList = authorList)
                }
                .toTypedArray()
        } catch (e: Exception){
            error.value = e
        }
    }
    if(error.value != null){
        Text("Error loading contributions: ${error.value}")
        return
    }
    if(contributions.isEmpty()){
        Text("Loading contributions...")
        return
    }

    val contributionsByType = (contributions + localContributions).groupBy { it.type }
    val types = Type.entries
    val selectedType = remember { mutableStateOf(types.first()) }
    val contributionsForType = (contributionsByType[selectedType.value] ?: emptyList())
        .sortedBy { it.name }

    val selectedContribution = remember { mutableStateOf<Contribution?>(null) }

    Box{
        Column {
            Row{
                for(type in types){
                    Text(type.name, modifier = Modifier
                        .background(if(selectedType.value == type) Color.Gray else Color.Transparent)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable {
                            selectedType.value = type
                            selectedContribution.value = null
                        }
                        .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text("Updates")
            }
            Box{
                val state = rememberLazyListState()
                LazyColumn(state = state) {
                    item{
                        // Table Header
                    }
                    items(contributionsForType){ contribution ->
                        Row(modifier = Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .clickable { selectedContribution.value = contribution }
                            .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(modifier = Modifier.weight(1f)){
                                Text("status")
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.weight(8f)){
                                Text(contribution.name ?: "Unnamed", fontWeight = FontWeight.Bold)
                                Text(contribution.sentence ?: "No description", maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            Row(modifier = Modifier.weight(4f)){
                                Text(contribution.authorList?.joinToString { it.name } ?: "Unknown")
                            }
                        }
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color.LightGray)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = state
                    )
                )
            }
        }

    }

}