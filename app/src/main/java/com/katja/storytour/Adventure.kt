package com.katja.storytour

class Adventure {
    var storyline = ""
}

class Location(
    var waypoints: MutableList<Waypoint>,
    var choices: MutableList<Storychoise>,
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
