package com.sangame.model;

import com.calanger.common.dao.AbstractPO;

import java.util.Date;

/**
 * 普通用户
 *
 * Created by nudtn on 2017/4/29.
 */
public class User extends AbstractPO {
	private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String userName;
    private java.lang.String nickName;
    private java.lang.String password;
    private java.lang.String phoneNumber;
    private java.lang.String email;
    private java.util.Date registerTime;

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public void setId(Integer id, boolean forceUpdate) {
		setId(id);

		if (forceUpdate) {
			addForceUpdateProperty("id");
		}
	}

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public void setUserName(String userName, boolean forceUpdate) {
		setUserName(userName);

		if (forceUpdate) {
			addForceUpdateProperty("userName");
		}
	}

    public java.lang.String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

	public void setNickName(String nickName, boolean forceUpdate) {
		setNickName(nickName);

		if (forceUpdate) {
			addForceUpdateProperty("nickName");
		}
	}

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public void setPassword(String password, boolean forceUpdate) {
		setPassword(password);

		if (forceUpdate) {
			addForceUpdateProperty("password");
		}
	}

    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	public void setPhoneNumber(String phoneNumber, boolean forceUpdate) {
		setPhoneNumber(phoneNumber);

		if (forceUpdate) {
			addForceUpdateProperty("phoneNumber");
		}
	}

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public void setEmail(String email, boolean forceUpdate) {
		setEmail(email);

		if (forceUpdate) {
			addForceUpdateProperty("email");
		}
	}

    public java.util.Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

	public void setRegisterTime(Date registerTime, boolean forceUpdate) {
		setRegisterTime(registerTime);

		if (forceUpdate) {
			addForceUpdateProperty("registerTime");
		}
	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
