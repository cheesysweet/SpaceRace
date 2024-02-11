# Project

## Environment & Tools
OS: Windows 10 64bit

IDE: IntelliJ 2020.3.3

Java: 15.0.2+7

Git: 2.24.1.windows.2

Maven: 3.6.3

## Purpose
The program is a bet game where players can place bets on different spaceships that race along the track. If the player
places a winning bet they get a 5x payout. 

#### GamePlay
The game is played by first starting the spaceship class where the user gets an interface where they can select how many
players that want to participate. After they selected number of participants they can press new game button that starts
interfaces for each of the players. In the player interfaces each player can place bets on different spaceships. In the
game interface there will be different buttons depending on the game state. The new game button creates a new race with
selected amount of players, start button starts a race and stop button stops the game and closes the player interfaces, 
and a new game can be created.

## Procedures
#### SpaceRace
The SpaceRace class is the main class that executes a new race and keeps updating the raceUI if the game is running or
if announces the winner of the race. It starts the gameUI in a separate thread than the race.

#### Constants
Just contains common values in our game. This to minimize redundancies and enable scaling and flexibility.

#### Race
The Race class starts by creating 5 ships and stores those racing ships in a list. It then moves the ships along the 
track until there is a winning ship. The race is completed when one of the ships distance is greater than the length of
the racetrack.

#### SpaceShip
Each ship stores their own distance and each spaceship gets a random step count each time it moves along the track that
gets added to there total distance.

#### RaceUI
The RaceUI extends JFrame and implements RaceListener. This is the frame for the race that contains the RacePanel and
RaceInfo classes. It is also used to repaint the RacePanel and update the RaceInfo by overridden methods from 
RaceListener. 

#### RaceListener
Interface used by the Race class to update information and race progress in the RaceUI.

#### RacePanel
The RacePanel extends JPanel that's used to display all the spaceships it loads images for each of the ships and stores
them as buffered images in a ArrayList. It then paints all the ships on the panel and positioning then depending on 
there distance along the racetrack.

#### RaceInfo
RaceInfo is used to select number of player, start a new game, start a race and stop race. The user first gets to select
how many players there will be (1-5) and then press NEW GAME. After that the buttons change to START or STOP GAME, where
start runs the game once and displays who win next to the buttons. STOP GAME closes the interfaces of all the players, 
and they can select a new amount of players.

#### PlayerManagerUI
Abstract UI for the players that extends JFrame and implements ActionListener where they can add bets to different ships. 
It uses radio buttons to select witch ship to bet on and stores the bets in a map, so a player can bet on different 
ships. The players start with 1000 cash, and a bet amount of 100 that can be changed. Once the race start no more bets 
can be placed until the race ends.

#### NotEnoughFounds
UI that displays a text that the player is out of founds. It goes throe abstract factory pattern that in Player class
creates a new frame that displays a label that says that you are out of found with player number as title.

#### Notifier
Notifier interfaced used by NotEnoughFounds.

#### Player
Player extends PlayerManagerUI and stores the bets placed when the player presses the place bet button. It resets the 
bets after each game and if the player don´t have enough founds to place the bet a new playerNotifier is created. This
is part of the abstract factory where a PlayerNotifier is created that then uses create method to create a new 
NotEnoughFounds.

#### PlayerNotifier
PlayerNotifier implements the PLayerFactory<Notifier> and this is used to create a new NotEnoughFounds frame or return 
null.

#### PlayerFactory<T>
Abstract factory interface with a create method.

#### PlayerInterface
Is responsible for creating the players and storing them in a Deque array. It implements methods to close the interfaces,
toggle the bet button, calculate winning bets and clear bets placed.

#### BaseThread
BaseThread is an abstract class that implements runnable that contains a sleep and killThread method.

#### Consumer / Producer
The producer adds the given value to the BetPool, and the consumer removes the given value from the BetPool.

#### BetPool
The betPool class uses a volatile int that represents the amount of founds the race organizer got. It got methods
used to add and subtract values from the betPool that are synchronized so only one thread can add or remove at once. It
got a method to get the amount left in the betPool that has not been implemented, was going to use it with observer
pattern to stop more bets from being placed and informing the players that the game is over.

