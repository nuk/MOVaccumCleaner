package br.unb.sma.MOVaccumCleaner.spike;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class SpikeAgent extends Agent {
	private static final long serialVersionUID = 8832032748421965813L;

	@Override
	protected void setup() {
		System.out.println("Setting Up my SpikeAgent :"+getAID().getName());
		System.out.println("Local is :"+getAID().getLocalName());
		addBehaviour(new TickerBehaviour(this,1000) {
			private static final long serialVersionUID = 277855709885559533L;

			@Override
			protected void onTick() {
				System.out.println("They keep rolling: "+ getTickCount());
				if (getAID().getLocalName().startsWith("sendSpike")){
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.addReceiver(new AID("receiveSpike",AID.ISLOCALNAME));
					msg.setContent("Its "+getTickCount()+" o'clock.");
					send(msg);
					System.out.println("sent message");
				}else{
					ACLMessage msg = receive();
					if (msg != null){
						System.out.println("Now i know its '"+msg.getContent()+"' because of the "+msg.getSender());
					}else{
						block();
					}
				}
			}
		});
	}
	
}
