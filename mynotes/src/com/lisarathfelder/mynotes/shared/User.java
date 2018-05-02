package com.lisarathfelder.mynotes.shared;

public class User extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String userPassword;
	private int userID;
	private boolean isLoggedIn;

	/**
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getuserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the password to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the UserID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param UserID
	 *            the UserID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the isLoggedIn
	 */
	public boolean getisLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn
	 *            the isLoggedIn to set
	 */
	public void setisLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
}