import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiPlayer {
	Receiver midiReceiver;
	static int stdVelocity = 127;
	
	public MidiPlayer() {
		try {
			midiReceiver = MidiSystem.getReceiver();
		} catch (MidiUnavailableException e) {
			System.err.println("Midi Unavailable Exception Thrown");
		}
	}

	public void play_note(int note, int velocity) {
		try{
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, 0, note, velocity);
			long timeStamp = -1;
			midiReceiver.send(myMsg, timeStamp);
		} catch (InvalidMidiDataException e) {
			System.err.println("Invalid MIDI Data Exception Thrown");
		}
	}
	
	public void play_strong_beat() {
		try {
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 116, 127);
			long timeStamp = -1;
			midiReceiver.send(myMsg, timeStamp);
			myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, 0, 36, 127);
			midiReceiver.send(myMsg, timeStamp);
			myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0, 0);
			midiReceiver.send(myMsg, timeStamp);
		} catch (InvalidMidiDataException e) {
			System.err.println("Invalid MIDI Data Exception Thrown");
		}
	}
	
	public void play_weak_beat() {
		try {
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 116, 0);
			long timeStamp = -1;
			midiReceiver.send(myMsg, timeStamp);
			myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, 0, 36, 127);
			midiReceiver.send(myMsg, timeStamp);
			myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0, 0);
			midiReceiver.send(myMsg, timeStamp);
		} catch (InvalidMidiDataException e) {
			System.err.println("Invalid MIDI Data Exception Thrown");
		}
	}
	
	public void play_song(String filename) {
		try {
			Scanner scan = new Scanner(new FileReader(filename));
			while(scan.hasNext()) {
				String s = scan.nextLine();
				while (!s.contains("delay")) {
					int note = Integer.parseInt(s.substring(0, s.indexOf(' ')));
					int code = Integer.parseInt(s.substring(s.indexOf(' ') + 1, s.length()));
					if (code == 1) play_note(note, stdVelocity);
					else stop_note(note, stdVelocity);
					s = scan.nextLine();
				}
				int delay = Integer.parseInt(s.substring(6, s.length()));
				Thread.sleep(delay);
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("404 Error: File Not Found");
		} catch (InterruptedException e) {
			System.err.println("You interrupted my sleep, you jerk.");
		}
	}
	
	public void stop_note(int note, int velocity) {
		try{
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_OFF, 0, note, velocity);
			long timeStamp = -1;
			midiReceiver.send(myMsg, timeStamp);
		} catch (InvalidMidiDataException e) {
			System.err.println("Invalid MIDI Data Exception Thrown");
		}
	}
//	public static void main(String [] args) throws Exception {
//		MidiPlayer mp = new MidiPlayer();
//		mp.play_song("song_test.txt");
//	}
}