#### BetPoolManager
Is responsible for creating producers and consumers.

#### ObjectPool
The ObjectPool class uses a executorService to make a fixed thread pool that are then executing a random amount of 
producers and consumers. The producers are added when a new game is created and consumers are created when a winner
is declared. These act as random players that are also placing bets.

## Discussion
We have completed the implementations and therefore completed the purpose of this project is completed. The program lets
you bet on 5 different spaceships that race along the track.

### Requirements
#### Concurrent processes
In this implementation there are between 3-7 concurrent processes depending on how many random players are created.
The main thread handles the RaceData and then a new thread is created to handle the EDT. Then the ObjectPool creates
between 1-10 producers that are executed in a threadPool with 5 threads.

#### Synchronized processes
The synchronized processes handles the update from the producers/consumers that add or subtract values from the
atomic betPool. This to make it thread safe so no values will be lost since the producers/consumers are running in
different threads.

#### Swing components
JFrame are used by extending the classes RaceUI, PlayerManagerUI and NotEnoughFounds where each of these create a
separate window to display information.
JPanel are extended in RaceInfo and are created in RaceUI. RaceInfo are used to display a JLabel that stats by telling
the user to select a number of players from the JComboBox. The JLabel is later updated with new text when the game is 
running. It also uses JButtons that the user can press.
JFormattedTextField is used in PlayerManagerUI and starts by displaying 100 and this is the betting amount that the player
can change. The PlayerManagerUI also displays 5 JRadioButtons, so the player can only choose one to bet on one.

#### Swing layouts
BorderLayout is used in RaceUI and PlayerManagerUI. This helps in RaceUI to display the main content in the center and 
the options at the bottom of the frame.
FlowLayout is used in RaceInfo to display the different options in a row.
GridLayout is used in PlayerManagerUI to add labels to the radioButtons. Made it super easy to place the buttons and
labels in a 2 wide grid.
BoxLayout is used in NotEnoughFounds. was just used to get in a last swing layout.


#### Design patters
Object Pool is used to act like random bets are placed. It uses the fixed thread pool, so the players get executed when
there is an empty thread available for them. This made it easy to implement some random players into the game but was
only implemented to include the pattern.
Producer / Consumer patters is used to add and subtract values from a shared betting pool where the players bet gets
added in the producer and if the player made a winning bet the consumer removes the bet. They are synchronized, and the 
betPool is a volatile int which make them thread safe. This works good if the game would end if the betPool gets empty.
Template method is used in Player / PlayerManagerUI where the players radioButtons and JLabels for are added for the 
amount of ships. This could have been done in PlayerManagerUI but did this to implement the Template method,
Abstract Factory is used by NotEnoughFound, Notifier, PlayerFactory and PlayerNotifier where the playerNotifier is the
factory that creates a new NotEnoughFounds. The PlayerFactory is the AbstractFactory interface and Notifier is the
NotEnoughFound interface. NotEnoughFounds is the concrete implementation and are responsible for creating a GUI if the
player is out of founds. Not really useful in the program and are only for requirements. Would be more useful if we would
create more gui´s like create a new frame when the betPool is out of founds.
Factory method is used in BetPoolManager, BaseThread, Producer and Consumer. BaseThread is our abstract class and
Producer / Consumer extends BaseThread. The BetPoolManager then generate producers or consumers based on given method.
This is useful to get rid of some duplicate code that otherwise would be needed in both producer and consumer.
Observer has not been implemented but could have been used by the players when they are placing a bet.

#### Stream Api
Stream api is used in PlayerInterface to calculate if a player has placed a winning bet. This helps to easily filter out
the players who placed a winning bet, and the performs the method to add founds depending on bet amount placed on the
winning ship.

#### Problems
I have not really had any major problems just some small ones mostly stuff I forgot to implement at first.

I had some problems with the founds for the players where at first if they placed a bet the bet would stay even if they
did not place a bet. This was solved by just removing all bets placed after each race.

At first my stream that calculates the winnings just checked if the player placed a winning bet and then calculated the
winnings on all the bets the player had. Fixed this by changing the stream to only take the bet placed on the winning ship.

I got some problem where when the player places a bet it takes a few seconds before anything gets registered. I don't
know why it stared doing so and have not been able to fix it.