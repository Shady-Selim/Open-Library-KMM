package com.shady.openlibrary.android.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.shady.openlibrary.entities.LibraryDocument


@Composable
fun DetailsActivity(doc: LibraryDocument) {
    Column() {
        Text(text = "Title: ${doc.title_suggest}",
            style = MaterialTheme.typography.h4)
        doc.author_name?.let {
            Text(text = "Author/s: ${it.toString()
                .replace("[", "")
                .replace("]","")}",
                style = MaterialTheme.typography.h5,
                fontStyle = FontStyle.Italic)
        }
        Row() {
            doc.isbn?.let {
                if (doc.isbn!!.size >5){
                    for (i in 0..4){
                        drawImage(doc.isbn!![i])
                    }
                } else {
                    it.forEach {
                        drawImage(it)
                    }
                }
            }
        }

    }

}

@Composable
fun drawImage(image: String){
    Image(painter = rememberImagePainter(
        data = "http://covers.openlibrary.org/b/isbn/$image-M.jpg"),
        contentDescription = image,
        modifier = Modifier.height(100.dp),
        contentScale = ContentScale.Fit,
    )
}