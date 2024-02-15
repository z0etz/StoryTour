package com.katja.storytour

class Adventure {
    val storyline = ""
}

class Location(
    val waypoints: MutableList<Waypoint>,
    val choices: MutableList<Storychoise>,
    var final: Boolean,
    var description: String
)

class Waypoint (
    val latitude: Double,
    val longitude: Double,
    val description: String
)

class Storychoise (
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String
)
