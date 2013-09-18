/**
 * 
 * @author Victor F�rnemark 890812-0770
 *
 * IP Station�r.
 * Uppgift 1.1 Multitr�dning med ut�kning.
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
		
		t1.start();			//Skapa och starta en tr�d fr�n klass T1
		
		m.waitForFiveSec();	//V�nta i 5 sekunder
		
		System.out.println("\nStart T2\n");
		thread2.start();	//Skapa och starta en tr�d fr�n klass T2
		
		m.waitForFiveSec();	//V�nta i 5 sekunder

		System.out.println("\nPause T2\n");
		t2.pause();			//Pausa tr�den fr�n klass T2

		m.waitForFiveSec();	//V�nta i 5 sekunder
		
		System.out.println("\nRestart T2\n");
		t2.restart();		//Aktivera tr�den fr�n klass T2
		
		m.waitForFiveSec();	//V�nta i 5 sekunder
		
		System.out.println("\nAvsluta T1\n");
		t1.terminate();		//Stoppa tr�den fr�n klass T1
		
		m.waitForFiveSec();	//V�nta i 5 sekunder
		
		System.out.println("\nAvsluta T2\n");
		t2.terminate();		//Stoppa tr�den fr�n klass T2
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
