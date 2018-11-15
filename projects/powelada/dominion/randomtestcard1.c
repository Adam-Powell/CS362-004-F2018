// Adam Powell
// CS362 Fall 2018
// random card test
// cardtest1 smithy: 


#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>

#define TESTCARD "smithy"
#define TESTNUM 200



int asserttrue(int a, int b, int c)
{
	if (a != b)
	{
		//printf("%s TEST FAILED test: %d\n", TESTCARD, c);
		return 1;
	}
	return 0;
}

int treasureCount(struct gameState G)
{
	int treasure = 0;

	for (int i = 0; i < G.handCount[G.whoseTurn]; i++)
	{
		if (G.hand[G.whoseTurn][i] == copper || G.hand[G.whoseTurn][i] == silver || G.hand[G.whoseTurn][i] == gold)
			treasure++;
	}

	return treasure;
}

int main()
{
	struct gameState G, statG;

	int result, random = 0, player = 1, handCount, deckCount, total = 0, passed = 0;

	int k[10] = { adventurer, gardens, embargo, village, minion, mine, cutpurse,
		sea_hag, tribute, smithy };

		for (int i = 0; i < TESTNUM; i++) {
			//set basic information
			int numPlayers = (rand() % 4)+1;
			int gameSeed = rand() % 5000;
			int fFlag = 0;
			int bonus = 0;

			//initializes G to contain appropriate information
			initializeGame(numPlayers, k, gameSeed, &G);

			random = rand() % 10;
			if (random == 5) {
				G.deckCount[player] = 0;
				deckCount = 0;
			}
			else {
				random = rand() % MAX_DECK;
				G.deckCount[player] = random;
				deckCount = random;
			}

			random = rand() % MAX_DECK;
			G.handCount[player] = rand() % MAX_HAND;
			handCount = random;
			G.discardCount[player] = rand() % MAX_DECK;

			memcpy(&statG, &G, sizeof(struct gameState));

			int result = cardEffect(smithy, 1, 1, 1, &G, 0, &bonus);
			fFlag += asserttrue(result, 0, 0);

			fFlag += asserttrue(statG.playedCardCount, (G.playedCardCount + 1), 1);
			fFlag += asserttrue(G.handCount[G.whoseTurn], statG.handCount[statG.whoseTurn] + 2, 2);

			if (fFlag == 0)
				passed++;
			total++;
		}

		printf("Testing card %s\nTests Attempted: %d\nTests Completed: %d\nTests Succesful: %d\n", TESTCARD, TESTNUM, total, passed);
	return 0;
}