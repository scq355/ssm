package com.qs.p2p.mediatorpattern.base_2;

/**
 * Created by scq on 2018-03-23 15:32:44
 */
public class UnitedNationsSecurityCouncil extends UnitedNations {

	private USA colleague1;
	private Iraq colleague2;

	public void setColleague1(USA colleague1) {
		this.colleague1 = colleague1;
	}

	public void setColleague2(Iraq colleague2) {
		this.colleague2 = colleague2;
	}

	@Override
	public void declare(String message, Country colleague) {
		if (colleague == colleague1) {
			colleague2.getMessage(message);
		}
		else {
			colleague1.getMessage(message);
		}
	}
}
