package adapter;

public class TestAdapter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RISPlayer ris = new RISPlayer();
		VLCplayer vlc = new VLCplayer();
		VLCPlayerAdapter vlcAdapter = new VLCPlayerAdapter(vlc);
		
		ris.play();
		//vlc.playVideo();
		vlcAdapter.play();
		
	}

}
