/**
 * 
 * @author Victor Förnemark 890812-0770
 *
 * IP Stationär.
 * Uppgift 1.1 Multitrådning med utökning.
 * 
 * Filer:
 * Main.java
 * T1.java
 * T2.java
 *
 */
public class Main {

	public static void main(String[] args) {
		
		Main m = new Main();
		T1 t1 = new T1();
		T2 t2 = new T2();
		Thread thread2 = new Thread(t2);
		
		t1.start();			//Skapa och starta en tråd från klass T1
		
		m.waitForFiveSec();	//Vänta i 5 sekunder
		
		System.out.println("\nStart T2\n");
		thread2.start();	//Skapa och starta en tråd från klass T2
		
		m.waitForFiveSec();	//Vänta i 5 sekunder

		System.out.println("\nPause T2\n");
		t2.pause();			//Pausa tråden från klass T2

		m.waitForFiveSec();	//Vänta i 5 sekunder
		
		System.out.println("\nRestart T2\n");
		t2.restart();		//Aktivera tråden från klass T2
		
		m.waitForFiveSec();	//Vänta i 5 sekunder
		
		System.out.println("\nAvsluta T1\n");
		t1.terminate();		//Stoppa tråden från klass T1
		
		m.waitForFiveSec();	//Vänta i 5 sekunder
		
		System.out.println("\nAvsluta T2\n");
		t2.terminate();		//Stoppa tråden från klass T2
	}
	
	public void waitForFiveSec()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
