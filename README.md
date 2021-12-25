# CardTrader
Implementation of a client for a network-based trading card game running on a centralised server.
Clients authenticate to the server with a username and password, after which the server acknowledges and sends the current list of cards owned by the player. A player receives a free randomly chosen card upon login.
Once the cards have been sent, the server accepts the commands CREDITS, OFFERS, BUY, SELL, and CARDS that, respectively, allow the amount of credits available to be seen, get a list of cards for sale, buy cards, sell cards, and retrieve the player's cards again.
Cards can have one of four ranks: common, uncommon, rare, and unique. The odds of receiving less common cards for free are significantly reduced, so one might receive the same common card multiple times before obtaining a particular rare card. The goal of the game is to collect as many cards of different type as possible.

Project for university, received an A grade.

