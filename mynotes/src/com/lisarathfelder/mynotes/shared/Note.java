package com.lisarathfelder.mynotes.shared;

import java.util.Date;

public class Note extends BusinessObject {

	private static final long serialVersionUID = 1L; //google

	private String title;
	private String content;
	private Date creatDate;
	private Date modDate;
	private String userName;
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
	 * @return the UserName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param UserID
	 *            the UserID to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
	
