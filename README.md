# cribbage
SE 339 Final Project

Basic two-person cribbage game with simple server to connect users.  The server will be built in Python with one thread per user. The server will store the most recent move of the game by one user until the other user requests and successfully receives that data, upon which the most recent move will be removed from the server. On the client side, we will have two threads - one which sends requests to the server to see if there is an updated state and the UI thread which must remain responsive so that the user can exit the game.
