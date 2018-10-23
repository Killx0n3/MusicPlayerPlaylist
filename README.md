# Shuffling Music Data
![Java](https://img.shields.io/badge/Language-Java-orange.svg)

## Project Details
We are all used to the idea of sorting data in order to process it more effectively. But for some applications the opposite is required: to have data presented to us randomly jumbled. Music players like iTunes, iPods and iPhones have a “shuffle” setting for example: this could be called an anti-“boredom” application.
It turns out that randomly shuffling data is not an easy thing to get right on a computer even if using an accurate random number generator: in fact it's easy to get wrong! For more information on why that is so have a look at the resources here, attend the lectures, and read some old reviews of the iTunes shuffle facility here.
The original “Fisher-Yates” shuffle algorithm was popularised by Donald Knuth. It runs in O(n) in the worst case, but uses direct addressing on an array. What if it turns out that the data we want to shuffle is not stored on an array? We will use linked list. Which then allows flexible length which is what is required for implementing play lists in music players. 

## Goal
In this project we will implement sorting and shuffling on a linked list, adapting basic algorithmic strategies. We will use this algorithms to program a simple “MusicPlayer” class which includes the functionality to shuffle.

## Smart Features
### Smart Shuffle
smartShuffle produces a random shuffle of all the tracks in the list, EXCEPT that no track appears in the shuffled list after any other track with higher played field. For example if there are 9 files, of which 2 have never been played, 3 have been played once and 4 have been played 4 times. Running smartShuffle on this playlist will mean that both the unplayed tracks will appear before the 3 single-time played tracks, and all of these will come before the remaining 4 tracks. However inspecting the two single played files, you'll see they are randomly shuffled; similarly the three singly-played files, and similarly the remaining 4 files.

### Recommended 
recommended tries to create a playlist that is based on browsing preferences. It uses a history of listening activity and creates a playlist that orders music it thinks you like. It does this by searching for the most frequently listened-to tracks in a list that logs tracks listened to or browsed for; it then rearranges the playlist so that tracks now appear only if they appear in the history list and ordered from most popular to least popular. (This is opposite to shuffling, and perhaps is just the “comfort” setting...)

## Author
#### M TanVir Hossain

Software Engineer

Sydney, Australia

Email: hossain.tanvir.m@gmail.com

Originally this project was done by me in May, 2017. 
