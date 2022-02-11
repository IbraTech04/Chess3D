# jChess
Simple yet powerful 2D/3D chess game, written in Java using the Processing P3D Renderer

Developed by Ibrahim Chehab and Fardeen Kasmani - iFlySoft - in ICS 4U Year 2022

## Why, IFlySoft? Why chess
Well for starters, Ibrahim doesn't know how to play chess and Fardeen thought this would be a cool way to make a friend learn it (sidenote, this led to an insane amount of hillarious "bugs" that weren't really bugs. Our code was fine, he just didn't know how the game worked)

We decided to challenge ourselves to use the P3D renderer. we had never created anything in 3D space and thought it would be a cool challenge. My partner in crime, Fardeen, was dragged along my infinite brainsurges of ideas; Some useless, some never to see the light of day, heck some he implemented himself!

This chess game includes Ibrahim's new UltraProteccV7 licence protection scheme. It's fairly basic as he doesn't have a math degree, but regardless it'll get the job done :) It expands the availible character set to include letters! It also no longer has set set-sizes, only set key sizes.

## Features:

* System requirements check:
  * Let's be real -We are not software engineers.... yet. We certainly don't write the most optimized code in the world, and the Processing P3D renderer doesn't really help with that either. So to be safe (and hopefully not fry anyones system), we added a minimum system requirements check that runs when you first start the game. If your computer doesnt meet our "reasonable" requirements, you have the option of either continuing in 3D mode like a rebel, swap over to 2D mode, or just exit the game entirely. 
  
* UltraProteccV7:
  * So for whatever reason we decided to add a licence key system to an open source game... Yeah.... But hey! More code = More Productivity!
  
* Customizable Board Colours
  * All those *other* chess games out there don't let you change your colours. *Pfft* With jChess you can select custom grid colours! Now why any normal user would go through and change all that is beyond me, but hey it was easy so why not :)
  
* Intuitive UI
	* They say don't judge a book by it's cover. That said, we are "software engineers" not front-end devs. We promise our code is better than our GUIs. That said, simple UIs are often the best, so that's what we went with. Simple, "elegant", and it works.

	
## How the code works

Our code leverages the usage of the processing core. Normally, the processing IDE gives the user a level of abstraction, hiding the pure Java code. Whenever a processing sketch is compiled, it get's converted to pure Java code, then compiled into bytecode which is then fed into a JVM to be executed. We bypassed the abstraction to gain complete access to the Processing Core via Eclipse by importing the processing core directly into our project, creating classes that extend PApplet (the main class of the processing core), and writing the same code that would've been generated, had we created the game directly in the Processing IDE

This allowed us access to Eclipse's superb debugger, and it's wide variety of plugins, namely Saros. Saros is a collaborative debugging plugin which basically makes a Eclipse project like a shared Google Doc. This allowed IFlySoft to work in parallel, speeding up the development process. 

We used a lot of Processing libraries like UiBooster, however had to rework them in pure Java (taken directly from the library's source code) in order for them to work properly under non-processing conditions. You'll notice the ColorPickerDialog class was modified from UiBooster's source to work in our required setting. 

Our code's main entry point is in Main.java. This file contains the necissary code to set up the environment so that jChess loads correctly. It also contains the licence key check. If you're feeling lucky, you can try this key: 
`YEENKFK-BBUOK8F6IO:Y@6U8E-@;9-NF`
Or you can use the built in keygen class to make your own

UltraProteccV7 works in the following way:
The first 7 letters must add up to a value divisble by 7
The next 17 letters must add up to a value divisible by 17
The next 3 letters must add up to a value divisible by 3
The final two letters must add up to a value divisble by 2

This system is heavily based on the Windows 95 CD-Key system, however opens up the possible character set from just numbers to numbers, letters, and symbols

