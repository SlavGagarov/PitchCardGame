public class Card {
    private int value;//11-J 12-Q 13-K 14-A
    private char suit; //S-spades C-clubs H-hearts D-diamonds

    public Card(int v, char s)
    {
        this.value=v;
        this.suit=s;
    }
    public int getVal()
    {
        return value;
    }
    public char getSuit()
    {
        return suit;
    }
    public String print()
    {
    	String ret = "";
    	if(value==11)
    		ret+="J ";//System.out.print("J ");
    	else if(value==12)
    		ret+="Q ";//System.out.print("Q ");
    	else if(value==13)
    		ret+="K ";//System.out.print("K ");
    	else if(value==14)
    		ret+="A ";//System.out.print("A ");
    	else
    		ret+=Integer.toString(value)+" ";//System.out.print(value+" ");
    	
    	if(suit=='S')
    		ret+="Spades ";//System.out.print("Spades ");
    	else if(suit=='C')
    		ret+="Clubs ";//System.out.print("Clubs ");
    	else if(suit=='H')
    		ret+="Hearts ";//System.out.print("Hearts ");
    	else if(suit=='D')
    		ret+="Diamonds ";//System.out.print("Diamonds ");
    	
    	//System.out.println();
    	return ret;
    		
    }
}
