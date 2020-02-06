
import java.util.*;
public class Deck {
	public ArrayList<Card> deck;
	private int size;
	
	public Deck()
	{
		deck=new ArrayList<Card>();
		int i;
		size=0;
		for(i=2; i<15;i++)
		{
			Card c1=new Card(i,'S');
			Card c2=new Card(i,'C');
			Card c3=new Card(i,'H');
			Card c4=new Card(i,'D');
			deck.add(c1);
			deck.add(c2);
			deck.add(c3);
			deck.add(c4);
			size+=4;
			
		}
	}
	
	public void shuffle()
	{
		int p;
		int random1;
		int random2;
		for(p=0;p<100;p++)
		{
			random1 = (int )(Math.random() * 52);
			random2 = (int )(Math.random() * 52);
			//System.out.println(random1+" "+random2);
			Collections.swap(this.deck,random1,random2);
		}
	}
	
	public void print()
	{
		int i;
		for(i=0;i<size;i++)
		{
			System.out.println(deck.get(i).getVal()+" "+deck.get(i).getSuit());
		}
	}
	
	public Card pop()
	{
		Card ret=deck.get(size-1);
		deck.remove(size-1);
		size--;
		return ret;
	}
	
	public void push(Card c)
	{
		deck.add(c);
		size++;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void sizeInc(int i)
	{
		size+=i;
	}
	
	public void sizeDec(int i)
	{
		size-=i;
	}
	
    /*
	public static void main(String args[]){
        Deck d1=new Deck();
        System.out.println(d1.getSize());
        //d1.sizeInc(5);
        System.out.println(d1.getSize());
        //d1.sizeDec(8);
        System.out.println(d1.getSize());
        
        for(int i=0;i<d1.getSize();i++)
        {
        	d1.deck.get(i).print();
        }
    }
    */
    
}
