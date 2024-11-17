package processing.app.contrib.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun ContributionPane(contribution: Contribution?) {
    if(contribution == null) {
        return
    }

    Box {
        Text("Contribution: ${contribution.name}")
    }
}