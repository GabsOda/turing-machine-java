package entities;

public class ProcessResult {
	private String tape;
	private String resultTape;
	private boolean accepted;
	private boolean loop;

	public ProcessResult() {

	}

	public ProcessResult(String tape, String resultTape, boolean accepted, boolean loop) {
		this.tape = tape;
		this.resultTape = resultTape;
		this.accepted = accepted;
		this.loop = loop;
	}

	public String getTape() {
		return tape;
	}

	public void setTape(String tape) {
		this.tape = tape;
	}

	public String getResult_tape() {
		return resultTape;
	}

	public void setResult_tape(String resultTape) {
		this.resultTape = resultTape;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public String toString() {
		return " - { Tape: " + tape + " -> result: " + resultTape + " } => Accepted? " + accepted + ", In Loop? "+ loop + "";
	}

}
