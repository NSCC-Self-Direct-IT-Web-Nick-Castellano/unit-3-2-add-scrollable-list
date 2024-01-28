/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.ui.theme.AffirmationsTheme

// import the affirmationsdata class
import com.example.affirmations.model.Affirmation
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

// the following practice app will display affirmations
// in a list way you can scroll with the ui of the
// device. This is a good intro to scrolling functionalities
// and the use of collections in kotlin and android apps
@Composable
fun AffirmationsApp() {
    // render all the affirmations with the affirmation
    // list composable. When you run the app. You can 
    // scroll with your finger or m
    AffirmationList(affirmationList = Datasource()
        .loadAffirmations(),
    )
}


// this component takes the affirmation data class
// and displays it's values in the ui screen
// values are resources: string affirmation message and
// drawable affirmation image
@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
     Card(modifier = modifier) {
         Column {
            Image(
                painter = painterResource(
                    affirmation.imageResourceId
                ),
                contentDescription = stringResource(
                    id = affirmation.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                ,
                contentScale = ContentScale.Crop
            )
             Text(
                 text = LocalContext.current.getString(
                     affirmation.stringResourceId
                 ),
                 modifier = Modifier.padding(16.dp),
                 style = MaterialTheme
                     .typography.headlineSmall
             )
         }
     }
}

// create a list for all the affirmation cards use the
// affirmationList param, it has all the affirmations we
// want to display, for this we will use the datasource list
@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
private fun AffirmationCardPreview() {
//    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
    AffirmationsApp()
}
