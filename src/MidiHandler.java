import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiHandler {
	private MidiDevice device;
	private MidiInputReceiver mir;
	private Game game;
	private Interpreter intr;
	private ArrayList<Integer> noteBuffer;
	private boolean dampened;

	public MidiHandler(Game game, Interpreter intr) {
		this.game = game;
		this.intr = intr;
		noteBuffer = new ArrayList<Integer>();
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);
				//add all transmitters to the device list
				System.out.println("Attempting to open " + infos[i]);

				//get all transmitters
				List<Transmitter> transmitters = device.getTransmitters();

				for(int j = 0; j<transmitters.size();j++) {
					//create a new receiver for each transmitter
					mir = new MidiInputReceiver(device.getDeviceInfo().toString());
					transmitters.get(j).setReceiver(mir);
				}

				Transmitter trans = device.getTransmitter();
				mir = new MidiInputReceiver(device.getDeviceInfo().toString());
				trans.setReceiver(mir);

				//open each device
				device.open();
				
				//if code gets this far without throwing an exception, print a success message
				System.out.println("Successfully opened " + device.getDeviceInfo() + "\n");
			} catch (MidiUnavailableException e) {
				System.out.println("These aren't the devices you are looking for.\n");
			}
		}
	}

	public class MidiInputReceiver implements Receiver {
		public String name;
		public MidiInputReceiver(String name) {
			this.name = name;
		}

		public void send(MidiMessage msg, long timeStamp) {
			byte message[] = new byte[3];
			message = msg.getMessage();
			if (message.length == 3) {
				if(message[0] == -80 && message[1] == 64) {
					if (message[2] == 127) {
						dampened = true;
					}
					else {
						dampened = false;
						for(int i : noteBuffer) {
							if (game != null) game.noteReleased(i);
							if (message[1] < 65) {
								intr.noteReleased(i);
								if(intr.currentType==7) { game.eraseChord();game.setNext(intr.get_next_chords()); game.drawChord(intr.get_next_chords().get(0)); }
							}
							game.setChord(intr.get_chord());
							
						}
						noteBuffer.clear();
					}
				} else if(message[0] == -112) {
					if (game != null) game.notePlayed(message[1], 0);
					if (message[1] < 65) intr.notePlayed(message[1]);
					game.setChord(intr.get_chord());
					game.setNext(intr.get_next_chords());
					game.drawChord(intr.get_next_chords().get(0));
				} else if(message[0] == -128) {
					if(!dampened) {
						if (game != null) game.noteReleased(message[1]);
						if (message[1] < 65) {
							intr.noteReleased(message[1]);
							if(intr.currentType==7) {game.eraseChord(); game.setNext(intr.get_next_chords()); game.drawChord(intr.get_next_chords().get(0)); }
						}
						game.setChord(intr.get_chord());
					} else {
						noteBuffer.add((int)message[1]);
					}
				}
			}
		}
		public void close() {}

	}

	public void imNotUseless() {}
	
	public void close() {
		device.close();
		mir.close();
	}
}