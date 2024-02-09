Week 6
This week consisted of me and harish reading the spec and trying to develop a strong understanding of the provided code base. Furthermore we also sturctured how this weekly meeting would occur so each of us keep the other updated of progress and where our issues arise.

Week 7
We had begun working on part 1 together, tackling each question. By the end of this week we had finished part 1 a)-d).

Week 8
With the help of the provided help sessions we deciphered part 1 e)-f) and we spent half the week completing this section. For the latter half the week we selected our sections for task 2 and how we were gonna tackle each part i.e. how to most efficently use our time.

Week 9
We began tackling task 2. First thing I had initionally done was identify each of the tecnical reuirements for each section.

Techical requirements-----------------------------------------------------------------------------------------------------------------------

1.Task requirements:

	- Collectable entities (sun stone)
		Sunstone can do the following:
		=> should be collectible by the player.
		=> can be used to open doors. 
		=> can be used interchangeably with treasure or keys  when building entities
		=> cant be used to bribe mercenaries or assassins.
		=> When used for opening doors or in building entities, Sun Stones should be retained after use. 
		=> When used as an ingredient in crafting, they should be consumed.
	- Buildable entities (spectre and midnight armour)
		=> Sceptres can be crafted with specific combinations of items and have a special ability to control the minds of mercenaries 		   or assassins for a limited duration.
		=> Midnight Armour can be crafted with specific combinations of items and provides extra attack damage and protection to the 	           player. It lasts forever once crafted.
	
2. Regarding technical requirements for collectable entities:
	We will implement an object sunstone that satifies these functional requirements.
	1. We can extend collectable class to sunstone class, build in entity factory.
	2. The logic of opening doors is all handled by door.java in if a player has a key with the correct number then door 
	   is opened and key is removed, however assuming sunstone can open any door, then we add the logic to check if key is 
	   present or sunstone is present, and within the checking sunstone is present we do not remove it from inventory.
	3. Change list in get buildables for inventory.java. specfics how to list sunstone interchnagebly with key and treasure.
	4. all though its considered a treasure like entity, it cannot be used to bribe mercenary.

Regarding technical requirements for Buildable entities:
	We will implement an objects midnight armour and sceptre. 
	1. When creating the the object midnight armour and sceptre, the constructors for each need to one added to EntityFactory
	2. Change build checkbuildcriteria function (add recipes for sceptre midnight armour) and getBuildables functons add to list of 	   possible items result.add("{items_recipe}").
        3.  to implements effects for each item player.java->battlesatistics => player.applybuff
--------------------------------------------------------------------------------------------------------------------------------------------

This helped us devisise our assumptions like:
- there exists multiple sunstones to be collected and used
- the sceptre contols enemies once its been used etc.

(assumptions listed in the requirements)

As we tackled , while writing test and the config files were self explanatory, adjusting the code bse for sunstone requirde changing multiple lines of code that already handles basic logic to account for the existence of sunstone. This was somtimes the addition of one line on and introduction of a helper function.