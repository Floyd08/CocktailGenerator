# CocktailGenerator

This is a passion project. I like programming and I like cocktails.

The goal of the app is to randomly generate cocktail recipes that are worth trying. I do this by drawing on my own knowledge about how to mix drinks, but I think it might be fun to have later versions rely on heuristics and machine learning. 

Currently you can try the app with a simple user interface here: http://18.191.37.210/CoGeneFrontEnd/

You can create a user account, and build your own list of ingredients (what you have on hand at home, perhaps), and the app will generate drinks from your custom list. If you want to use the Master List, you can use the app as a guest.

Current goals: 
- ~~Implement caching with Redis,  and move to a stateless backend~~    CHECK
- ~~Migrate to MySQL~~     Too early, the schema will likely change a lot in the near future
- Implement proper security

Features coming soon:
- Generate a drink from a template chosen at random
- Generate a drink from a template with at least one ingredient specifed by the user
- Generate a drink totally at random

Other features:
- Allow users to save, export, and share drinks they've generated
- Improve the quality of drinks that are generated by expanding ingredient attributes. 

