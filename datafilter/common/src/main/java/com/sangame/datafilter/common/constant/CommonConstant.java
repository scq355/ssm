package com.sangame.datafilter.common.constant;

public class CommonConstant {

	/**
	 * @author MaoRenwei
	 * @description 数据的状态
	 * @date 2017/4/25
	 */
	public enum DataStatus {
		CHECK("待审核", 0),
		SYS_PASS("系统通过", 1),
		MANUAL_PASS("人员通过", 2),
		REJECT("拒绝", 3),
		ALL("状态无关", -1);

		private String name;
		private int value;

		DataStatus(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

}
