import java.util.*;
public class Player {
	public int score;
	public int betV;
	public char betS;
	public int handSize;
	public ArrayList<Card> hand;
	public ArrayList<Card> wonCards;
	public int roundScore;// A+K+Q+J+10
	public int roundPoints;
	public boolean hasPlayed;
	public Player()
	{
		score=0;
		handSize=0;
		hand=new ArrayList<Card>();
		wonCards=new ArrayList<Card>();
	}
	
	public Card playCard(Card c)
	{
		int i;
		Card ret;
		for(i=0;i<hand.size();i++)
		{
			ret=hand.get(i);
			if((c.getVal()==ret.getVal())&&(c.getSuit()==ret.getSuit()))
			{
				handSize--;
				hand.remove(i);
				return ret;
			}
		}
		System.out.println("Card not in hand.");
		hasPlayed=true;
		return null;
	}
	
	public void drawCard(Card c)
	{
		hand.add(c);
		handSize++;
	}
	
	public void betV(int v)
	{
		betV=v;
	}
	
	public void betS(char s)
	{
		betS=s;
	}
	
	public int getScore()
	{
		return score;
	}
	
	
	/*
	public static void main(String args[]){
		Player test=new Player();
		test.drawCard(new Card(2,'S'));
		test.drawCard(new Card(3,'H'));
		test.drawCard(new Card(5,'S'));
		test.drawCard(new Card(8,'H'));
		//test.handSize=4;
		System.out.println(test.handSize);
		Card c=new Card(3,'H');
		Card c2=new Card(11,'H');
		//Card print=test.playCard(c);
		//System.out.println(print.getSuit()+" "+print.getVal());
		System.out.println();
		for(int i=0;i<test.handSize;i++)
		{
			System.out.println(test.hand.get(i).getVal()+" "+test.hand.get(i).getSuit());
		}
	}
	*/

}
