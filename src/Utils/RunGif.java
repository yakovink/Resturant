package Utils;

import View.ClientForm;
import View.PopUp;

/*simple tool for running gif without the ability to run gif files in css style**/

public class  RunGif extends Thread{
	
	private ClientForm cf;
	private static RunGif rg;
	
	public static RunGif getInstance() {
		if(rg==null) {
			rg=new RunGif();
		}
		return rg;
	}
	
	private RunGif() {	}
	
	public void run() {
		while(true) {
			try {
				for(int i=0;i<21;i++) {
					ClientForm.getForm().setStyle(String.format("-fx-background-image: url('file:images/gif/frame_%d_delay-1.99s.png');-fx-background-repeat: stretch;-fx-background-position: center center;-fx-background-size: 1980 1080;", i));
					Thread.sleep(700);
				}
				} catch (InterruptedException e) {
					PopUp.display("gif: internal error", false);
				}
			}
		}

	public ClientForm getCf() {
		return cf;
	}

	public void setCf(ClientForm cf) {
		this.cf = cf;
	}

	public static RunGif getRg() {
		return rg;
	}

	public static void setRg(RunGif rg) {
		RunGif.rg = rg;
	}
	}


