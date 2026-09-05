package com.example.achievements14_b.Compose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.achievements14_b.MainViewModel
import com.example.achievements14_b.data.model.RecordsModel

@Composable // present the whole screen in this func
fun AchievementScreen(viewModel: MainViewModel) {

    //Task 4 - read values from MainViewModel

    //read LiveData from MainViewModel for all 3 states
    val isLoading by viewModel.achievementsLoadingLiveData.observeAsState(false)

    val error by viewModel.achievementsErrorLiveData.observeAsState()

    val success by viewModel.achievementsSuccessLiveData.observeAsState()


    //Task 3 - display states for UI
    val context = LocalContext.current //to show toast message

    Box(

            modifier = Modifier . fillMaxSize ()
    ){
        //Load
            if (isLoading){ //loading LiveData from MainViewModel
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                ) //for Spinner
            }

        //Errors
        val currentError = error

        if (currentError != null){

            LaunchedEffect (currentError) {
                Toast.makeText(
                    context,
                    currentError.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //Success
        val currentSuccess = success

        if (currentSuccess != null){
          val records = mutableListOf<RecordsModel>()

            currentSuccess.forEach{ achievement ->
                records.addAll(achievement.records)
            }
            taskTwo(records)
        }
    }
}

//Task 1:
@Composable
fun RecordCard(record: RecordsModel) {

    // make it vertical as assignment asked
    Column(
        modifier = Modifier.alpha( // active flag
            if (record.active){ 1f } else 0.5f
        ),
        horizontalAlignment = Alignment.CenterHorizontally //to center it Horizontally
    ){
        //image
        AsyncImage(
        model = record.image, //image URL
        contentDescription =  record.title
        )
        Text(  //title
            record.title)
        Text( //label
            record.label)
    }
}

//Task 2:
@Composable
fun taskTwo(records: List<RecordsModel>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    )
    {
        item (
            span = { GridItemSpan(maxLineSpan) } //a full-width header
        ){
            Text("Achievements - 4 of 6" ) //this title above Items records
        }

    items(records) // goes through records one by one
    {  record ->
        RecordCard(record) //display all the record
    }

    }
}

//preview for task 1
@Preview(showBackground = true)
@Composable
fun RecordCardPreview(){
    RecordCard(
        record = RecordsModel(
            id = 1,
            title = "First Achievement",
            label = "Completed",
            image = "https://share.google/3jBLpqu2goHykqHEE",
            active = true
        )
    )
}
//preview for task 2
@Preview(showBackground = true)
@Composable
fun TaskTwoPreview(){
    val records = listOf(
        RecordsModel(
            id = 1,
            title = "First Achievement",
            label = "Completed",
            active = true,
            image = ""
        )
    )
}


//preview for task 3
@Preview(showBackground = true)
@Composable
fun TaskThreeLoginPreview(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

