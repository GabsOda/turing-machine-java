package entities;

public class ProcessResult {
	private String tape;
	private String result_tape;
	private boolean accepted;
	private boolean loop;

	public ProcessResult() {

	}

	public ProcessResult(String tape, String result_tape, boolean accepted, boolean loop) {
		this.tape = tape;
		this.result_tape = result_tape;
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
		return result_tape;
	}

	public void setResult_tape(String result_tape) {
		this.result_tape = result_tape;
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
		return "ProcessResult [tape=" + tape + ", result_tape=" + result_tape + ", accepted=" + accepted + ", loop="
				+ loop + "]";
	}

}
