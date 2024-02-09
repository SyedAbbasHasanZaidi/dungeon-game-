# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[harish/movement-interface](https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/merge_requests/1)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

The main issues lies within the move method present within the base & derived classes. Given that the enemy superclass implements an abstract method move, this requires all derived classes to implement said function. This is however in flaw maintainability as not all future subclasses does not need/require a move method, this can be seen presently within the enemy superclass defined as an abstract method. It can be seen in the current code that each move method implementation in the current code uses a different move algorithm to define the enemy’s movement. This is a repeated line of code.

> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

Since each move function is  an implementation of a different strategy utilizing the Strategy pattern to create an interface that abstracts the move function into a separate interface allows each enemy subclass to independently implement an algorithm to describe its move functionality. 

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

Given the issues discussed above, we will remove the abstract move method within the Enemy super class. Create an movement interface which has a move method defined within that requires each class that inherits the interface to implement a custom strategy for movement. 

### b) Observer Pattern

> Identify one place where the Observer Pattern is present in the codebase, and outline how the implementation relates to the key characteristics of the Observer Pattern.

The Observer pattern is a OOP design pattern in which an observer maintains a list of objects as its subjects and the state of the subjects is constantly monitored either by the subject alerting the observer or vice versa. This pattern appears in the code base within the context of bombs and switches. Bombs and switches work when bombs are placed next to switches, each bomb and switches both maintain a list of switches and bombs respectively. When a switch is activated then it go through a list of bombs that it subscribes to and detonates them depending on the switches location to the bomb. From the POV of the bomb it is the subject as the switch maintains a list of bombs and notifies the corresponding bombs whether to detonate, this is the push method of notification as the switch pushes the detonation message to the bomb rather than the bomb requesting it via a pull. Although the bomb also has a list of switches it does not use it to notify its observers.

This implementation of the Observer pattern provides a platform which is loosely coupled meaning that the subject does not need to know the contents or the location of the switch and rather only whether it needs to be detonated. In addition both the bomb and switch use a subscribe and unsubscribe method which allows them to dynamically register and deregister from their respective subscription list. 
 

### c) Inheritance Design

[harish/inheritence-design](https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/merge_requests/3)

> i. Name the code smell present in the above code. Identify all subclasses of Entity which have similar code smells that point towards the same root cause.

The code smell we found was **Dead Code**, which is when code is placed in situations where it is not required to be. This can be seen with the onOverlap, onDestroy, and onMovedAway methods, as every subclass of Entity provided a function stub for these functions as they were declared within Entity.java as abstract functions. This was a code smell as although this was functional and compilable code, it meant that the functions needed stubs where no functionality was provided.



> ii. Redesign the inheritance structure to solve the problem, in doing so remove the smells.

To solve this we made the functions (onOverlap, onDestroy, and onMovedAway) not abstract meaning that we could provide a default stub functionality for it within the Entity.java function and then override this functionality within the required subclasses.

### d) More Code Smells

[Abbas/Shotgun_Surgery-onOverlap](https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/merge_requests/4)

> *Collectable entities are a big problem. We tried to change the way picking up items is handled, to be done at the player level instead of within the entity itself but found that we had to start making changes in heaps of different places for it to work, so we abandoned it.*

> i. What design smell is present in the above description?

The code smell is **Shotgun Surgery**, which means that the code written is in a rigid structure in which it is difficult to modify the code base without making various large changes in numerous places. This is due to the fact that the code is not modular enough and is tightly coupled resulting in multiple dependencies. In specific we found that when looking through the entities that the player could pick up they all shared the same functionality with some little variances. This is an example of shotgun surgery as any changes in the code base to modify the onOverlap function seen throughout the code base would require changes in multiple places.


> ii. Refactor the code to resolve the smell and underlying problem causing it.

To refactor the above smell, we created a Collectable.java file as an abstract sub-class of the Entity.java file. This allowed us to place functionality for the onOverlap and canMoveOnto function within this class which would be inherited by the appropriate classes. 

### e) Open-Closed Goals

[harish/goal-factory-composite-refactor](https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/commit/1d24832d60c7d10f6f543f9e7ce148df8f03eeaa)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?

