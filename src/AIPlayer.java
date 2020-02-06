
public class AIPlayer extends Player{
	public int cD=0;
	public int cS=0;
	public int cC=0;
	public int cH=0;
	
	public char decideBetSuit()
	{


		char ret = 'N';
		for(int i=0;i<hand.size();i++)
		{
			if(hand.get(i).getVal()>12 || hand.get(i).getVal()<4)
			{
			if(hand.get(i).getSuit() == 'D')
				cD++;
			if(hand.get(i).getSuit() == 'S')
				cS++;
			if(hand.get(i).getSuit() == 'C')
				cC++;
			if(hand.get(i).getSuit() == 'H')
				cH++;
			}
			if(hand.get(i).getVal()==11)
			{
				if(hand.get(i).getSuit() == 'D')
					cD++;
				if(hand.get(i).getSuit() == 'S')
					cS++;
				if(hand.get(i).getSuit() == 'C')
					cC++;
				if(hand.get(i).getSuit() == 'H')
					cH++;
			}
		}
		if(cD>2)
			ret='D';
		else if(cS>2)
			ret='S';
		else if(cC>2)
			ret='C';
		else if(cH>2)
			ret='H';
		
		if(ret=='N')
		{
			if(cD>1)
				ret='D';
			else if(cS>1)
				ret='S';
			else if(cC>1)
				ret='C';
			else if(cH>1)
				ret='H';
		}
		
		if(ret=='N')
			ret=hand.get(0).getSuit();
		betS=ret;
		return ret;
	}
	
	public int decideBetVal()
	{
		boolean bool10=false;
		for(int i=0;i<hand.size();i++)
		{
			if(hand.get(i).getSuit()==betS && hand.get(i).getVal()==10)
			{
				bool10=true;
			}
		}

		int ret=0;
		if(cD>cS)
			ret=cS;
		if(cC>ret)
			ret=cC;
		if(cH>ret)
			ret=cH;
		if(ret>2)
			ret=5;
		if(ret==2 && bool10)
			ret=4;
		
		betV=ret;
		cD=0;
		cS=0;
		cC=0;
		cH=0;
		return ret;
	}
	
	public Card decidePlay(Pitch Game)
	{
		Card ret=playCard(hand.get(0));
		int max=0;
		int maxPos=0;
		int min=15;
		int minPos=0;
		//if going first
		if(Game.playedCards.size()==0)
		{
			for(int i=0;i<hand.size();i++)
			{
				//find max trump
				if(hand.get(i).getSuit()==Game.currentBet && hand.get(i).getVal()>max)
				{
					max=hand.get(i).getVal();
					maxPos=i;
				}
				if(max>0)
					return hand.get(maxPos);	
			}
			//if no trump
			for(int i=0;i<hand.size();i++)
			{
				if(hand.get(i).getVal()>max)
				{
					max=hand.get(i).getVal();
					maxPos=i;
				}
				return hand.get(maxPos);
			}
		}
		
		else
		{
			//try beat first played
			for(int i=0;i<hand.size();i++)
			{
				//have a card that wins
				if((hand.get(i).getSuit()==Game.playedCards.get(0).getSuit()) && (hand.get(i).getVal()>Game.playedCards.get(0).getVal()))
				{
					return hand.get(i);
				}
			}
			for(int i=0;i<hand.size();i++)
			{
				//min for played suit
				if((hand.get(i).getSuit()==Game.playedCards.get(0).getSuit()) && (hand.get(i).getVal()<min))
				{
					minPos=i;
					min=hand.get(i).getVal();
				}
				return hand.get(minPos);
			}
			for(int i=0;i<hand.size();i++)
			{
				//min if no other matches
				if(hand.get(i).getVal()<min)
				{
					minPos=i;
					min=hand.get(i).getVal();
				}
				return hand.get(minPos);
			}
		}
		return ret;
	}
	
	/*
	public static void main(String args[]){
		AIPlayer test=new AIPlayer();
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
		
		Card ai=test.decidePlay();
		System.out.println("Decided: "+ai.getVal()+" "+ai.getSuit());
		
		System.out.println();
		for(int i=0;i<test.handSize;i++)
		{
			System.out.println(test.hand.get(i).getVal()+" "+test.hand.get(i).getSuit());
		}
		
	}
	*/
}

