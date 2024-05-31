# Story Tour

I started Story Tour as my final Building AI course project and aim to keep developing it as I learn more about programming and AI. 

As of June 1st 2024 the app is available as an Android prototype with pre-defined locations. If you ever find yourself near Biomedicum in Solna, Sweden, feel free to download [the APK from this repository](https://github.com/z0etz/StoryTour/tree/main/app/release) and try it out!


## Summary

Story Tour lets you explore any new location via enchanting stories, where you are the main character! In every adventure a new story unfolds, as you walk to different point of interests and make choices of what you would like to do next.


## Background

I have previously created treasure hunts and adventures where users need to find details in the real world and use those to solve different puzzles, some examples can be seen at: [Zonventure](https://zonventure.com/) and [T.I.M.O.s](https://timos.se/). Story Tour is an experiment to explore how AI can be utilized to to generate interesting and interactive adventures, directly in whatever location the user starts the app. 

With puzzles I have worked on previously, a core aim has been to make their answers un-googleable. If the answer could be found without looking at the right thing, from the right angle, irl, we scratchted the puzzle. Working with AI, I will have to make use of data that is readliy availible to anyone, so I am working on ways to present gathered location data via instructions that are obscure enough to only be understood when at the location. A simple example could be "When you pass the second red house, turn towards the highest tower". I have had some promising results prompting ChatGPT for walking directions of the type I am after from a gps location, but there is still loots of testing to do. And that is what this project is all about. 


## How is it used?

Describe the process of using the solution. In what kind situations is the solution needed (environment, time, etc.)? Who are the users, what kinds of needs should be taken into account?

<img src="/Screenshots/Screenshot_01.png" width="300">
<img src="/Screenshots/Screenshot_02.png" width="300">
<img src="/Screenshots/Screenshot_03.png" width="300">


## Data sources and AI method

In order to generate stories I will need to make use of a map API in order to get points of interest close to the users starting location and a GPT in order to write the walking instructions according to predefined prompts. While the initial tests have been made chatGPT, I would like to see if I can make use of open source resources in this project. Currently I an looking at using [OpenStreetMap API](https://wiki.openstreetmap.org/wiki/API_v0.6) and [gpt4free](https://github.com/xtekky/gpt4free) , but would be glad to hear about ather resources that may be suitable for this project!  