Open closed programming principle explains that code must be written such that it is closed for modification but open for extension. This means that the code must be able to be expanded on without having to modify existing code heavily. The current code within the goals package does not adhere to this design principle as adding a new goal type for example would require significant changes in the pre-existing code to create this new goal type. The use of switch statements within the code tightly couples the goal type to the code and means a new case must be introduced in order to accommodate for a new goal. 

Thus, we believe that the Goals package should be changed to improve maintainability.

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

We believe that the goals package should be refactored, and have chosen the composite pattern and factory pattern as we believe that this would allow for a effective and more maintainable code base. A composite pattern is chosen for the structure of the tree as it allows for the tree to be structured of iniform node types which in this case is Goal.java. This Goal.java is transformed into a abstract class with abstract function stubs which will be uniquely implemented by each node type (nodes and leaf node). 

In addition, a factory pattern is chosen to allow the code to create a logic tree recursively from the provided JSONObject type.

This section is inspired by lab07 core exercise 1.

[Briefly explain what you did]

### f) Open Refactoring

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

[Harish/enemy-goal implementation and tests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/merge_requests/8)

**Assumptions**

The fact i assumed was that i assumed enemies killed by a player placedd bomb would not be considered a kill as the player did not enter the battle phase with the enemy.

**Design**

I designed this by creating a new EnemyGoal Class which would store the number of enemies neccesary to be slain AND how many had be slain already and continuously check the number of enemies slain. This would be an extension of the goals package designed with the factory and composite pattern. 

**Changes after review**

After a design review we thought best that the goal would be better designed if the number of enemies slain was stored at the player level as it is the player's attribute. This way all the achieved function had to do was verify the information rather than contuniously implementing logic.

**Test list**

Pass Cases: 
1. Achieve the enemy slain target and destroy all spawners
2. No enemies requruired to be slain and no spawners on map

Fail Cases:
1. Achieve enemy slain target but spawners exist
2. Destroy spawners but enemy slain target not achieved


### Choice 1 (Swamp Tile)

[Links to your merge requests](/put/links/here)

**Assumptions**

**Design**

Create a new entity called 'SwampTile.java' which will be an entity created on the item layer. When a enemy activates the onOverlap funciton within swampTile, the enemy will now be stuck as their new stuck field will act as a countdown until they can move again.

**Changes after review**


**Test list**

Pass Tests:
1. Can create a swamp tile
2. Player walks over the tile and nothing happens
3. Zombie walks over and gets stuck
4. Mercenary walks over and gets stuck
5. Allied Mercenary walks over and gets stuck


### Choice 2 (Sun Stone & More Buildables)

merge requests: [https://nw-syd-gitlab.cseunsw.tech/COMP2511/23T3/teams/F09A_BIDOOF/assignment-ii/-/merge_requests/9]

**Assumptions**

=> Sunstone can open any door.
=> Multiple sunstones can be collected.
=> They are not consumed when oping doors
=> they do not have a limit of usage and can be used in nay way, multiple ways.

**Design**

Sunstone will be a collectable that in herits from the collectable class already established.

The class itself will hold minimal functionality as most of the properties it has reside more in how the gme interacts with the object.

These include:
    - player.java, when get buildabeles never removes sunstone after any crafting.
    - check if key open door in door.java, if not, use sunstone and ensure it is not removed from plyer inventory.

**Changes after review**
After understanding that nearly all functionality is based on interaction we moved most code out of the class (apart from the constructor) and chnaged a few lines through out the code base to endure the functionality was achieved 


**Test list**

- pick up sunstone 
- craft using sunstone 
- craft using key and treasure without using sunstone in inventory 
- pick up multiple sunstones 
- use sunstone to walk through doors

**Other notes**

[Any other notes]

### Choice 3 (Insert choice) (If you have a 3rd member)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

## Task 3) Investigation Task ⁉️

[Merge Request 1](/put/links/here)

[Briefly explain what you did]

[Merge Request 2](/put/links/here)

[Briefly explain what you did]

Add all other changes you made in the same format here:
