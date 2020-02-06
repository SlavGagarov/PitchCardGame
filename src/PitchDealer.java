import java.util.*;
public class PitchDealer implements Dealer{
	
	public Deck pitchDeck;
	
	public PitchDealer()
	{
		pitchDeck=new Deck();
		pitchDeck.shuffle();
	}
	
	@Override
	public ArrayList<Card> dealHand() {
		ArrayList<Card> ret= new ArrayList<Card>();
		for(int i=0; i<6; i++)
		{
			ret.add(pitchDeck.pop());
		}
		return ret;
	}
	/*
	public static void main(String args[]){
		PitchDealer dealer1 = new PitchDealer();
		dealer1.pitchDeck.shuffle();
		ArrayList<Card> a1=dealer1.dealHand();
		for(int i=0;i<6;i++)
			a1.get(i).print();
		System.out.println();
		a1=dealer1.dealHand();
		for(int i=0;i<6;i++)
			a1.get(i).print();
		dealer1.dealHand();
		dealer1.dealHand();
		dealer1.dealHand();
		dealer1.dealHand();
		dealer1.dealHand();
		System.out.println();System.out.println();
		for(int i=0;i<dealer1.pitchDeck.getSize();i++)
		{
			dealer1.pitchDeck.deck.get(i).print();
		}
	}
	*/
}
