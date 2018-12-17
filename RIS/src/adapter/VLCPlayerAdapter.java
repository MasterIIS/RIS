package adapter;

public class VLCPlayerAdapter implements MediaPlayer{
	private VLCplayer vlc;
	
	public VLCPlayerAdapter(VLCplayer vlc) {
		this.vlc = vlc;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		vlc.playVideo();
	}
}