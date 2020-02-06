import java.util.*;
public class Pitch implements DealerType{
	public char currentBet;
	public int betVal;
	public Player better;
	public int lowest;
	public int highest;
	public int numPlayers;
	public Card firstPlayed;
	public Dealer dealer;
	public ArrayList<Player> players;
	public ArrayList<Card> playedCards;
	
	@Override
	public Dealer createDealer() {
		PitchDealer dealer = new PitchDealer();
		return dealer;
	}
	
	
	public Pitch(int num)
	{
		numPlayers=num;
		players=new  ArrayList<Player>();
		playedCards=new ArrayList<Card>();
		Player real=new Player();
		players.add(real);
		for(int i=0;i<num-1;i++)
		{
			players.add(new AIPlayer());
		}
		dealer=createDealer();
	}

	public void startRound()
	{
		//deal each player a hand
		//System.out.println("DEALING:"+numPlayers);
		for(int i=0;i<numPlayers;i++)
		{
			players.get(i).hand=dealer.dealHand();
			/*System.out.print("Player "+i+" ");
			for(int j=0;j<6;j++) {
				System.out.print(players.get(i).hand.get(j).getVal()+""+players.get(i).hand.get(j).getSuit()+" ");
				
			}
			System.out.println();*/
			players.get(i).roundPoints=0;
			players.get(i).roundScore=0;
			players.get(i).hasPlayed=false;
		}
		
		//get their bets
		//set currentBet
		//set betVal
		//set better
	}
	
	public Player decideRound()
	{
		Card winner=playedCards.get(0);
		firstPlayed=winner;
		int winPos=0;
		for(int i=1;i<players.size();i++)
		{
			if(winner.getSuit()==currentBet)
			{
				
				//if winner is bet suit and played card is higher version of bet suit
				if((playedCards.get(i).getSuit()==currentBet) && ((playedCards.get(i).getVal()>winner.getVal())))
				{
					winner=playedCards.get(i);
					winPos=i;
				}
			}
			else
			{
				
				//if winner is not bet suit and played is
				if((playedCards.get(i).getSuit()==currentBet))
				{
					winner=playedCards.get(i);
					winPos=i;
				}
				//if winner is not and bet is not the bet suit
				else if((playedCards.get(i).getSuit()==firstPlayed.getSuit()))
				{
					if((playedCards.get(i).getVal())>firstPlayed.getVal())
					{
						winner=playedCards.get(i);
						winPos=i;
					}
				}
			}
		}
		
		Player winP=players.get(winPos);
		for(int i=0;i<playedCards.size();i++)
		{
			players.get(winPos).wonCards.add(playedCards.get(i));
		}	
		return winP;
	}
	
	//check winner
	//calculate score
	public boolean checkForWinner()
	{
		boolean ret=false;
		for(int i=0;i<numPlayers;i++)
		{
			if(players.get(i).getScore()>=7)
				ret=true;
		}
		return ret;
	}
	
	public void calcScore()
	{
		for(int i=0;i<numPlayers;i++)
		{
			players.get(i).roundScore = 0;
			players.get(i).roundPoints = 0;
		}
		
		Card low=new Card(15,currentBet);
		Card high=new Card(1,currentBet);
		for(int k=0;k<playedCards.size();k++)
		{
			if((playedCards.get(k).getSuit()==currentBet)&&(playedCards.get(k).getVal()<low.getVal()))
			{
				low=playedCards.get(k);
			}
			if((playedCards.get(k).getSuit()==currentBet)&&(playedCards.get(k).getVal()>high.getVal()))
			{
				high=playedCards.get(k);
			}
		}
		for(int i=0;i<numPlayers;i++)
		{
			Player current=players.get(i);
			for(int j=0; j<current.wonCards.size();j++)
			{
				//chek if they have min
				Card temp=current.wonCards.get(j);
				if((temp.getSuit()==low.getSuit()) && (temp.getVal()==low.getVal()))
				{
					current.roundPoints++;
				}
				//chck if they have max
				if((temp.getSuit()==high.getSuit()) && (temp.getVal()==high.getVal()))
				{
					current.roundPoints++;
				}
				//check for J
				if((temp.getSuit()==currentBet) && (temp.getVal()==11))
				{
					current.roundPoints++;
				}
				//calc points for other high cards
				if(temp.getVal()>10)
				{
					current.roundScore+=(temp.getVal()-10);
				}
				//calc points for 10
				if(temp.getVal()==10)
				{
					current.roundScore+=10;
				}
				//SHUFFLE CARDS BACK
				
			}
		}
		int highestP=0;
		int highestScore=0;
		for(int i=0;i<numPlayers;i++)
		{
			if(players.get(i).roundScore>highestScore)
			{
				highestP=i;
			}
			if((players.get(i).wonCards.size())==(numPlayers*6))
			{
				if(players.get(i).betV==5)
				{
					players.get(i).roundPoints++;
				}
			}
		}
		players.get(highestP).roundPoints++;
		
		for(int i=0;i<numPlayers;i++)
		{
			// if they got less than what they bet
			if(players.get(i).betV>(players.get(i).roundPoints))
			{
				players.get(i).score-=players.get(i).betV;
			}
			else
			{
				players.get(i).score+=players.get(i).roundPoints;
			}
		}
		if(((PitchDealer)dealer).pitchDeck.getSize() < 25)
		{
			Deck dd=new Deck();
			dd.shuffle();
			((PitchDealer)dealer).pitchDeck =dd;
		}
	}
}
