import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiHandler {
	MidiDevice device;
	MidiInputReceiver mir;
	Game game;
	Interpreter intr;

	public MidiHandler(Game game, Interpreter intr) {
		this.game = game;
		this.intr = intr;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (int i = 0; i < infos.length; i++) {
			try {
				device = MidiSystem.getMidiDevice(infos[i]);
				//does the device have any transmitters?
				//if it does, add it to the device list
				System.out.println(infos[i]);

				//get all transmitters
				List<Transmitter> transmitters = device.getTransmitters();
				//and for each transmitter

				for(int j = 0; j<transmitters.size();j++) {
					//create a new receiver
					mir = new MidiInputReceiver(device.getDeviceInfo().toString());
					transmitters.get(j).setReceiver(mir);
				}

				Transmitter trans = device.getTransmitter();
				mir = new MidiInputReceiver(device.getDeviceInfo().toString());
				trans.setReceiver(mir);

				//open each device
				device.open();
				//if code gets this far without throwing an exception
				//print a success message
				System.out.println(device.getDeviceInfo()+" Was Opened");

			} catch (MidiUnavailableException e) {
				System.out.println("MIDI Unavailable Exception Thrown");
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
				if(message[0] == -80) {
					if (message[1] == 64) System.out.println("Damper pedal " + (message[2] == 0 ? "released" : "depresed"));
				} else if(message[0] == -112) {
					if (game != null) game.notePlayed(message[1], 0);
					if (message[1] < 60) intr.notePlayed(message[1]);
					game.setChord(intr.get_chord());
					System.out.println(intr.get_chord());
				} else if(message[0] == -128) {
					if (game != null) game.noteReleased(message[1]);
					if (message[1] < 60) intr.noteReleased(message[1]);
					game.setChord(intr.get_chord());
				}
			}
		}
		public void close() {}

	}

	public void close() {
		device.close();
		mir.close();
	}
}