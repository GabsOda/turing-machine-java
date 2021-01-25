package entities;

public class Transition implements Comparable<Transition>{
	private int id;
	private String from;
	private String to; 
	private char read;
	private char write;
	private char direction;
	
	public Transition(int id, String from, String to, char read, char write, char direction) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.write = write;
		this.read = read;
		this.direction = direction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public char getWrite() {
		return write;
	}

	public void setWrite(char write) {
		this.write = write;
	}

	public char getRead() {
		return read;
	}

	public void setRead(char read) {
		this.read = read;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Transition [from=" + from + ", to=" + to + ", read=" + read + ", write=" + write
				+ ", direction=" + direction + "]";
	}

	@Override
	public int compareTo(Transition o) {
		if (o.getId() > this.id) {
			return 1;
		} else if (o.getId() < this.id){
			return -1;
		} else {
			return 0;
		}
	}
}
