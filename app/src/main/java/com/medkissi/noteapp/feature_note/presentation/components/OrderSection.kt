package com.medkissi.noteapp.feature_note.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medkissi.noteapp.feature_note.domain.util.NoteOrder
import com.medkissi.noteapp.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit


) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text ="Title",
                checked = noteOrder is NoteOrder.Title,
                onCheck = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text ="Date",
                checked = noteOrder is NoteOrder.Date,
                onCheck = { onOrderChange(NoteOrder.Date(orderType = noteOrder.orderType)) }
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text ="Color",
                checked =noteOrder is NoteOrder.Color,
                onCheck = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
            )

        }
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text ="Ascending",
                checked = noteOrder.orderType is OrderType.Ascending,
                onCheck = { onOrderChange(noteOrder.copy(OrderType.Ascending))}
            )
            Spacer(modifier = modifier.width(8.dp))
            DefaultRadioButton(
                text ="Descending",
                checked = noteOrder.orderType is OrderType.Descending,
                onCheck = {  onOrderChange(noteOrder.copy(OrderType.Descending))}
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun OrderSectionPreview() {

    OrderSection(onOrderChange ={})
    
}