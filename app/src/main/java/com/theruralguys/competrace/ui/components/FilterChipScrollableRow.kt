package com.theruralguys.competrace.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FilterChipScrollableRow(
    chipList: List<String>,
    selectedChips: Set<String>,
    onClickFilterChip: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected: (String) -> Boolean = { selectedChips.contains(it) }

    val leadingIcon: @Composable ((visible: Boolean) -> Unit) = {
        AnimatedVisibility(visible = it) {
            Icon(
                imageVector = Icons.Rounded.Done,
                contentDescription = ""
            )
        }
    }

    /*val list = arrayListOf<String>()
    list.addAll(selectedChips)
    chipList.forEach { if(!isSelected(it)) list.add(it) }*/

    LazyRow(
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        items(chipList.size) {
            ElevatedFilterChip(
                selected = isSelected(chipList[it]),
                onClick = { onClickFilterChip(chipList[it]) },
                label = {
                    Text(
                        text = chipList[it],
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                leadingIcon = { leadingIcon(isSelected(chipList[it])) },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .animateItemPlacement(),
            )
        }
    }
}