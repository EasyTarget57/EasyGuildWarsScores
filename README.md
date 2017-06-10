The goal of this project is to make visualizations of Guild Wars scores for the game castle clash. This code starts from the Guild Wars Data.txt as produced by WoonTools for Castle Clash v3.0. The goal is to automatically parse these files, store them and create a website with different views on the data.

Currently you can start it as test and it will parse a file, do some calculations/add columns and transform it to json. This Json is then visualized by writing a static website which parses the Json and uses it to fil up a table.

TODO:
High priority:
- The calculations to determine average might attacked needs to be fixed.
- The html template needs to be improved.
- We need to be able to run it as a jar.

Once we get it workable, I want to:
- Save scores to a database (but keep website static).
- Improve html template and add more views. I think we should make the java write out a js file with the json data. Add a few functions that will get the right data and then we keep all html pages as the current one: an empty skeleton and javascript/jquery that gets the data and fills up the template.
- Make it easy to deploy.

Suggestions and/or help is always welcome. I'm decent at writing java but my html/css suck and I don't really know how to setup a website.