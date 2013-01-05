package com.fishbans.api;

public class Stats {
	private Integer MCBANS = 0;
	private Integer MCBOUNCER = 0;
	private Integer MCBLOCKIT = 0;
	private Integer MINEBANS = 0;
	private Integer BCBANS = 0;
	public Stats(long mcbansAmt,long mcbouncerAmt,long mcblockitAmt,long minebansAmt,long bcbansrAmt){
		setMCBANS((int) mcbansAmt);
		setMCBOUNCER((int) mcbouncerAmt);
		setMCBLOCKIT((int) mcblockitAmt);
		setMINEBANS((int) minebansAmt);
		setBCBANS((int) bcbansrAmt);
	}
	public Integer getMCBANS() {
		return MCBANS;
	}
	private void setMCBANS(Integer mCBANS) {
		MCBANS = mCBANS;
	}
	public Integer getMCBOUNCER() {
		return MCBOUNCER;
	}
	private void setMCBOUNCER(Integer mCBOUNCER) {
		MCBOUNCER = mCBOUNCER;
	}
	public Integer getMCBLOCKIT() {
		return MCBLOCKIT;
	}
	private void setMCBLOCKIT(Integer mCBLOCKIT) {
		MCBLOCKIT = mCBLOCKIT;
	}
	public Integer getMINEBANS() {
		return MINEBANS;
	}
	private void setMINEBANS(Integer mINEBANS) {
		MINEBANS = mINEBANS;
	}
	public Integer getBCBANS() {
		return BCBANS;
	}
	private void setBCBANS(Integer bCBANS) {
		BCBANS = bCBANS;
	}
}
