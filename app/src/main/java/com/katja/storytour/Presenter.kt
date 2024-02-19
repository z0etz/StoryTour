package com.katja.storytour

import java.sql.Timestamp

class Presenter(private val view: GeneralContract.View) : GeneralContract.Presenter {

    override fun loadImage() {
        val backgroundImage = GeneralModel.backgroundImage
        view.setBackground(backgroundImage)
    }

    override fun setupAdventure(length: Int) {
        GeneralModel.adventureFinishTime = System.currentTimeMillis() + length * 60 * 1000
        println("Adventure length: $length minutes")
        println("Adventure finish time: ${Timestamp(GeneralModel.adventureFinishTime)}")
        GeneralModel.adventure = Adventure()
        setupLocation()
    }

    override fun setupLocation(storychoise: Storychoise?) {
        // Requested adventure time left in minutes
        val timeLeft = ((GeneralModel.adventureFinishTime - System.currentTimeMillis()) / 60000).toInt()

//        val waypointsList: MutableList<Waypoint> = mutableListOf()
//        val choicesList: MutableList<Storychoise> = mutableListOf()
//        var finalValue: Boolean = false

        //Random number for getting test data
        val randomNumber = (0 until TestDataModel.testLocations.size).random()

        if (GeneralModel.adventure!!.storyline!!.isEmpty()) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocationFirst
        }
        else if (timeLeft < 10) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[randomNumber]
            GeneralModel.location!!.final = true
            GeneralModel.location!!.description += " Thank you for this adventure, hope to see you another time!"
        }
        else if (timeLeft < 20) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[randomNumber]
            GeneralModel.location!!.description += " What would you like to do next? We are running out of time, so choose wisely."
        }
        else {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[randomNumber]
            GeneralModel.location!!.description += " What would you like to do next?"
        }
    }

    override fun endAdventure() {
        GeneralModel.adventureFinishTime = 0
        GeneralModel.adventure = null
        GeneralModel.location = null
    }

    override fun documentStoryline(text: String) {
        GeneralModel.adventure?.storyline += "text/n/n"
    }


}


