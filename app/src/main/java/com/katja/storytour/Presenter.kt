package com.katja.storytour

import android.content.Context
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

    override fun setupLocation(storyChoise: Storychoise?) {
        // Requested adventure time left in minutes
        val timeLeft = ((GeneralModel.adventureFinishTime - System.currentTimeMillis()) / 60000).toInt()

    //        val waypointsList: MutableList<Waypoint> = mutableListOf()
//        val choicesList: MutableList<Storychoise> = mutableListOf()
//        var finalValue: Boolean = false


        // For setup of test locations only
        setupTestLocation(storyChoise, timeLeft)

    }

    fun setupTestLocation(storychoise: Storychoise?, timeLeft: Int) {

        // Chosen location
        var locationName = "Starting point"
        if(storychoise != null) {
            locationName = storychoise.name
        }
        var locationNumber = 0
        when (locationName) {
            "Music" -> locationNumber = 0
            "Elephants" -> locationNumber = 1
            "Gazebo" -> locationNumber = 2
        }

        if (GeneralModel.adventure!!.storyline.isEmpty()) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocationFirst
        }
        else if (timeLeft < 10) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[locationNumber]
            GeneralModel.location!!.final = true
            GeneralModel.location!!.description += " Thank you for this adventure, hope to see you another time!"
        }
        else if (timeLeft < 20) {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[locationNumber]
            GeneralModel.location!!.description += " What would you like to do next? We are running out of time, so choose wisely."
        }
        else {
            //Get location from test data
            GeneralModel.location = TestDataModel.testLocations[locationNumber]
            GeneralModel.location!!.description += " What would you like to do next?"
        }
    }


    fun calculateDistanceToWaypoint(context: Context, waypoint: Waypoint): Int {
        val currentLocation = PositionManager.getCurrentLocation(context)
        if (currentLocation != null) {
            return PositionManager.calculateDistance(currentLocation)
        }
        return -1 // Return a negative value if current location is not available
    }

    override fun endAdventure() {
        GeneralModel.adventureFinishTime = 0
        GeneralModel.adventure = null
        GeneralModel.location = null
    }

    override fun documentStoryline(text: String) {
        GeneralModel.adventure?.storyline += "$text\n\n"
    }


}


