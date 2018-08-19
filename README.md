# Pinball - Homework CC3002

Basic logic for the pinball game and GUI, sadly both are not linked yet, so no score nor
fancy hittable upgrade.

## Getting Started

If you want to download the project hit te good ol' green download button, 
and go for the classic git clone _url_ on your terminal.
Now the thing is this project doesn't do much, is just the logic, so imma keep
 y'all updated so when the GUI is finished you can enjoy this WinXP 
 masterpiece we all played in our childhoods.

### Prerequisites

So for this to run you just need Java 8 installed, that's all.
Not much ain't it?

### Installing
This project doesn't really need installation, because it basically does 
nothing yet. But again, if you wanna run some tests, or actually help me 
develop this you'll need to hit your system with the classic:

```
sudo apt install openjdk-8-jre
```
Or if you wanna help develop the project
```
sudo apt install openjdk-8-jdk
```
I'm hoping you'are using some Debian based Linux distro, otherwise I really 
don't know how to install packages.
## How to play
For you to play the game yo need to compile the file PinballApp located in the GUI package.
The keys to play are:
* Left arrow key - Activate left flipper
* Right arrow key - Activate right flipper
* N - Set new table
* Space bar - Throw new ball
* CTRL+r - Reset game

Some details tho, when you first start the game, there is no table set, so you
cannot throw a new ball. So first you need to set a new table to star playin'.
Then, you cannot throw a new ball when there is already one in play.
Also, you need to wait 'til  you loose the current ball in order to set a new table.

The color codes for the hittables are (not that it matter, they do nothing):
* SpotTarget - Light blue
* DropTarget - Light green
* KickerBumper - Light pink
* PopBumper - Light yellow

## Features
Just the independent flippers.

## Running the tests

Ahm, yeah.

So I'm using JetBrains' IDEA IntelliJ IDE, and I just click a button to run 
the tests (which are located on the test directory inside src).

I'm using JUnit4 to test tho, and you probably can google how to test with 
JUnit4 using your environment of choice.

### Break down into end to end tests

I don't really understand what is supposed to go here.

### And coding style tests


Here neither.

## Deployment

Yeah right.

## Built With

* [Maven](https://maven.apache.org/) (I belive) - Dependency Management

## Contributing

If you wanna contribute, feel free to do so, I'd be flattered.

Now, the thing is I don't really know what a pull request is, so your 
contributions will most likely end up living in purgatory.

But hey, it's the thought that counts.

## Versioning

Not really. 

## Authors

* **Gabriel Chaperon** - *Almost everything* - [GabrielChaperon](https://github.com/GabrielChaperon)

* **Juan-Pablo Silva** - *The template for the project* - [juanpablos](https://github.com/juanpablos)

See also the list of [contributors](https://github.com/GabrielChaperon/cc3002-pinball/contributors) who
(did not) participate in this project.

## Acknowledgments

* My mom and dad, who are always supportive.
* My girlfriend, who is supportive most of the time.
* Alexander Bergel, for the luls and for teaching me how to be an OUTSTANDING ENGINEER.
* Inspiration.
* Alcohol.