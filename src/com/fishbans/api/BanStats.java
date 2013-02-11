package com.fishbans.api;

public class BanStats {
	private int MCBANS = 0;
	private int MCBOUNCER = 0;
	private int MCBLOCKIT = 0;
	private int MINEBANS = 0;
	private int GLIZER = 0;
	public BanStats(long mcbansAmt,long mcbouncerAmt,long mcblockitAmt,long minebansAmt, long glizerAmt){
		setMCBANS((int) mcbansAmt);
		setMCBOUNCER((int) mcbouncerAmt);
		setMCBLOCKIT((int) mcblockitAmt);
		setMINEBANS((int) minebansAmt);
		setGLIZER((int) glizerAmt);
	}
	public int getMCBANS() {
		return MCBANS;
	}
	private void setMCBANS(int mCBANS) {
		MCBANS = mCBANS;
	}
	public int getMCBOUNCER() {
		return MCBOUNCER;
	}
	private void setMCBOUNCER(int mCBOUNCER) {
		MCBOUNCER = mCBOUNCER;
	}
	public int getMCBLOCKIT() {
		return MCBLOCKIT;
	}
	private void setMCBLOCKIT(int mCBLOCKIT) {
		MCBLOCKIT = mCBLOCKIT;
	}
	public int getMINEBANS() {
		return MINEBANS;
	}
	private void setMINEBANS(int mINEBANS) {
		MINEBANS = mINEBANS;
	}
	public int getGLIZER() {
		return GLIZER;
	}
	private void setGLIZER(int glizerAmt) {
		GLIZER = glizerAmt;
	}
}
