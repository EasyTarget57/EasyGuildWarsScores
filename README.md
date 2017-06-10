### EasyGuildWarsScores ###

## Overview ##

The goal of this project is to make visualizations of Guild Wars scores for the game castle clash. This code starts from the Guild Wars Data.txt as produced by WoonTools for Castle Clash v3.0. The goal is to automatically parse these files, store them and create a website with different views on the data.

# Status #
Currently you can start it as test and it will parse a file, do some calculations/add columns and transform it to json. This Json is then visualized by writing a static website which parses the Json and uses it to fil up a table.

Unless someone can help set up a better system for a website I'll keep with writing out Json from Java and make static websites read Json and fill up the view. 

# TODO #
Short term 
- The calculations to determine average might attacked needs to be fixed.
- The html template needs to be improved.
- We need to be able to run it as a jar.

Medium term
- Save scores to a database (but keep website static).
- Improve html template.
- Add support for displaying multiple GWs results.
- Add a view with average scores over last 5 or 10 GWs.
- Add a view showing all GWs results of a player.

Long term
- Add graphs, exports of data, other fancy things that we might be able to use html components for.
- Make it easy to deploy. Ideally a script that starts up a virtual android and captures with wireshark CLI, it tells you: log in to castle clash, you click ok, it starts capturing and tells you to look at all the GW scores. You say ok. If it captured the packets correctly it shuts down the virtual android, runs WoonTools, runs this program and then updates a server.
- Do the same for visualizing other data from WoonTools.

Suggestions and/or help is always welcome. I'm decent at writing java but my html/css suck and I don't really know how to setup a website.

