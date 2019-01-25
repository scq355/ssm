package com.qs.p2p.factorymethodpattern.base_2;

/**
 * Created by scq on 2018-03-22 16:18:16
 */
public class VolunteerFactory implements IFactory {
	@Override
	public LeiFeng createLeiFeng() {
		return new Volunteer();
	}
}
