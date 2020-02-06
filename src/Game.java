import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.awt.event.*;
import java.awt.*;
public class Game extends Application{
	Text welcome;
	Text FILL,bet,bet2;
	Button p2,p3,p4;
	Button c1,c2,c3,c4,c5,c6;
	Button b2,b3,b4,b5;
	Button midc1,midc2,midc3,midc4;
	Button b1=new Button("1");
	Button b0=new Button("Pass");
	Button startR=new Button("Start Round");
	Button nextRound=new Button("Next Round");
	Button afterScores=new Button("Continue");
	Button startAgain=new Button("Play Again!");
	Text dont=new Text("Do not click your cards yet!");
	Button suit1,suit2,suit3,suit4;
	Stage s1;
	Scene scene, scene2, sceneSuiit,endR;
	HashMap<String, Scene> sceneMap;
	int pn=0;
	Pitch game;
	ArrayList<Button> arrayB;
	public static void main(String[] args) {
	
	launch(args);
	}

	public Scene sceneOne()
	{
	    welcome=new Text("                                                           Welcome to Pitch");
	    FILL=new Text("                                       ");
	    welcome.setTextAlignment(TextAlignment.CENTER);

	    
	    BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(150));
	    
		HBox paneCenter = new HBox(10,FILL,welcome);
		HBox buttons = new HBox(10,FILL, p2,p3,p4);
		pane.setCenter(paneCenter);
		pane.setBottom(buttons);
		scene = new Scene(pane,800,600);
		return scene;
	}
	
	public Scene betvalScene(Pitch Game)
	{
		for(int i=0;i<Game.players.size();i++)
		{
			if(Game.players.get(i).score > 6)
			{
				return playAgainScene(Game);
			}
		}

		BorderPane pane = new BorderPane();
		
		Game.startRound();
		
		arrayB=new ArrayList<Button>();
		arrayB.add(c1);
		arrayB.add(c2);
		arrayB.add(c3);
		arrayB.add(c4);
		arrayB.add(c5);
		arrayB.add(c6);
		
		//Game.
		for(int i=0; i<Game.players.get(0).hand.size();i++) {
			arrayB.get(i).setMinHeight(200);
			arrayB.get(i).setMinWidth(100);
			arrayB.get(i).setText(Game.players.get(0).hand.get(i).print());
		}
		
		HBox cards = new HBox(36,c1,c2,c3,c4,c5,c6);
		pane.setBottom(cards);
		//get bets
		
		bet=new Text("Bet a Value: ");
		

		HBox numBet = new HBox(5,bet,b0,b1,b2,b3,b4,b5);
		VBox colnumBet = new VBox(20,numBet,dont);
		numBet.setAlignment(Pos.CENTER);
		colnumBet.setAlignment(Pos.CENTER);
		pane.setCenter(colnumBet);
		
		

		//shows your cards and gets your bet
		//new scene if he is the highest bet is need probably call it in the last if
		scene2 = new Scene(pane,800,600);
		return scene2;
	}
	
	public Scene updateBet(int userB, Pitch Game)
	{

		int playerQues=0;
		int i;
		for(i=1; i<Game.numPlayers;i++)
		{
			AIPlayer tmpPlayer=(AIPlayer)Game.players.get(i);
			int tmp=tmpPlayer.decideBetVal();
			char chartmp=tmpPlayer.decideBetSuit();
			if (tmp>Game.players.get(0).betV)
			{
				Game.betVal=tmp;
				Game.currentBet=Game.players.get(i).betS;
				Game.better=Game.players.get(i);
				playerQues=1;
			}
			else
			{
				Game.players.get(i).betS=0;
			}
		}
		for(i=1; i<Game.numPlayers;i++)
		{
			if (Game.players.get(0).betV < Game.betVal)
			{
				Game.players.get(i).betS=0;
			}
		}
		
		if(playerQues==0)
		{
			Game.betVal=Game.players.get(0).betV;
			Game.better=Game.players.get(0);
			
			Text select=new Text("Select Suit");
			HBox suitBet = new HBox(5,select,suit1,suit2,suit3,suit4);
			VBox colsuitBet = new VBox(20,suitBet,dont);
			suitBet.setAlignment(Pos.CENTER);
			colsuitBet.setAlignment(Pos.CENTER);
			HBox cards = new HBox(36,c1,c2,c3,c4,c5,c6);
			Text highval=new Text("												You bet the highest value!");
			
			BorderPane pane = new BorderPane();
			
			pane.setTop(highval);
			pane.setCenter(colsuitBet);
			pane.setBottom(cards);
			sceneSuiit = new Scene(pane,800,600);
			return sceneSuiit;
		}
		else
		{
			Text you= new Text("Highest Bit : "+Game.betVal);
			you.setTextAlignment(TextAlignment.CENTER);
			Text got= new Text("By : Player "+i);
			got.setTextAlignment(TextAlignment.CENTER);
			FILL = new Text("														");
			HBox cardz=new HBox(36,c1,c2,c3,c4,c5,c6);
			VBox msg=new VBox(20,you,got,startR,dont);
			
			HBox Fillandmsg = new HBox(msg);
			Fillandmsg.setAlignment(Pos.CENTER);
			BorderPane pane = new BorderPane();
			msg.setLayoutX(200);
			msg.setLayoutY(200);
			pane.setCenter(Fillandmsg);
			
			pane.setBottom(cardz);
			Scene sceneOutbit=new Scene(pane,800,600); 
			return sceneOutbit;
		}
	}
	
	public Scene startRound(Pitch Game)
	{

		//CALL ending round if no cards in hand
		if(Game.players.get(0).hand.size()==0)
		{
			return (endRound(Game));
		}
		
		
		while(Game.players.get(0).hand.size() !=arrayB.size())
		{
			arrayB.remove(arrayB.size()-1);
		}
	
		HBox cards=new HBox(36);
		for(int i=0;i<arrayB.size(); i++)
		{
			arrayB.get(i).setMinHeight(200);
			arrayB.get(i).setMinWidth(100);
			arrayB.get(i).setText(Game.players.get(0).hand.get(i).print());
			cards.getChildren().addAll(arrayB.get(i));
		}
		String suitString="";
		if(Game.currentBet=='D')
			suitString="Diamonds";
		if(Game.currentBet=='S')
			suitString="Spades";
		if(Game.currentBet=='C')
			suitString="Clubs";
		if(Game.currentBet=='H')
			suitString="Hearts";
		
		Text betaC=new Text("													Play a Card!");
		Text trump=new Text("													Current Trump is "+suitString);
		VBox textbox=new VBox(10,betaC,trump);
		BorderPane pane = new BorderPane();
		pane.setTop(textbox);
		pane.setBottom(cards);
		Scene round=new Scene(pane,800,600);
		return round;
	}
	
	public Scene displayPlayed(Pitch Game)
	{

		Text played=new Text("Cards played this round");
		for(int i=1; i<Game.numPlayers;i++)
		{
			AIPlayer tmpPlayer=(AIPlayer)Game.players.get(i);
			Game.playedCards.add(tmpPlayer.decidePlay(Game));
		}
		
		midc1.setMinSize(100, 200);
		midc1.setText(Game.playedCards.get(0).print());
		midc2.setMinSize(100, 200);
		midc2.setText(Game.playedCards.get(1).print());
		midc3.setMinSize(100, 200);
		midc4.setMinSize(100, 200);
		if(Game.numPlayers > 2)
		{
			
			midc3.setText(Game.playedCards.get(2).print());
		}
		
		if(Game.numPlayers > 3)
		{
			
			midc4.setText(Game.playedCards.get(3).print());
		}
		
		HBox midCards = new HBox(36,midc1,midc2,midc3,midc4);
		midCards.setAlignment(Pos.CENTER);
		VBox collection = new VBox(20,played,midCards,nextRound);
		collection.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane();
		pane.setCenter(collection);
		Scene round=new Scene(pane,800,600);
		Game.decideRound();
		return round;
	}
	
	public Scene endRound(Pitch Game)
	{

		Game.calcScore();//calculates, adds and subtracts points based on bets and cards played
		Text scores=new Text("Scores:");
		String scoreStr=("You : "+Game.players.get(0).score+"\nPlayer1 : "+Game.players.get(1).score);
		if(Game.players.size()>2)
			scoreStr+=("\nPlayer2 : "+Game.players.get(2).score);
		if(Game.players.size()>3)
			scoreStr+=("\nPlayer3 : "+Game.players.get(3).score);
		Text scoreDis=new Text(scoreStr);
		VBox collection = new VBox(20,scores,scoreDis,afterScores);
		collection.setAlignment(Pos.CENTER);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(collection);
		Scene endR=new Scene(pane,800,600);
		return endR;
	}
	
	public Scene playAgainScene(Pitch Game)
	{
		
		String winner="WINNER\n";
		if(Game.players.get(0).score > 6)
		{
			winner+="You\n";
		}
		
		for(int i=1;i<Game.players.size();i++)
		{
			if(Game.players.get(i).score >6)
			{
				winner+=("Player"+i+"\n");
			}
		}
		Text winnerT=new Text(winner);
		VBox collection = new VBox(20,winnerT,startAgain);
		collection.setAlignment(Pos.CENTER);
		BorderPane pane = new BorderPane();
		pane.setCenter(collection);
		Scene endR=new Scene(pane,800,600);
		return endR;
	}
	
	public void start(Stage primaryStage) throws Exception {
		
	    primaryStage.setTitle("Pitch");
	    primaryStage.setWidth(800);
	    primaryStage.setHeight(600);
	    
	    p2=new Button("2 Players");
	    p3=new Button("3 Players");
	    p4=new Button("4 Players");
	    
		b2=new Button("2");
		b3=new Button("3");
		b4=new Button("4");
		b5=new Button("5");
	    
		c1=new Button();
		c2=new Button();
		c3=new Button();
		c4=new Button();
		c5=new Button();
		c6=new Button();
		
		suit1=new Button("Diamonds");
		suit2=new Button("Clubs");
		suit3=new Button("Hearts");
		suit4=new Button("Spades");
		
		midc1=new Button();
		midc2=new Button();
		midc3=new Button();
		midc4=new Button();
		
		primaryStage.setScene(sceneOne());
		//primaryStage.show();
		p2.setOnAction(event->{
					pn=2;
					game=new Pitch(pn);
					primaryStage.setScene(betvalScene(game));
			
		});
		
		p3.setOnAction(event->{
					pn=3;
					game=new Pitch(pn);
					primaryStage.setScene(betvalScene(game));
			}
		);
		
		p4.setOnAction(event->{
					pn=4;
					game=new Pitch(pn);
					primaryStage.setScene(betvalScene(game));
			}
		);
		b0.setOnAction(event->{
			game.players.get(0).betV(0);
			updateBet(0,game);
			//primaryStage.setScene(sceneOne());
			primaryStage.setScene(updateBet(0,game));
		});
		b1.setOnAction(event->{
			game.players.get(0).betV(1);
			updateBet(1,game);
			//primaryStage.setScene(sceneOne());
			primaryStage.setScene(updateBet(1,game));
		});
		b2.setOnAction(event->{
			
			game.players.get(0).betV(2);
			updateBet(2,game);
			//primaryStage.setScene(sceneOne());
			primaryStage.setScene(updateBet(2,game));
		}
		);
		b3.setOnAction(event->{
				game.players.get(0).betV(3);
				updateBet(3,game);
				//primaryStage.setScene(sceneOne());
				primaryStage.setScene(updateBet(3,game));
			}
		);
		b4.setOnAction(event->{
				game.players.get(0).betV(4);
				updateBet(4,game);
				//primaryStage.setScene(sceneOne());
				primaryStage.setScene(updateBet(4,game));
			}
		);
		b5.setOnAction(event->{
				game.players.get(0).betV(5);
				//updateBet(5,game);
				primaryStage.setScene(updateBet(5,game));
			}
		);
		
		suit1.setOnAction(event->{
			game.players.get(0).betS('D');
			game.currentBet='D';
			primaryStage.setScene(startRound(game));
		    }
		);
		
		suit2.setOnAction(event->{
			game.players.get(0).betS('C');
			game.currentBet='C';
			primaryStage.setScene(startRound(game));
		    }
		);
		
		suit3.setOnAction(event->{
			game.players.get(0).betS('H');
			game.currentBet='H';
			primaryStage.setScene(startRound(game));
		    }
		);
		
		suit4.setOnAction(event->{
			game.players.get(0).betS('S');
			game.currentBet='S';
			primaryStage.setScene(startRound(game));
		    }
		);
	    
		startR.setOnAction(event->{
			primaryStage.setScene(startRound(game));
		    }
		);
		
		nextRound.setOnAction(event->{
			primaryStage.setScene(startRound(game));
	    }
		);
		
		afterScores.setOnAction(event->{
			primaryStage.setScene(betvalScene(game));	    }
		);
		startAgain.setOnAction(event->{
			primaryStage.setScene(sceneOne());	    }
		);
		
		
		c1.setOnAction(event->{
			/*
			for(int i=0; i<game.playedCards.size();i++)
				System.out.print(game.playedCards.get(i).print());
			System.out.println("\nabove was b4 u play card and below after all cards are played");
			*/
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(0)));
			
			primaryStage.setScene(displayPlayed(game));//CHANGE THIS TO DISPLAY THE PLAYED CARDS
			//System.out.println("sizOfPlayedCards = "+game.playedCards.size());
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				//System.out.println("i="+i);
				game.playedCards.remove(0);
				//System.out.print(game.playedCards.get(0).print());
			}
			
		});
		c2.setOnAction(event->{
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(1)));
			primaryStage.setScene(displayPlayed(game));
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				game.playedCards.remove(0);
			}
		});
		c3.setOnAction(event->{
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(2)));
			primaryStage.setScene(displayPlayed(game));
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				game.playedCards.remove(0);
			}
		});
		c4.setOnAction(event->{
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(3)));
			primaryStage.setScene(displayPlayed(game));
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				game.playedCards.remove(0);
			}
		});
		c5.setOnAction(event->{
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(4)));
			primaryStage.setScene(displayPlayed(game));
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				game.playedCards.remove(0);
			}
		});
		c6.setOnAction(event->{
			game.playedCards.add(game.players.get(0).playCard(game.players.get(0).hand.get(5)));
			primaryStage.setScene(displayPlayed(game));
			int playedcardsize=game.playedCards.size();
			for(int i=0; i<playedcardsize;i++)
			{
				game.playedCards.remove(0);
			}
		});
		primaryStage.show();
}
}
