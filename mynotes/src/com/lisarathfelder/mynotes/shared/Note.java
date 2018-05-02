package com.lisarathfelder.mynotes.shared;

import java.sql.Date;

public class Note extends BusinessObject {

	private static final long serialVersionUID = 1L; //google

	private String title;
	private String content;
	private Date creatDate;
	private Date modDate;
	private int userID;
	private int noteID;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the creatDate
	 */
	public Date getCreatDate() {
		return creatDate;
	}

	/**
	 * @param creatDate
	 *            the creatDate to set
	 */
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}

	/**
	 * @param modDate
	 *            the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
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
	 * @return the noteID
	 */
	public int getNoteID() {
		return noteID;
	}

	/**
	 * @param noteID
	 *            the noteID to set
	 */
	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}
	
	/**
	 * @return the content
	 */
	public String content() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void content(String content) {
		this.content = content;
	}
	
}
	
