package sernet.gs.reveng;

// Generated Jun 5, 2015 1:28:30 PM by Hibernate Tools 3.4.0.CR1

/**
 * SysExportNotizId generated by hbm2java
 */
public class SysExportNotizId implements java.io.Serializable {

	private int expId;
	private int notizImpId;
	private int notizId;

	public SysExportNotizId() {
	}

	public SysExportNotizId(int expId, int notizImpId, int notizId) {
		this.expId = expId;
		this.notizImpId = notizImpId;
		this.notizId = notizId;
	}

	public int getExpId() {
		return this.expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public int getNotizImpId() {
		return this.notizImpId;
	}

	public void setNotizImpId(int notizImpId) {
		this.notizImpId = notizImpId;
	}

	public int getNotizId() {
		return this.notizId;
	}

	public void setNotizId(int notizId) {
		this.notizId = notizId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysExportNotizId))
			return false;
		SysExportNotizId castOther = (SysExportNotizId) other;

		return (this.getExpId() == castOther.getExpId())
				&& (this.getNotizImpId() == castOther.getNotizImpId())
				&& (this.getNotizId() == castOther.getNotizId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getExpId();
		result = 37 * result + this.getNotizImpId();
		result = 37 * result + this.getNotizId();
		return result;
	}

}
